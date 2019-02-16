package org.esfinge.guardian.invoker.core;

import java.lang.reflect.Method;

import org.esfinge.guardian.annotation.authorization.PostAuthorize;
import org.esfinge.guardian.authorizer.AuthorizerProcessor;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.invoker.Invoker;
import org.esfinge.guardian.utils.GuardianConfig;

public class CoreMethodInvoker extends Invoker {
	
	private GuardianConfig guardianConfig = new GuardianConfig();
	private String returnedObjKey = guardianConfig.getKey("returnedObj.key");
	
	@Override
	protected void preInvoke(AuthorizationContext context) throws Exception {
		Method method = context.getGuardedMethod();
		if ( !method.isAnnotationPresent(PostAuthorize.class) ) {
			AuthorizerProcessor.process(context);
		}
	}

	@Override
	protected Object postInvoke(AuthorizationContext context, Object returnedObj) throws Exception {
		Method method = context.getGuardedMethod();
		if ( method.isAnnotationPresent(PostAuthorize.class) ) {
			if (returnedObj != null) {
				context.getResource().put(returnedObjKey, returnedObj);
			}
			AuthorizerProcessor.process(context);
		}
		return returnedObj;
	}
}