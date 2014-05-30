package org.esfinge.guardian.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.esfinge.guardian.context.AuthorizationContext;

public abstract class Invoker {
	
	abstract protected void preInvoke(AuthorizationContext context) throws Exception;
	abstract protected Object postInvoke(AuthorizationContext context, Object returnedObj) throws Exception;
	
	final public Object invoke(AuthorizationContext context) throws Exception {
		preInvoke(context);
		
		Object guardedObj = context.getGuardedObj();
		Method guardedMethod = context.getGuardedMethod();
		Object[] guardedMethodArgs = context.getGuardedMethodArgs();
		
		Object returnedObj = null;
		try {
			returnedObj = guardedMethod.invoke(guardedObj, guardedMethodArgs);
		} catch(InvocationTargetException e) {
			throw (Exception) e.getTargetException();
		}
		return postInvoke(context, returnedObj);
	}

}
