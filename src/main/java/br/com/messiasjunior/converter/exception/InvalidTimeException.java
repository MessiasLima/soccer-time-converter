package br.com.messiasjunior.converter.exception;

public class InvalidTimeException extends Exception {
	public InvalidTimeException(String time) {
		super("Invalid time:" + time);
	}
}
