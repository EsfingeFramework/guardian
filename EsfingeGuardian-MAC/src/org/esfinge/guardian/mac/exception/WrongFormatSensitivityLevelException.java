package org.esfinge.guardian.mac.exception;

@SuppressWarnings("serial")
public class WrongFormatSensitivityLevelException extends RuntimeException {
	public WrongFormatSensitivityLevelException(Throwable cause) {
		super(cause);
	}
	
	public WrongFormatSensitivityLevelException(String message) {
		super(message);
	}
	
	public WrongFormatSensitivityLevelException(String message, Throwable cause) {
		super(message, cause);
	}
}
