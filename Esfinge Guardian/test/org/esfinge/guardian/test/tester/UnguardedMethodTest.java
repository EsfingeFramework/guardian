package org.esfinge.guardian.test.tester;

import static org.junit.Assert.*;
import org.junit.Test;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.test.GuardedClass;
import org.esfinge.guardian.test.GuardedInterface;

public class UnguardedMethodTest {
	
	@Test
	public void unguardedMethodTest() {
		GuardedInterface gc = AuthorizationContext.guardObject(new GuardedClass());
		assertEquals("This method must always execute", "Executed", gc.unguardedMethod());
	}
}
