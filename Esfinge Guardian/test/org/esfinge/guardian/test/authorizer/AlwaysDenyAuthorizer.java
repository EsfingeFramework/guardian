package org.esfinge.guardian.test.authorizer;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.test.annotation.AlwaysDenyAnnotation;

public class AlwaysDenyAuthorizer implements Authorizer<AlwaysDenyAnnotation> {

	@Override
	public Boolean authorize(AuthorizationContext context, AlwaysDenyAnnotation securityAnnotation) {
		return false;
	}
}