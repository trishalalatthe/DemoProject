package com.myretail.exception;

/**
 * Custome exception class for the data save failure scenario
 * @author Trishala
 *
 */
public class DataSaveFailureException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataSaveFailureException() {
		super();
	}

	public DataSaveFailureException(String message) {
		super(message);
	}

	public DataSaveFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSaveFailureException(Throwable cause) {
		super(cause);
	}
}
