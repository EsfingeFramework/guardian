package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class IllegalFieldAnnotationUsageException extends RuntimeException {
	public IllegalFieldAnnotationUsageException(Throwable e) {
		super(e);
	}
	
	public IllegalFieldAnnotationUsageException(String message) {
		super(message);
	}
	
	public IllegalFieldAnnotationUsageException(String message, Throwable cause) {
		super(message, cause);
	}
}
