package org.esfinge.guardian.utils;

import java.lang.annotation.Annotation;

import org.esfinge.guardian.annotation.config.AuthorizerClass;
import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AuthorizerCreationException;


public class AuthorizerFactory {
	
	private AuthorizerFactory() { }
	
	static public Authorizer<? extends Annotation> createAuthorizer(AuthorizationContext context, Class<? extends Annotation> annotationType) {
		Annotation authorizerAnnotation = annotationType.getAnnotation( AuthorizerClass.class );
		AuthorizerClass authorizerClazz = (AuthorizerClass) authorizerAnnotation;
		
		Authorizer<? extends Annotation> authorizer;
		try {
			authorizer = authorizerClazz.value().newInstance();
		} catch (Exception e) {
			throw new AuthorizerCreationException("Problem creating the authorizer: " + authorizerClazz.value().getName(), e);
		}
		return authorizer;
	}
}