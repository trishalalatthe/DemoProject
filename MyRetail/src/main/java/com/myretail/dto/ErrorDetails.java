package com.myretail.dto;

import java.io.Serializable;

/**
 * The DTO for the Error Messages
 * @author Trishala
 *
 */
public class ErrorDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The error message
	 */
	private String message;

	/**
	 * @param message
	 */
	public ErrorDetails(String message) {
		this.message = message;

	}

	/**
	 * @return string message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}