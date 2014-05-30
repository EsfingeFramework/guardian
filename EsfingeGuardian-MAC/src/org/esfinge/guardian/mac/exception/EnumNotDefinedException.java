package org.esfinge.guardian.mac.exception;

@SuppressWarnings("serial")
public class EnumNotDefinedException extends RuntimeException {
	public EnumNotDefinedException(Throwable cause) {
		super(cause);
	}
	
	public EnumNotDefinedException(String message) {
		super(message);
	}
	
	public EnumNotDefinedException(String message, Throwable cause) {
		super(message, cause);
	}
}
