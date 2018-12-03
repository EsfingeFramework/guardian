package org.esfinge.guardian.rbac.authorizer;

import java.util.ArrayList;
import java.util.List;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.rbac.annotation.authorization.DenyRoles;
import org.esfinge.guardian.rbac.utils.RbacConfig;

public class DenyRolesAuthorizer implements Authorizer<DenyRoles> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyRoles denyRoles) {

		RbacConfig rbacConfig = new RbacConfig();
		List<String> rolesList = context.getSubject().get(rbacConfig.getRolesKey(), new ArrayList<String>());

		String annotatedRole = denyRoles.value();

		// TODO: handle situations when more than one role is annotated
		return !rolesList.contains(annotatedRole);
	}
}