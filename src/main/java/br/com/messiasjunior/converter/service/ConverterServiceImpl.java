package br.com.messiasjunior.converter.service;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;
import br.com.messiasjunior.converter.exception.InvalidTimeException;
import br.com.messiasjunior.converter.model.MatchPeriod;
import java.time.Duration;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements ConverterService {
	private static final String MATCH_TIME_REGEX = "\\[[A-Z][A-Z0-9]\\]\\s[0-9]{1,2}:[0-9]{2}\\.[0-9]{3}";
	private static final Duration DURATION_REGULAR_FIRST_HALF = Duration.ofMinutes(45);
	private static final Duration DURATION_REGULAR_SECOND_HALF = Duration.ofMinutes(90);

	public static final String EXTRA_TIME_DELIMITER = " +";
	public static final String SECONDS_DELIMITER = ":";
	public static final String MATCH_DURATION_DELIMITER = " - ";

	@Override
	public String convertMatchTime(String matchTime) throws InvalidPeriodException, InvalidTimeException {
		if (!isMatchTimeValid(matchTime)) {
			throw new InvalidTimeException(matchTime);
		}

		var elementArray = matchTime.split(" ");
		var matchPeriod = parsePeriod(elementArray[0]);
		var duration = parseTime(elementArray[1]);
		var extraTime = getExtraTime(matchPeriod, duration);

		return formatMatchTime(matchPeriod, duration, extraTime);
	}

	private String formatMatchTime(MatchPeriod matchPeriod, Duration matchDuration, Duration extraTime) {
		StringBuilder builder = new StringBuilder();

		switch (matchPeriod) {
			case FIRST_HALF :
				builder.append(durationToString(normalizeMatchDuration(matchDuration, DURATION_REGULAR_FIRST_HALF)));
				break;
			case SECOND_HALF :
			case FULL_TIME :
				builder.append(durationToString(normalizeMatchDuration(matchDuration, DURATION_REGULAR_SECOND_HALF)));
				break;
			default :
				builder.append(durationToString(matchDuration));
		}

		if (!extraTime.isZero() || matchPeriod == MatchPeriod.FULL_TIME) {
			builder.append(EXTRA_TIME_DELIMITER);
			builder.append(durationToString(extraTime));
		}

		builder.append(MATCH_DURATION_DELIMITER);
		builder.append(matchPeriod.name());

		return builder.toString();
	}

	private Duration normalizeMatchDuration(Duration matchDuration, Duration regularMatchPeriodDuration) {
		if (matchDuration.minus(regularMatchPeriodDuration).isNegative()) {
			return matchDuration;
		} else {
			return regularMatchPeriodDuration;
		}
	}

	private Duration getExtraTime(MatchPeriod matchPeriod, Duration duration) {
		Duration extraTime;

		switch (matchPeriod) {
			case FIRST_HALF :
				extraTime = duration.minus(DURATION_REGULAR_FIRST_HALF);
				break;
			case SECOND_HALF :
			case FULL_TIME :
				extraTime = duration.minus(DURATION_REGULAR_SECOND_HALF);
				break;
			default :
				return Duration.ZERO;
		}

		return extraTime.isNegative() ? Duration.ZERO : extraTime;
	}

	private String durationToString(Duration duration) {
		var minutes = duration.toMinutes();
		var seconds = duration.toSecondsPart();
		var millis = duration.toMillisPart();

		if (millis > 499) {
			seconds += 1; // Rounding up
		}

		return String.format("%02d", minutes) + SECONDS_DELIMITER + String.format("%02d", seconds);
	}

	private Duration parseTime(String timeString) {
		var timeArray = timeString.split(SECONDS_DELIMITER);
		var minutes = Integer.parseInt(timeArray[0]);
		var seconds = Double.parseDouble(timeArray[1]);
		var timeInMillis = ((Double) (((minutes * 60) + seconds) * 1000)).longValue(); // Convert to millis

		return Duration.ofMillis(timeInMillis);
	}

	private MatchPeriod parsePeriod(String periodString) throws InvalidPeriodException {
		return MatchPeriod.fromShortForm(periodString.substring(1, 3));
	}

	private boolean isMatchTimeValid(String matchTime) {
		return matchTime.matches(MATCH_TIME_REGEX);
	}
}
