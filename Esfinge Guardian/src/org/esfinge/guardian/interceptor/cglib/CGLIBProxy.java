package org.esfinge.guardian.interceptor.cglib;

import java.lang.reflect.Method;

import org.esfinge.guardian.context.WrappedObj;
import org.esfinge.guardian.exception.ProxyCreationException;
import org.esfinge.guardian.interceptor.GuardianInterceptor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLIBProxy extends GuardianInterceptor implements MethodInterceptor {
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Exception {
		return guardianIntercept(obj, method, args);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <E> E createGuardedObject(E obj, Object[] constructorArgs, WrappedObj<?>... dynamicContext) {
		CGLIBProxy proxy = new CGLIBProxy();
		proxy.setDynamicContext(dynamicContext);
		proxy.setGuardedObject(obj);
		
		Enhancer e = new Enhancer();
		e.setSuperclass(obj.getClass());
		e.setCallback(proxy);
		
		E returnObj = null;
		try {
			if (constructorArgs == null) {
				returnObj = (E) e.create();
			} else {
				Class<?>[] argTypes = new Class<?>[constructorArgs.length];
				for (int i=0; i < constructorArgs.length; i++) {
					argTypes[i] = constructorArgs[i].getClass();
				}
				returnObj = (E) e.create(argTypes, constructorArgs);
			}
		} catch (Exception ex) {
			String msg = "Could not create proxy. Try checking the number of atributes in the constructor of: " + obj.getClass().getSimpleName();
			throw new ProxyCreationException(msg, ex);
		}
		return returnObj;
	}
} 