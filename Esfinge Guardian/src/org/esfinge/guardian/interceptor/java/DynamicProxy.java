package org.esfinge.guardian.interceptor.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.esfinge.guardian.context.WrappedObj;
import org.esfinge.guardian.exception.ProxyCreationException;
import org.esfinge.guardian.interceptor.GuardianInterceptor;


public class DynamicProxy extends GuardianInterceptor implements InvocationHandler {
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return guardianIntercept(getGuardedObject(), method, args);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <E> E createGuardedObject(E obj, Object[] args, WrappedObj<?>... dynamicContext) {
		Class<?>[] objInterfaces = obj.getClass().getInterfaces();
		if (objInterfaces.length == 0) {
			String msg = "In order to use this proxy, the class " + obj.getClass() + "needs to implement at least one interface";
			throw new ProxyCreationException(msg);
		}
		
		DynamicProxy dynaProxy = new DynamicProxy();
		dynaProxy.setGuardedObject(obj);
		dynaProxy.setDynamicContext(dynamicContext);
		
		E proxy = (E) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                objInterfaces,
                dynaProxy);
		
		return proxy;
	}
}