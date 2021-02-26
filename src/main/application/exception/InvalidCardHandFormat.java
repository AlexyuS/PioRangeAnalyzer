package main.application.exception;

public class InvalidCardHandFormat extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCardHandFormat(String message) {
		super(message);
	}
}
