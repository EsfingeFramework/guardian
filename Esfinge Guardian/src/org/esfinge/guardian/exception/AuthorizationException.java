package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class AuthorizationException extends RuntimeException {
	public AuthorizationException(Throwable cause) {
		super(cause);
	}
	
	public AuthorizationException(String message) {
		super(message);
	}
	
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
}
