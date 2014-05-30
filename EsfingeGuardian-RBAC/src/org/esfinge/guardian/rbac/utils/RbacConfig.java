package org.esfinge.guardian.rbac.utils;

import java.util.ResourceBundle;

public class RbacConfig {

	public static ResourceBundle bundle = ResourceBundle.getBundle("META-INF/RbacConfig");
	
	static public String getRolesKey() {
		return bundle.getString("roles.key");
	}
	
	static public String getDeclareRolesKey() {
		return bundle.getString("declareRoles.key");
	}
}
