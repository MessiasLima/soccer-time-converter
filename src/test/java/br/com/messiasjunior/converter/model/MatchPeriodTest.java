package br.com.messiasjunior.converter.model;

import static org.junit.jupiter.api.Assertions.*;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Period test")
class MatchPeriodTest {
	@Test
	@DisplayName("Should get period by short form")
	void fromShortForm() throws InvalidPeriodException {
		assertEquals(MatchPeriod.PRE_MATCH, MatchPeriod.fromShortForm("PM"));
		assertEquals(MatchPeriod.FIRST_HALF, MatchPeriod.fromShortForm("H1"));
		assertEquals(MatchPeriod.HALF_TIME, MatchPeriod.fromShortForm("HT"));
		assertEquals(MatchPeriod.SECOND_HALF, MatchPeriod.fromShortForm("H2"));
		assertEquals(MatchPeriod.FULL_TIME, MatchPeriod.fromShortForm("FT"));
	}

	@Test
	@DisplayName("Should throw exception if period is invalid")
	void fromShortFormException() {
		assertThrows(InvalidPeriodException.class, () -> MatchPeriod.fromShortForm(""));
		assertThrows(InvalidPeriodException.class, () -> MatchPeriod.fromShortForm("foo"));
		assertThrows(InvalidPeriodException.class, () -> MatchPeriod.fromShortForm(null));
	}
}