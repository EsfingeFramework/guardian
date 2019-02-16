package org.esfinge.guardian.rbac.tester;

import static org.junit.Assert.assertEquals;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AuthorizationException;
import org.esfinge.guardian.rbac.test.GuardedRBACClass;
import org.esfinge.guardian.rbac.test.GuardedRBACInterface;
import org.junit.Test;

public class RoleTester {
	
	@Test
	public void testAccessToProtectedResourceWithProperRole() {
		GuardedRBACInterface gc = AuthorizationContext.guardObject(new GuardedRBACClass());
		assertEquals("This method must always execute", true, gc.executableByAnyoneWithRoleAdministrator() );
		
	}
	
	@Test(expected=AuthorizationException.class)
	public void testAccessToProtectedResourceWithImproperRole() {
		GuardedRBACInterface gc = AuthorizationContext.guardObject(new GuardedRBACClass());
		
		gc.executableByAnyoneWithRoleManager();
	}
}