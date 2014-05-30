package org.esfinge.guardian.rbac.entity;

public class Role {
	private String roleName;
	
	public Role(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	final public String toString() {
		return roleName;
	}
	
	@Override
	final public boolean equals(Object o) {
		if (o != null && roleName != null && o instanceof Role) {
			return roleName.equals(((Role) o).getRoleName());
		}
		return false;
	}
	
	@Override
	final public int hashCode() {
		return roleName.toLowerCase().hashCode();
	}
	
	final public String getRoleName() {
		return roleName;
	}
	
	public boolean isSubjectInRole(Role role) {
		return this.equals(role);
	}
}