package org.esfinge.guardian.abac.exception;

@SuppressWarnings("serial")
public class MalformedELException extends RuntimeException {
	public MalformedELException(Throwable cause) {
		super(cause);
	}
	
	public MalformedELException(String message) {
		super(message);
	}
	
	public MalformedELException(String message, Throwable cause) {
		super(message, cause);
	}
}
