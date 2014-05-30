package org.esfinge.guardian.test.authorizer;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.test.annotation.AlwaysAllowAnnotation;

public class AlwaysAllowAuthorizer implements Authorizer<AlwaysAllowAnnotation> {

	@Override
	public Boolean authorize(AuthorizationContext context, AlwaysAllowAnnotation securityAnnotation) {
		return true;
	}
}