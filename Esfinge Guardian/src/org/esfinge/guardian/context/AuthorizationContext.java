package org.esfinge.guardian.context;

import java.lang.reflect.Method;

import org.esfinge.guardian.context.entity.ContextMap;
import org.esfinge.guardian.context.entity.ContextType;
import org.esfinge.guardian.exception.ProxyCreationException;
import org.esfinge.guardian.init.Repository;
import org.esfinge.guardian.interceptor.GuardianInterceptor;
import org.esfinge.guardian.invoker.Invoker;
import org.esfinge.guardian.utils.ServiceLocator;

public class AuthorizationContext {

	private Object guardedObj;
	private Method guardedMethod;
	private Object[] guardedMethodArgs;
	private ContextMap subject;
	private ContextMap environment;
	private ContextMap resource;

	private Invoker invoker;
	private Repository repository;
	private WrappedObj<?>[] wrappedObjs;

	public AuthorizationContext() { }

	public AuthorizationContext(Object guardedObj, Method guardedMethod, Object[] guardedMethodArgs) {
		this.guardedObj = guardedObj;
		this.guardedMethod = guardedMethod;
		this.guardedMethodArgs = guardedMethodArgs;

		this.subject = new ContextMap();
		this.environment = new ContextMap();
		this.resource = new ContextMap();

		this.invoker = ServiceLocator.getServiceImplementation(Invoker.class);
		this.repository = Repository.getInstance();
	}

	public AuthorizationContext(Object guardedObj, Method guardedMethod, Object[] guardedMethodArgs,
			WrappedObj<?>... wrappedObjs) {
		this(guardedObj, guardedMethod, guardedMethodArgs);
		this.wrappedObjs = wrappedObjs;
	}

	/**
	 * Used to guard objects. Objects will be instantiated using default constructor
	 * 
	 * @param object
	 * @param wrappedObjs
	 * @return
	 */
	static public <E> E guardObject(E object, WrappedObj<?>... wrappedObjs) {
		GuardianInterceptor interceptor = ServiceLocator.getServiceImplementation(GuardianInterceptor.class);
		return AuthorizationContext.guardObject(interceptor, object, null, wrappedObjs);
	}

	/**
	 * Used to guard objects, with the constructorArgs param indicating the params
	 * to be used in the construction of the guarded obj
	 * 
	 * @param object
	 * @param wrappedObjs
	 * @return
	 */
	static public <E> E guardObject(E object, Object[] constructorArgs, WrappedObj<?>... wrappedObjs) {
		GuardianInterceptor interceptor = ServiceLocator.getServiceImplementation(GuardianInterceptor.class);
		return AuthorizationContext.guardObject(interceptor, object, constructorArgs, wrappedObjs);
	}

	/**
	 * Used to guard objects with a specific proxy
	 * 
	 * @param interceptor
	 * @param object
	 * @param wrappedObjs
	 * @return
	 */
	static public <E> E guardObject(GuardianInterceptor interceptor, E object, Object[] constructorArgs,
			WrappedObj<?>... wrappedObjs) {
		E guardedObj = null;
		try {
			guardedObj = (E) interceptor.createGuardedObject(object, constructorArgs, wrappedObjs);
		} catch (Exception e) {
			throw new ProxyCreationException("Could not create proxy", e);
		}
		return guardedObj;
	}

	/**
	 * Wrap the runtime object to be used in the context as a resource property
	 * 
	 * @param contextName the name used to retrieve the runtime object from the
	 *                    resource map
	 * @param wrappedObj  to be used when requesting to guard an object
	 * @return
	 */
	static public <E> WrappedObj<E> wrapAsResourceProp(String contextName, E wrappedObj) {
		return new WrappedObj<E>(contextName, wrappedObj, ContextType.RESOURCE);
	}

	/**
	 * Wrap the runtime object to be used in the context as a subject property
	 * 
	 * @param contextName the name used to retrieve the runtime object from the
	 *                    subject map
	 * @param wrappedObj  to be used when requesting to guard an object
	 * @return
	 */
	static public <E> WrappedObj<E> wrapAsSubjectProp(String contextName, E wrappedObj) {
		return new WrappedObj<E>(contextName, wrappedObj, ContextType.SUBJECT);
	}

	/**
	 * Wrap the runtime object to be used in the context as a environment property
	 * 
	 * @param contextName the name used to retrieve the runtime object from the
	 *                    environment map
	 * @param wrappedObj  to be used when requesting to guard an object
	 * @return
	 */
	static public <E> WrappedObj<E> wrapAsEnvironmentProp(String contextName, E wrappedObj) {
		return new WrappedObj<E>(contextName, wrappedObj, ContextType.ENVIRONMENT);
	}

	static public void main(String[] args) {
		// Do nothing this way
	}

	/*
	 * SETTERS AND GETTERS
	 */

	public Invoker getInvoker() {
		return invoker;
	}

	public void setInvoker(Invoker invoker) {
		this.invoker = invoker;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository authorizationRepository) {
		this.repository = authorizationRepository;
	}

	public Object getGuardedObj() {
		return guardedObj;
	}

	public void setGuardedObj(Object guardedObj) {
		this.guardedObj = guardedObj;
	}

	public Method getGuardedMethod() {
		return guardedMethod;
	}

	public void setGuardedMethod(Method guardedMethod) {
		this.guardedMethod = guardedMethod;
	}

	public Object[] getGuardedMethodArgs() {
		return guardedMethodArgs;
	}

	public void setGuardedMethodArgs(Object[] guardedMethodArgs) {
		this.guardedMethodArgs = guardedMethodArgs;
	}

	public ContextMap getSubject() {
		return subject;
	}

	public void setSubject(ContextMap subject) {
		this.subject = subject;
	}

	public ContextMap getEnvironment() {
		return environment;
	}

	public void setEnvironment(ContextMap environment) {
		this.environment = environment;
	}

	public ContextMap getResource() {
		return resource;
	}

	public void setResource(ContextMap resource) {
		this.resource = resource;
	}

	public WrappedObj<?>[] getWrappedObjs() {
		return wrappedObjs;
	}

	public void setWrappedObjs(WrappedObj<?>[] wrappedObjs) {
		this.wrappedObjs = wrappedObjs;
	}
}