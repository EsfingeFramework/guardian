package org.esfinge.guardian.test.invoker;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AuthorizationException;
import org.esfinge.guardian.init.Repository;
import org.esfinge.guardian.invoker.Invoker;
import org.esfinge.guardian.test.GuardedClass;
import org.esfinge.guardian.test.GuardedInterface;
import org.esfinge.guardian.test.context.MockAuthorizationContext;
import org.esfinge.guardian.test.context.MockRepository;
import org.junit.Before;
import org.junit.Test;

public class InvokerTester {
	private Repository repository;
	private GuardedInterface gc;
	
	@Before
	public void setUp() {
		repository = MockRepository.createAlwaysAllowDenyAnnotationsRepository();
		gc = new GuardedClass();
	}
	
	@Test
	public void alwaysExecutedMethodTest() throws Exception {
		Method guardedMethod = gc.getClass().getMethod("alwaysExecutedMethod");
		
		AuthorizationContext context = MockAuthorizationContext.createMock(gc, guardedMethod, repository);
		
		Invoker invoker = context.getInvoker();
		String result = (String) invoker.invoke(context);
		assertEquals("This result should be OK", "OK", result);
	}
	
	@Test(expected=AuthorizationException.class)
	public void neverExecutedMethodTest() throws Exception {
		Method guardedMethod = gc.getClass().getMethod("neverExecutedMethod");
		
		AuthorizationContext context = MockAuthorizationContext.createMock(gc, guardedMethod, repository);
		
		Invoker invoker = context.getInvoker();
		invoker.invoke(context);
	}
}
