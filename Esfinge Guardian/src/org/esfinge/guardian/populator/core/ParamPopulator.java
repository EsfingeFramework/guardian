package org.esfinge.guardian.populator.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.esfinge.guardian.annotation.context.Environment;
import org.esfinge.guardian.annotation.context.Resource;
import org.esfinge.guardian.annotation.context.Subject;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.populator.Populator;

public class ParamPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {
		Method guardedMethod = context.getGuardedMethod();
		Object[] guardedMethodArgs = context.getGuardedMethodArgs();
		
		Annotation[][] annotations = guardedMethod.getParameterAnnotations();
		int lin = annotations.length;
		int col = (lin != 0) ? annotations[0].length : 0;
		
		for (int i=0; i < lin; i++) {
			for (int j=0; j < col; j++) {
				if (annotations[i].length == 0) {
					continue;
				}
				
				Class<? extends Annotation> type = annotations[i][j].annotationType();
				
				if (type.equals(Subject.class)) {
					Subject subject = (Subject) annotations[i][j];
					context.getSubject().put(subject.value(), guardedMethodArgs[i]);
				} else if (type.equals(Resource.class)) {
					Resource resource = (Resource) annotations[i][j];
					context.getResource().put(resource.value(), guardedMethodArgs[i]);
				} else if (type.equals(Environment.class)) {
					Environment environment = (Environment) annotations[i][j];
					context.getEnvironment().put(environment.value(), guardedMethodArgs[i]);
				}
			}
		}
	}
}