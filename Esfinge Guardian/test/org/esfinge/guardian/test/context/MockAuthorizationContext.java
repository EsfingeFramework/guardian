package org.esfinge.guardian.test.context;

import java.lang.reflect.Method;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.init.CachedAuthorizers;
import org.esfinge.guardian.init.Repository;
import org.esfinge.guardian.invoker.Invoker;
import org.esfinge.guardian.test.GuardedInterface;
import org.esfinge.guardian.utils.ServiceLocator;

public class MockAuthorizationContext {
	
	static public AuthorizationContext createMock(GuardedInterface gc, Method guardedMethod, Repository repository) {
		
		AuthorizationContext context = new AuthorizationContext(gc, guardedMethod, null);
		
		Invoker invoker = 
				ServiceLocator.getServiceImplementation(Invoker.class);
		
		context.setGuardedObj(gc);
		context.setGuardedMethod(guardedMethod);
		context.setInvoker(invoker);
		context.setRepository(repository);
		context.getRepository().setCachedAuthorizers(new CachedAuthorizers());
		return context;
	}
}