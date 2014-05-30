package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class PopulatorClassAnnotationMisuseException extends RuntimeException {
	public PopulatorClassAnnotationMisuseException(Throwable cause) {
		super(cause);
	}
	
	public PopulatorClassAnnotationMisuseException(String message) {
		super(message);
	}
	
	public PopulatorClassAnnotationMisuseException(String message, Throwable cause) {
		super(message, cause);
	}
}
