package org.esfinge.guardian.rbac.test;

import org.esfinge.guardian.annotation.context.Subject;
import org.esfinge.guardian.rbac.annotation.authorization.AllowRoles;
import org.esfinge.guardian.rbac.annotation.context.DeclareRoles;

@DeclareRoles({"Administrator","Director"})
public class GuardedRBACClass implements GuardedRBACInterface {
	
	@Subject("mySubjectAttribute")
	private String toBeUsedByTheSubject = "shouldBeInTheSubject";
	
	@Subject("myStaticSubjectAttribute")
	static private String staticAttribute = "shouldBeInTheSubject";
	
	@Override
	@AllowRoles("Administrator")
	public boolean executableByAnyoneWithRoleAdministrator() {
		return true;
	}
	
	@Override
	@AllowRoles("Manager")
	public boolean executableByAnyoneWithRoleManager() {
		return true;
	}
	
	/* SETTERS AND GETTERS */
	
	@Override
	public String getToBeUsedByTheSubject() {
		return toBeUsedByTheSubject;
	}

	@Override
	public void setToBeUsedByTheSubject(String toBeUsedByTheSubject) {
		this.toBeUsedByTheSubject = toBeUsedByTheSubject;
	}

	public static String getStaticAttribute() {
		return staticAttribute;
	}

	public static void setStaticAttribute(String staticAttribute) {
		GuardedRBACClass.staticAttribute = staticAttribute;
	}

}