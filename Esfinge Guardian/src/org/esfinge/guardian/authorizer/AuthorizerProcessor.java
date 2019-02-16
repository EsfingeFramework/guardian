package org.esfinge.guardian.authorizer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AuthorizationException;
import org.esfinge.guardian.populator.PopulatorProcessor;
import org.esfinge.guardian.utils.AuthorizerUtils;

public class AuthorizerProcessor {
	
	@SuppressWarnings("unchecked")
	static public void process(AuthorizationContext context) {
		PopulatorProcessor.process(context);
			
		Method guardedMethod = context.getGuardedMethod();
		
		Set<Annotation> authorizationAnnotations = 
							AuthorizerUtils.getAuthorizationAnnotations(
									context, guardedMethod.getAnnotations()); 
		
		boolean authorized = true;
		if ( !authorizationAnnotations.isEmpty() ) {
			
			for (Annotation annotation : authorizationAnnotations) {
				
				@SuppressWarnings("rawtypes")
				Authorizer authorizer = AuthorizerUtils.getAuthorizer(context, annotation);
				
				authorized = (authorized && authorizer.authorize(context, annotation));
				
				if (!authorized) break;
			}
		}
		
		if (!authorized) {
			throw new AuthorizationException("Unauthorized Access");
		}
	}
}