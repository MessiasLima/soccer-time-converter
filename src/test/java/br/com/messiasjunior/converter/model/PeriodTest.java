package br.com.messiasjunior.converter.model;

import static org.junit.jupiter.api.Assertions.*;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Period test")
class PeriodTest {
	@Test
	@DisplayName("Should get period by short form")
	void fromShortForm() throws InvalidPeriodException {
		assertEquals(Period.PRE_MATCH, Period.fromShortForm("PM"));
		assertEquals(Period.FIRST_HALF, Period.fromShortForm("H1"));
		assertEquals(Period.HALF_TIME, Period.fromShortForm("HT"));
		assertEquals(Period.SECOND_HALF, Period.fromShortForm("H2"));
		assertEquals(Period.FULL_TIME, Period.fromShortForm("FT"));
	}

	@Test
	@DisplayName("Should throw exception if period is invalid")
	void fromShortFormException() {
		assertThrows(InvalidPeriodException.class, () -> Period.fromShortForm(""));
		assertThrows(InvalidPeriodException.class, () -> Period.fromShortForm("foo"));
		assertThrows(InvalidPeriodException.class, () -> Period.fromShortForm(null));
	}
}