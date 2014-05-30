package org.esfinge.guardian.authorizer;

import java.lang.annotation.Annotation;

import org.esfinge.guardian.context.AuthorizationContext;

public interface Authorizer<E extends Annotation> {
	Boolean authorize(AuthorizationContext context, E securityAnnotation);
}