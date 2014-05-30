package org.esfinge.guardian.interceptor;

import java.lang.reflect.Method;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.context.WrappedObj;
import org.esfinge.guardian.invoker.Invoker;

public abstract class GuardianInterceptor {
	private Object guardedObject;
	private WrappedObj<?>[] dynamicContext;
	
	public abstract <E> E createGuardedObject(E obj, Object[] args, WrappedObj<?>... dynamicContext);
	
	public Object guardianIntercept(Object obj, Method method, Object[] args) throws Exception {
		AuthorizationContext context = new AuthorizationContext(getGuardedObject(), method, args, getDynamicContext());
		
		Invoker invoker = context.getInvoker();
		return invoker.invoke(context);
	}
	
	/* SETTERS AND GETTERS */
	
	public Object getGuardedObject() {
		return guardedObject;
	}
	public void setGuardedObject(Object guardedObject) {
		this.guardedObject = guardedObject;
	}
	public WrappedObj<?>[] getDynamicContext() {
		return dynamicContext;
	}
	public void setDynamicContext(WrappedObj<?>[] dynamicContext) {
		this.dynamicContext = dynamicContext;
	}
}