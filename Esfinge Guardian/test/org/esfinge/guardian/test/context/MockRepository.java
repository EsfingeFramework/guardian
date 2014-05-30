package org.esfinge.guardian.test.context;

import java.util.HashSet;
import java.util.Set;

import org.esfinge.guardian.init.AuthorizationAnnotations;
import org.esfinge.guardian.init.Repository;
import org.esfinge.guardian.test.annotation.AlwaysAllowAnnotation;
import org.esfinge.guardian.test.annotation.AlwaysDenyAnnotation;

public class MockRepository {
	static public Repository createAlwaysAllowDenyAnnotationsRepository() {
		Set<String> set = new HashSet<String>();
		set.add( AlwaysAllowAnnotation.class.getName() );
		set.add( AlwaysDenyAnnotation.class.getName() );
		AuthorizationAnnotations authorizationAnnotations = new AuthorizationAnnotations(set);
		
		Repository repository = Repository.getInstance();
		repository.setAuthorizationAnnotations(authorizationAnnotations);
		return repository;
	}
}
