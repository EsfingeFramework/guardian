package org.esfinge.guardian.exception;

@SuppressWarnings("serial")
public class ProxyCreationException extends RuntimeException {
	public ProxyCreationException(Throwable e) {
		super(e);
	}
	
	public ProxyCreationException(String message) {
		super(message);
	}
	
	public ProxyCreationException(String message, Throwable cause) {
		super(message, cause);
	}
}