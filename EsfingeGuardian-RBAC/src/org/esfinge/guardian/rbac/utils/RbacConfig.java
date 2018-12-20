package org.esfinge.guardian.rbac.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class RbacConfig {

	private Properties prop;

	public RbacConfig() {
		prop = loadProperties();
	}

	public String getRolesKey() {
		return prop.getProperty("roles.key");
	}

	public String getDeclareRolesKey() {
		return prop.getProperty("declareRoles.key");
	}

	private Properties loadProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				prop.load(new FileInputStream("src/META-INF/RbacConfig.properties"));
			} catch (IOException e) {
				Logger.getLogger(this.getClass().getName(), "GuardianConfig could not be loaded: " + e);
			}
		}
		return prop;
	}
}
