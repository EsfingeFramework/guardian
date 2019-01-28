package org.esfinge.guardian.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class GuardianConfig {

	// static public ResourceBundle bundle =
	// ResourceBundle.getBundle("META-INF/GuardianConfig");
	static private Properties prop;

	public GuardianConfig() {
		prop = loadProperties();
	}
	
	public String getSubjectKey() {
		return prop.getProperty("subject.key");
	}

	public String getEnvironmentKey() {
		return prop.getProperty("environment.key");
	}

	public String getResourceKey() {
		return prop.getProperty("resource.key");
	}

	public String getKey(String key) {
		return prop.getProperty(key);
	}

	private Properties loadProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				prop.load(new FileInputStream("src/META-INF/services/GuardianConfig.properties"));
			} catch (IOException e) {
				Logger.getLogger(this.getClass().getName(), "GuardianConfig could not be loaded: " + e);
			}
		}
		return prop;
	}
}
