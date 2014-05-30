package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class AnnotationScannerException extends RuntimeException {
	public AnnotationScannerException(Throwable e) {
		super(e);
	}
	
	public AnnotationScannerException(String message) {
		super(message);
	}
	
	public AnnotationScannerException(String message, Throwable cause) {
		super(message, cause);
	}
}
