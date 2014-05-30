package org.esfinge.guardian.init;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.utils.AuthorizerFactory;

public class CachedAuthorizers {
	
	private Map<Method, Map<Class<? extends Annotation>, Authorizer<? extends Annotation>>> cachedAuthorizersMap = 
										new HashMap<Method, Map<Class<? extends Annotation>, Authorizer<? extends Annotation>>>();
	
	public CachedAuthorizers() { }
	
	private void addMethodEntry(Method method, Annotation annotation, Authorizer<? extends Annotation> authorizer) {
		if (!cachedAuthorizersMap.containsKey(method)) {
			Map<Class<? extends Annotation>, Authorizer<? extends Annotation>> innerMap = 
						new HashMap<Class<? extends Annotation>, Authorizer<? extends Annotation>>();
			innerMap.put(annotation.annotationType(), authorizer);
			cachedAuthorizersMap.put(method, innerMap);
		}
	}
	
	private void addAnnotationEntry(Method method, Annotation annotation, Authorizer<? extends Annotation> authorizer) {
		if (!cachedAuthorizersMap.containsKey(method)) {
			Map<Class<? extends Annotation>, Authorizer<? extends Annotation>> innerMap = cachedAuthorizersMap.get(method);
			if (!innerMap.isEmpty()) {
				innerMap.put(annotation.annotationType(), authorizer);
			}
		}
	}
	
	public Authorizer<? extends Annotation> getAuthorizer(AuthorizationContext context, Method method, Annotation annotation) {
		Authorizer<? extends Annotation> authorizer = null;
		
		if (cachedAuthorizersMap.containsKey( method )) {
			Map<Class<? extends Annotation>, Authorizer<? extends Annotation>> innerMap = cachedAuthorizersMap.get(method);
			
			if (innerMap.containsKey(annotation.annotationType())) {
				authorizer = innerMap.get( annotation.annotationType() );
				
			} else {
				authorizer = AuthorizerFactory.createAuthorizer( context, annotation.annotationType() );
				addAnnotationEntry(method, annotation, authorizer);
			}
			
		} else {
			authorizer = AuthorizerFactory.createAuthorizer( context, annotation.annotationType() );
			addMethodEntry(method, annotation, authorizer);
		}
		
		return authorizer;
	}
}