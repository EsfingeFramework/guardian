package org.esfinge.guardian.utils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.init.AuthorizationAnnotations;
import org.esfinge.guardian.init.CachedAuthorizers;
import org.esfinge.guardian.init.Repository;

public class AuthorizerUtils {

//	static public boolean isEL(String value) {
//		return value.matches(ELUtils.EL_PATTERN);
//	}

	static public Set<Annotation> getAuthorizationAnnotations(AuthorizationContext context, Annotation[] annotations) {

		Repository repository = context.getRepository();

		if (Objects.isNull(repository.getAuthorizationAnnotations())) {
			Set<String> set = new HashSet<String>();
			for (Annotation ann : annotations)
				set.add(ann.annotationType().getName());
			repository.setAuthorizationAnnotations(new AuthorizationAnnotations(set));
		}

		if (Objects.isNull(repository.getCachedAuthorizers()))
			repository.setCachedAuthorizers(new CachedAuthorizers());

		return repository.getAuthorizationAnnotations().extractAuthorizationAnnotations(annotations,
				new HashSet<Annotation>());
	}

	static public Authorizer<? extends Annotation> getAuthorizer(AuthorizationContext context,
			Annotation authorizationAnnotation) {
		return context.getRepository().getCachedAuthorizers().getAuthorizer(context, context.getGuardedMethod(),
				authorizationAnnotation);
	}
}
