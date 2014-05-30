package org.esfinge.guardian.mac.utils;

import java.util.ResourceBundle;

public class MacConfig {

	public static ResourceBundle bundle = ResourceBundle.getBundle("META-INF/MacConfig");
	
	static public String getAuthorizationLevelKey() {
		return bundle.getString("authorizationLevel.key");
	}
}
