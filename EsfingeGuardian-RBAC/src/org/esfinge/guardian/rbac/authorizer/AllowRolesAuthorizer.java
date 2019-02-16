package org.esfinge.guardian.rbac.authorizer;

import java.util.HashSet;
import java.util.Set;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.rbac.annotation.authorization.AllowRoles;
import org.esfinge.guardian.rbac.entity.Role;
import org.esfinge.guardian.rbac.exception.RbacMisuseException;
import org.esfinge.guardian.rbac.utils.RbacConfig;

public class AllowRolesAuthorizer implements Authorizer<AllowRoles> {
	
	@Override
	public Boolean authorize(AuthorizationContext context, AllowRoles allowRoles) {
		
		RbacConfig rbacConfig = new RbacConfig();
		Set<Role> subjectRoles = context.getSubject().get(rbacConfig.getRolesKey(), new HashSet<Role>());
		Set<Role> annotatedRoles = new HashSet<Role>();
		
		String[] annotatedRolesNames = allowRoles.value();
		for (String roleName : annotatedRolesNames) {
			annotatedRoles.add(new Role(roleName));
		}
		
		if (annotatedRoles.isEmpty() && !subjectRoles.isEmpty()) {
			throw new RbacMisuseException("A role must be defined to access the method: " + context.getGuardedMethod().getName());
		}
		
		boolean authorized = false;
		for (Role annotatedRole : annotatedRoles) {
			for (Role subjectRole : subjectRoles) {
				authorized = authorized || annotatedRole.isSubjectInRole(subjectRole);
			}
		}
		
		return authorized;
	}
}