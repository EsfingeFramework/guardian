package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class ServiceLocatorMisuseException extends RuntimeException {
	public ServiceLocatorMisuseException(Throwable cause) {
		super(cause);
	}
	
	public ServiceLocatorMisuseException(String message) {
		super(message);
	}
	
	public ServiceLocatorMisuseException(String message, Throwable cause) {
		super(message, cause);
	}
}
