package com.india.ecommerce.exception;

public class InavalidUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public InavalidUserException(String message) {
		super(message);

	}

	public InavalidUserException(String message, Throwable t) {
		super(message, t);

	}
}


