package org.esfinge.guardian.test.tester;

import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.esfinge.guardian.test.annotation.AlwaysAllowAnnotation;
import org.esfinge.guardian.utils.AnnotationUtils;
import org.junit.Test;

public class AnnotationUtilsTest {
	
	@Test
	public void getAnnotationTypeOfSimpleTestPath() throws InstantiationException, IllegalAccessException {
		Class<Annotation> clazz = AnnotationUtils.getAnnotationType(AlwaysAllowAnnotation.class.getName());
		assertTrue(clazz.getName().equals(AlwaysAllowAnnotation.class.getName()));
	}
	
	@AlwaysAllowAnnotation
	public void methodThatContainsADummyAnnotation() {
	}
	
	@Test
	public void getAnnotationFullnameWithAnAnnotationParamTest() throws NoSuchMethodException, SecurityException {
		Method m = this.getClass().getMethod("methodThatContainsADummyAnnotation");
		Annotation annotation = m.getAnnotation(AlwaysAllowAnnotation.class);
		String fullname = AnnotationUtils.getAnnotationFullname(annotation);
		assertTrue(fullname.equals(AlwaysAllowAnnotation.class.getName()));
	}
	
	@Test
	public void getAnnotationFullnameWithAnAnnotationTypeParamTest() throws NoSuchMethodException, SecurityException {
		Method m = this.getClass().getMethod("methodThatContainsADummyAnnotation");
		Annotation annotation = m.getAnnotation(AlwaysAllowAnnotation.class);
		String fullname = AnnotationUtils.getAnnotationFullname(annotation.annotationType());
		assertTrue(fullname.equals(AlwaysAllowAnnotation.class.getName()));
	}
}
