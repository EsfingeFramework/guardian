package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class AuthorizerCreationException extends RuntimeException {
	public AuthorizerCreationException(Throwable e) {
		super(e);
	}
	
	public AuthorizerCreationException(String message) {
		super(message);
	}
	
	public AuthorizerCreationException(String message, Throwable cause) {
		super(message, cause);
	}
}