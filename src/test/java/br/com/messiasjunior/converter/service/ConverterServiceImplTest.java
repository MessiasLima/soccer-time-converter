package br.com.messiasjunior.converter.service;

import static org.junit.jupiter.api.Assertions.*;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;
import br.com.messiasjunior.converter.exception.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Match time converter test")
class ConverterServiceImplTest {
	private ConverterService service;

	@BeforeEach
	void setup() {
		service = new ConverterServiceImpl();
	}

	@Test
	@DisplayName("Should convert pre-match time")
	void convertPreMatchMatchTime() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("00:00 - PRE_MATCH", service.convertMatchTime("[PM] 0:00.000"));
	}

	@Test
	@DisplayName("Should convert first half time rounded down")
	void convertFirstHalfTimeRoundedDown() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("00:15 - FIRST_HALF", service.convertMatchTime("[H1] 0:15.025"));
	}

	@Test
	@DisplayName("Should convert first half time rounded up")
	void convertFirstHalfTimeRoundedUp() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("03:08 - FIRST_HALF", service.convertMatchTime("[H1] 3:07.513"));
	}

	@Test
	@DisplayName("Should convert first half with additional time")
	void convertFirstHalfWithAdditionalTime() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("45:00 +00:00 - FIRST_HALF", service.convertMatchTime("[H1] 45:00.001"));
		assertEquals("45:00 +01:16 - FIRST_HALF", service.convertMatchTime("[H1] 46:15.752"));
	}

	@Test
	@DisplayName("Should convert half time")
	void convertHalfTime() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("45:00 - HALF_TIME", service.convertMatchTime("[HT] 45:00.000"));
	}

	@Test
	@DisplayName("Should convert second half time")
	void convertSecondHalfTime() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("45:01 - SECOND_HALF", service.convertMatchTime("[H2] 45:00.500"));
	}

	@Test
	@DisplayName("Should convert second half with additional time")
	void convertSecondHalfWithAdditionalTime() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("90:00 +00:01 - SECOND_HALF", service.convertMatchTime("[H2] 90:00.908"));
	}

	@Test
	@DisplayName("Should convert full time")
	void convertFullTime() throws InvalidPeriodException, InvalidTimeException {
		assertEquals("90:00 +00:00 - FULL_TIME", service.convertMatchTime("[FT] 90:00.000"));
	}

	@Test
	@DisplayName("Should throw exception if time format is invalid")
	void throwExceptionIfTimeFormatIsInvalid() {
		assertThrows(InvalidTimeException.class, () -> service.convertMatchTime("90:00"));
		assertThrows(InvalidTimeException.class, () -> service.convertMatchTime("FOO"));
	}

	@Test
	@DisplayName("Should throw exception if period is invalid")
	void throwExceptionIfInputIsInvalid() {
		assertThrows(InvalidPeriodException.class, () -> service.convertMatchTime("[H3] 90:00.000"));
	}

	@Test
	@DisplayName("Should throw exception if time is invalid")
	void throwExceptionIfTimeIsInvalid() {
		assertThrows(InvalidTimeException.class, () -> service.convertMatchTime("[PM] -10:00.000"));
	}
}