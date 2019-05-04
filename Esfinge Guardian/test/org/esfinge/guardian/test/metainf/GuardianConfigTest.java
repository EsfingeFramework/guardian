package org.esfinge.guardian.test.metainf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class GuardianConfigTest {

	@Test
	public void guardianConfigProperties() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		prop.load(classLoader.getResourceAsStream("META-INF/services/GuardianConfig.properties"));
		assertNotNull(prop);
		assertEquals("subject", prop.getProperty("subject.key"));
	}
	
}
