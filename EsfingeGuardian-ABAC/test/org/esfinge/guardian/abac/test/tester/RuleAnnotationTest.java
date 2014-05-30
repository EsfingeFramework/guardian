package org.esfinge.guardian.abac.test.tester;

import static org.junit.Assert.assertEquals;

import org.esfinge.guardian.abac.exception.MalformedELException;
import org.esfinge.guardian.abac.test.GuardedABACClass;
import org.esfinge.guardian.abac.test.GuardedABACInterface;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AuthorizationException;
import org.junit.Test;


public class RuleAnnotationTest {
	
	@Test(expected=AuthorizationException.class)
	public void testSimpleAlwaysFalseRule() {
		GuardedABACInterface gc = AuthorizationContext.guardObject(new GuardedABACClass());
		gc.execAlwaysFalseRule();
	}
	
	@Test()
	public void testSimpleAlwaysTrueRule() {
		GuardedABACInterface gc = AuthorizationContext.guardObject(new GuardedABACClass());
		assertEquals("This method must always execute and return true", true, gc.execAlwaysTrueRule());
	}
	
	@Test(expected=MalformedELException.class)
	public void testELFormation() {
		GuardedABACInterface gc = AuthorizationContext.guardObject(new GuardedABACClass());
		gc.wrongRuleFormat();
	}
}
