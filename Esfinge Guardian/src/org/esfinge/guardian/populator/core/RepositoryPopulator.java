package org.esfinge.guardian.populator.core;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.esfinge.guardian.annotation.config.AuthorizerClass;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.init.AuthorizationAnnotations;
import org.esfinge.guardian.init.CachedAuthorizers;
import org.esfinge.guardian.init.ClasspathAnnotations;
import org.esfinge.guardian.init.Repository;
import org.esfinge.guardian.populator.Populator;

public class RepositoryPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {
		Repository repository = context.getRepository();
		
		repository.setCachedAuthorizers( new CachedAuthorizers() );
		
		ClasspathAnnotations classpathAnnotations = ClasspathAnnotations.getInstance(context);
		repository.setCachedClasspathAnnotations( classpathAnnotations );
		
		Map<String, Set<String>> allAnnotationsMap = classpathAnnotations.getClasspathAnnotations();
		Set<String> authorizationAnnotationsSet = allAnnotationsMap.get(AuthorizerClass.class.getName());
		if (authorizationAnnotationsSet == null) {
			authorizationAnnotationsSet = new HashSet<String>();
		}
		repository.setAuthorizationAnnotations(new AuthorizationAnnotations(authorizationAnnotationsSet));
	}
}