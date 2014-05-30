package org.esfinge.guardian.init;


public class Repository {
	private AuthorizationAnnotations authorizationAnnotations;
	private CachedAuthorizers cachedAuthorizers;
	private ClasspathAnnotations classpathAnnotations;
	
	static private Repository instance;
	
	private Repository() {
	}
	
	static public Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}
	
	/*
	 * SETTERS AND GETTERS
	 */
	
	public ClasspathAnnotations getCachedClasspathAnnotations() {
		return classpathAnnotations;
	}
	public void setCachedClasspathAnnotations(
			ClasspathAnnotations classpathAnnotations) {
		this.classpathAnnotations = classpathAnnotations;
	}
	public AuthorizationAnnotations getAuthorizationAnnotations() {
		return authorizationAnnotations;
	}
	public void setAuthorizationAnnotations(
			AuthorizationAnnotations authorizationAnnotations) {
		this.authorizationAnnotations = authorizationAnnotations;
	}
	public CachedAuthorizers getCachedAuthorizers() {
		return cachedAuthorizers;
	}
	public void setCachedAuthorizers(CachedAuthorizers cachedAuthorizers) {
		this.cachedAuthorizers = cachedAuthorizers;
	}
}