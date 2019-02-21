package org.esfinge.guardian.abac.test.tester;

import static org.junit.Assert.assertTrue;

import org.esfinge.guardian.abac.el.ELUtils;
import org.junit.Test;

public class ELPatternTest {
	String chars = "0123456789qwertyuiopasdfghjkl�zxcvbnm!.=><+-*/~;:|\\";
	
	@Test
	public void testELMatching() {
		String el = "#{" + chars + "}";
		assertTrue(el.matches(ELUtils.EL_PATTERN));
	}
}
