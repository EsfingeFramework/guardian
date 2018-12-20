package org.esfinge.guardian.rbac.populator;

import java.util.HashSet;
import java.util.Set;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.populator.Populator;
import org.esfinge.guardian.rbac.annotation.context.DeclareRoles;
import org.esfinge.guardian.rbac.entity.Role;
import org.esfinge.guardian.rbac.utils.RbacConfig;

public class DeclareRolesPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {

		RbacConfig rbacConfig = new RbacConfig();
		Set<Role> declareRolesSet = new HashSet<Role>();

		Class<?> clazz = context.getGuardedObj().getClass();
		if (clazz.isAnnotationPresent(DeclareRoles.class)) {
			String[] roleNames = clazz.getAnnotation(DeclareRoles.class).value();
			for (String roleName : roleNames) {
				declareRolesSet.add(new Role(roleName));
			}

			context.getSubject().put(rbacConfig.getRolesKey(), declareRolesSet);
		}
	}
}