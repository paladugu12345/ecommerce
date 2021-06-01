package com.india.ecommerce.exception;

public class AccountNumberNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountNumberNotFoundException(String message) {
		super(message);

	}

	public AccountNumberNotFoundException(String message, Throwable t) {
		super(message, t);

	}
}
