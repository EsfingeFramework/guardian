package org.esfinge.guardian.rbac.exception;

@SuppressWarnings("serial")
public class RbacMisuseException extends RuntimeException {
	public RbacMisuseException(Throwable cause) {
		super(cause);
	}
	
	public RbacMisuseException(String message) {
		super(message);
	}
	
	public RbacMisuseException(String message, Throwable cause) {
		super(message, cause);
	}
}
