package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class AnnotationInstantiationException extends RuntimeException {
	public AnnotationInstantiationException(Throwable e) {
		super(e);
	}
	
	public AnnotationInstantiationException(String message) {
		super(message);
	}
	
	public AnnotationInstantiationException(String message, Throwable cause) {
		super(message, cause);
	}
}
