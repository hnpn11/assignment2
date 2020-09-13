package com.fpt.assignment2;

import org.springframework.http.HttpStatus;

public class CustomMessageError extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorMessage errorMessage;
	private HttpStatus status;

	public CustomMessageError() {
		super();
	}

	public CustomMessageError(ErrorMessage errorMessage, HttpStatus status) {
		super();
		this.errorMessage = errorMessage;
		this.status = status;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
