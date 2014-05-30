package org.esfinge.guardian.mac.exception;

@SuppressWarnings("serial")
public class NegativeSensitivityLevelException extends RuntimeException {
	public NegativeSensitivityLevelException(Throwable cause) {
		super(cause);
	}
	
	public NegativeSensitivityLevelException(String message) {
		super(message);
	}
	
	public NegativeSensitivityLevelException(String message, Throwable cause) {
		super(message, cause);
	}
}
