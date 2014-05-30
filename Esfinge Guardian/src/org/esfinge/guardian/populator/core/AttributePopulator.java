package org.esfinge.guardian.populator.core;

import java.lang.reflect.Field;

import org.esfinge.guardian.annotation.context.Environment;
import org.esfinge.guardian.annotation.context.Resource;
import org.esfinge.guardian.annotation.context.Subject;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.IllegalFieldAnnotationUsageException;
import org.esfinge.guardian.populator.Populator;

public class AttributePopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {
		Field[] declaredFields = context.getGuardedObj().getClass().getDeclaredFields();
		
		Object guardedObj = context.getGuardedObj();
		
		for (Field field : declaredFields) {
			try {
				if (field.isAnnotationPresent(Subject.class)) {
					Subject subject = (Subject) field.getAnnotation(Subject.class);
					field.setAccessible(true);
					context.getSubject().put(subject.value(), field.get(guardedObj) );
				} else if (field.isAnnotationPresent(Resource.class)) {
					Resource resource = (Resource) field.getAnnotation(Resource.class);
					field.setAccessible(true);
					context.getResource().put(resource.value(), field.get(guardedObj) );
				} else if (field.isAnnotationPresent(Environment.class)) {
					Environment environment = (Environment) field.getAnnotation(Environment.class);
					field.setAccessible(true);
					context.getEnvironment().put(environment.value(), field.get(guardedObj) );
				}
			} catch (Exception e) {
				throw new IllegalFieldAnnotationUsageException("Cannot access the the value of: " + field.getName(), e);
			}
		}
	}
}