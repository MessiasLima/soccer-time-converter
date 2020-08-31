package br.com.messiasjunior.converter.service;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;
import br.com.messiasjunior.converter.exception.InvalidTimeException;

public interface ConverterService {
	String convertMatchTime(String matchTime) throws InvalidPeriodException, InvalidTimeException;
}
