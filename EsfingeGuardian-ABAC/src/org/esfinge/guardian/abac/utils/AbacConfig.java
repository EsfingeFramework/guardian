package org.esfinge.guardian.abac.utils;

import java.util.ResourceBundle;

public class AbacConfig {
	static public ResourceBundle bundle = ResourceBundle.getBundle("META-INF/AbacConfig");
	
	static public String getSubjectKey() {
		return bundle.getString("subject.key");
	}
	
	static public String getEnvironmentKey() {
		return bundle.getString("environment.key");
	}
	
	static public String getResourceKey() {
		return bundle.getString("resource.key");
	}
}
