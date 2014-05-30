package org.esfinge.guardian.utils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

public class AuthorizerUtils {
	
//	static public boolean isEL(String value) {
//		return value.matches(ELUtils.EL_PATTERN);
//	}
	
	static public Set<Annotation> getAuthorizationAnnotations(AuthorizationContext context, Annotation[] annotations) {
		return context
					.getRepository()
						.getAuthorizationAnnotations()
							.extractAuthorizationAnnotations(annotations, new HashSet<Annotation>());
	}
	
	static public Authorizer<? extends Annotation> getAuthorizer(AuthorizationContext context, Annotation authorizationAnnotation) {
		return context
					.getRepository()
						.getCachedAuthorizers()
							.getAuthorizer(context, context.getGuardedMethod(), authorizationAnnotation);
	}
}
