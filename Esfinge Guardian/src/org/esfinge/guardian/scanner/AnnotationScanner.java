package org.esfinge.guardian.scanner;

import java.util.Map;
import java.util.Set;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AnnotationScannerException;

public interface AnnotationScanner {
	public Map<String, Set<String>> scan(AuthorizationContext context) throws AnnotationScannerException;
	
}