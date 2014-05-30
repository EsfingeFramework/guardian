package org.esfinge.guardian.utils;

import java.lang.annotation.Annotation;

import org.esfinge.guardian.exception.AnnotationInstantiationException;

public class AnnotationUtils {

	private AnnotationUtils() { }
	
	@SuppressWarnings("unchecked")
	static public Class<Annotation> getAnnotationType(String strAnnotation)  {
		Class<Annotation> annotationType = null;
		try {
			annotationType = (Class<Annotation>) Class.forName(strAnnotation);
		} catch (Exception e) {
			throw new AnnotationInstantiationException(e);
		}
		return annotationType;
	}
	
	static public Class<? extends Annotation> getAnnotationType(Annotation annotation) {
		return annotation.getClass();
	}
	
	static public String getAnnotationFullname(Annotation annotation) {
		return getAnnotationFullname(annotation.annotationType());
	}
	
	static public String getAnnotationFullname(Class<? extends Annotation> type) {
		String[] parts = type.toString().split(" ");
		return parts[1];
	}
	
	static public String extractAnnotationValue(Annotation annotation) {
		String annotationName = annotation.toString();
		String[] parts1 = annotationName.split("value=");
		if (parts1.length <= 1) {
			return null;
		}
		
		String strP1 = parts1[1];
		String[] parts2;
		if (strP1.contains(",")) {
			parts2 = strP1.split(",");
		} else {
			parts2 = strP1.split("\\)");
		}
		return parts2[0];
	}
}