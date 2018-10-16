package com.myretail.dto;

import java.io.Serializable;

/**
 * @author Trishala
 *  DTO for the success message
 *
 */
public class SuccessDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the success meassage
	 */
	private String message;

	/**
	 * @param message
	 */
	public SuccessDetail(String message) {
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