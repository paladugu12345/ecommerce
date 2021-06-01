package com.india.ecommerce.exception;

public class InavalidArgumentException  extends Exception{
	private static final long serialVersionUID = 1L;

	public InavalidArgumentException(String message) {
		super(message);

	}

	public InavalidArgumentException(String message, Throwable t) {
		super(message, t);

	}
}
