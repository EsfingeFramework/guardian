package org.esfinge.guardian.test;

import org.esfinge.guardian.annotation.authorization.PostAuthorize;
import org.esfinge.guardian.test.annotation.AlwaysAllowAnnotation;
import org.esfinge.guardian.test.annotation.AlwaysDenyAnnotation;


public interface GuardedInterface {
	
	abstract String unguardedMethod();
	
	@AlwaysAllowAnnotation
	abstract String alwaysExecutedMethod();
	
	@AlwaysDenyAnnotation
	abstract String neverExecutedMethod();
	
	@AlwaysAllowAnnotation @PostAuthorize
	abstract boolean alwaysExecutedButPostAuthorizedMethod();
	
	@AlwaysDenyAnnotation @PostAuthorize
	abstract void neverExecutedButPostUnauthorizedMethod();
	
	void setToBeUsedByTheSubject(String toBeUsedByTheSubject);
	
	String getToBeUsedByTheSubject();
}