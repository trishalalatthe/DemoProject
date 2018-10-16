package com.myretail.exception;

/**
 * Custom Exception class when the Product is not found
 * @author Trishala
 *
 */
public class ProductNotFoundException extends RuntimeException{	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}
}
