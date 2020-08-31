package br.com.messiasjunior.converter.model;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;

public enum Period {
	PRE_MATCH("PM"), FIRST_HALF("H1"), HALF_TIME("HT"), SECOND_HALF("H2"), FULL_TIME("FT");

	private final String shortForm;

	Period(String shortForm) {
		this.shortForm = shortForm;
	}

	public String getShortForm() {
		return shortForm;
	}

	public static Period fromShortForm(String shortForm) throws InvalidPeriodException {
		if (shortForm == null || shortForm.isBlank()) {
			throw new InvalidPeriodException();
		}

		for (Period period : values()) {
			if (shortForm.equals(period.shortForm)) {
				return period;
			}
		}

		throw new InvalidPeriodException("Invalid period: " + shortForm);
	}
}
