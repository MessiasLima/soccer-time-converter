package br.com.messiasjunior.converter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidPeriodException extends Exception {
	public InvalidPeriodException() {
	}

	public InvalidPeriodException(String message) {
		super(message);
	}
}
