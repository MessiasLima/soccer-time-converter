package br.com.messiasjunior.converter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidTimeException extends Exception {
	public InvalidTimeException(String time) {
		super("Invalid time:" + time);
	}
}
