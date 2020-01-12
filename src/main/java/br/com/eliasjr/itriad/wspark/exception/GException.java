package br.com.eliasjr.itriad.wspark.exception;

public class GException extends RuntimeException {

	private static final long serialVersionUID = 731731048434938541L;

	public GException(String message) {
		super(message);
	}

	public GException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
