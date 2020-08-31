package br.com.messiasjunior.converter.model;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;

public enum MatchPeriod {
	PRE_MATCH("PM"), FIRST_HALF("H1"), HALF_TIME("HT"), SECOND_HALF("H2"), FULL_TIME("FT");

	private final String shortForm;

	MatchPeriod(String shortForm) {
		this.shortForm = shortForm;
	}

	public static MatchPeriod fromShortForm(String shortForm) throws InvalidPeriodException {
		if (shortForm == null || shortForm.isBlank()) {
			throw new InvalidPeriodException();
		}

		for (MatchPeriod matchPeriod : values()) {
			if (shortForm.equals(matchPeriod.shortForm)) {
				return matchPeriod;
			}
		}

		throw new InvalidPeriodException("Invalid period: " + shortForm);
	}
}
