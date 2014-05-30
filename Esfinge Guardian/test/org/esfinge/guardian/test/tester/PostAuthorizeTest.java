package org.esfinge.guardian.test.tester;

import static org.junit.Assert.assertEquals;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AuthorizationException;
import org.esfinge.guardian.test.GuardedClass;
import org.esfinge.guardian.test.GuardedInterface;
import org.junit.Test;

public class PostAuthorizeTest {
	
	@Test(expected=AuthorizationException.class)
	public void testPostUnauthorization() {
		GuardedInterface gc = AuthorizationContext.guardObject(new GuardedClass());
		gc.neverExecutedButPostUnauthorizedMethod();
	}
	
	@Test
	public void testPostAuthorization() {
		GuardedInterface gc = AuthorizationContext.guardObject(new GuardedClass());
		assertEquals("This method must always execute and return true", 
							true, 
							gc.alwaysExecutedButPostAuthorizedMethod());
	}
}
