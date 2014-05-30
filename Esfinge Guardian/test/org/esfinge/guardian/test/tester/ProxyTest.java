package org.esfinge.guardian.test.tester;

import static org.junit.Assert.assertEquals;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.ProxyCreationException;
import org.esfinge.guardian.interceptor.GuardianInterceptor;
import org.esfinge.guardian.interceptor.cglib.CGLIBProxy;
import org.esfinge.guardian.interceptor.java.DynamicProxy;
import org.esfinge.guardian.test.ClassWithNoDefaultConstructor;
import org.esfinge.guardian.test.GuardedClass;
import org.esfinge.guardian.test.GuardedRootClass;
import org.junit.Test;
public class ProxyTest {
	
	@Test(expected=ProxyCreationException.class)
	public void dynamicProxyWithClassWithoutInterfaceTest() throws Exception {
		GuardianInterceptor gi = new DynamicProxy();
		@SuppressWarnings("unused")
		GuardedRootClass gc = AuthorizationContext.guardObject(gi, new GuardedRootClass(), null);
	}
	
	@Test
	public void cglibProxyWithClassWithoutInterfaceTest() throws Exception {
		GuardianInterceptor gi = new CGLIBProxy();
		GuardedRootClass gc = AuthorizationContext.guardObject(gi, new GuardedRootClass(), null);
		assertEquals("Should be true", gc.methodOfAClassWithNoInterfaceImpl(), true);
	}
	
	@Test
	public void cglibProxyWithClassWithNoDefaultConstructorTest() throws Exception {
		GuardianInterceptor gi = new CGLIBProxy();
		GuardedClass gc = new GuardedClass();
		Object[] constructorArgs = { gc };
		ClassWithNoDefaultConstructor clazz = 
					AuthorizationContext.guardObject(
											gi, 
											new ClassWithNoDefaultConstructor(gc), 
											constructorArgs );
		
		assertEquals("Should be true", clazz.getName(), true);
	}
	
	
}