package org.esfinge.guardian.utils;

import java.util.ResourceBundle;

public class GuardianConfig {
	static public ResourceBundle bundle = ResourceBundle.getBundle("META-INF/GuardianConfig");
	
	static public String getSubjectKey() {
		return bundle.getString("subject.key");
	}
	
	static public String getEnvironmentKey() {
		return bundle.getString("environment.key");
	}
	
	static public String getResourceKey() {
		return bundle.getString("resource.key");
	}
	
	static public String getKey(String key) {
		return bundle.getString(key);
	}
}
