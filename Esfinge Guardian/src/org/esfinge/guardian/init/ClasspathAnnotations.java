package org.esfinge.guardian.init;

import java.util.Map;
import java.util.Set;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.scanner.AnnotationScanner;
import org.esfinge.guardian.utils.ServiceLocator;

public class ClasspathAnnotations {
	private Map<String, Set<String>> classpathAnnotations;
	static private ClasspathAnnotations instance = null;
	
	private ClasspathAnnotations(AuthorizationContext context) { 
		AnnotationScanner scanner = ServiceLocator.getServiceImplementation(AnnotationScanner.class);
		classpathAnnotations = scanner.scan(context);
	}
	
	static public ClasspathAnnotations getInstance(AuthorizationContext context) {
		if (instance == null) {
			instance = new ClasspathAnnotations(context);
		}
		return instance;
	}
		
	public Map<String, Set<String>> getClasspathAnnotations() {
		return classpathAnnotations;
	}
}
