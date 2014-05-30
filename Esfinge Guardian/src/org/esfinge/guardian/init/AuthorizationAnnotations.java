package org.esfinge.guardian.init;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.esfinge.guardian.utils.AnnotationUtils;

public class AuthorizationAnnotations {
	
	private Set<Class<? extends Annotation>> allAuthorizationAnnotations = new HashSet<Class<? extends Annotation>>();
	
	public AuthorizationAnnotations(Set<String> annotations) { 
		cacheAuthorizationAnnotations(annotations);
	}
	
	private void cacheAuthorizationAnnotations(Set<String> authorizationAnnotations) {
		for (String annotation : authorizationAnnotations) {
			Class<? extends Annotation> annotationType = AnnotationUtils.getAnnotationType( annotation );
			allAuthorizationAnnotations.add( annotationType );
		}
	}
	
	public void setAnnotationSet(Set<Class<? extends Annotation>> authorizationAnnotations) {
		allAuthorizationAnnotations = authorizationAnnotations;
	}
	
	public Boolean contains(Annotation annotation) {
		Class<? extends Annotation> type = annotation.annotationType();
		if (allAuthorizationAnnotations.contains( type ) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public Set<Annotation> extractAuthorizationAnnotations(Annotation[] annotations, Set<Annotation> authorizationAnnotations) {
		
		for (Annotation annotation : annotations) {
			
			if (this.contains(annotation)) {
				authorizationAnnotations.add(annotation);
			}
			// support for domain annotations
			if(!annotation.annotationType().getPackage().getName().startsWith("java.lang.annotation")){
				authorizationAnnotations.addAll(
						extractAuthorizationAnnotations(
												annotation.annotationType().getAnnotations(), 
												authorizationAnnotations));
			}
		}
		
		return authorizationAnnotations;
	}
}