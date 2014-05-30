package org.esfinge.guardian.rbac.test;

import org.esfinge.guardian.rbac.annotation.authorization.AllowRoles;

public interface GuardedRBACInterface {

	@AllowRoles("Administrator")
	abstract boolean executableByAnyoneWithRoleAdministrator();

	@AllowRoles("Manager")
	abstract boolean executableByAnyoneWithRoleManager();
	
	String getToBeUsedByTheSubject();
	
	void setToBeUsedByTheSubject(String toBeUsedByTheSubject);
	
}