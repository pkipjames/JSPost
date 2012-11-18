package com.tsystems.jschool.exception;

/**
 * @author Lilia Abdulina
 * This exception will be throw then isset duplicate entity 
 */
public class DuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateException() {
		super();
	}
	
	public DuplicateException(String arg) {
		super(arg);
	}

	public DuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateException(Throwable cause) {
		super(cause);
	}

}
