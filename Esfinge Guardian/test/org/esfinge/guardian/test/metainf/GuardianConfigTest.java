package org.esfinge.guardian.test.metainf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class GuardianConfigTest {

	@Test
	public void guardianConfigProperties() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/META-INF/GuardianConfig.properties"));
		assertNotNull(prop);
		assertEquals("subject", prop.getProperty("subject.key"));
	}
	
}
