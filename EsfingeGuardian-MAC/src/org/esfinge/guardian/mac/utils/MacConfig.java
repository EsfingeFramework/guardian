package org.esfinge.guardian.mac.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class MacConfig {
	
	private Properties prop;

	public MacConfig() {
		prop = loadProperties();
	}

	public String getAuthorizationLevelKey() {
		return prop.getProperty("authorizationLevel.key");
	}

	private Properties loadProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				prop.load(classLoader.getResourceAsStream("META-INF/services/MacConfig.properties"));
			} catch (IOException e) {
				Logger.getLogger(this.getClass().getName(), "MacConfig could not be loaded: " + e);
			}
		}
		return prop;
	}

}
