package org.esfinge.guardian.test;

import org.esfinge.guardian.annotation.authorization.PostAuthorize;
import org.esfinge.guardian.annotation.context.Subject;
import org.esfinge.guardian.test.annotation.AlwaysAllowAnnotation;
import org.esfinge.guardian.test.annotation.AlwaysDenyAnnotation;

public class GuardedClass implements GuardedInterface {
	
	@Subject("mySubjectAttribute")
	private String toBeUsedByTheSubject = "shouldBeInTheSubject";
	
	@Subject("myStaticSubjectAttribute")
	static private String staticAttribute = "shouldBeInTheSubject";
	
	@Override
	public String unguardedMethod() {
		return "Executed";
	}
	
	@AlwaysAllowAnnotation
	@Override
	public String alwaysExecutedMethod() {
		return "OK";
	}
	
	@AlwaysDenyAnnotation
	@Override
	public String neverExecutedMethod() {
		return "This string can never be read";
	}
	
	@AlwaysDenyAnnotation @PostAuthorize
	@Override
	public void neverExecutedButPostUnauthorizedMethod() {
		return;
	}
	
	@AlwaysAllowAnnotation @PostAuthorize
	@Override
	public boolean alwaysExecutedButPostAuthorizedMethod() {
		return true;
	}
	
	/* SETTERS AND GETTERS */
	
	@Override
	public String getToBeUsedByTheSubject() {
		return toBeUsedByTheSubject;
	}

	@Override
	public void setToBeUsedByTheSubject(String toBeUsedByTheSubject) {
		this.toBeUsedByTheSubject = toBeUsedByTheSubject;
	}

	public static String getStaticAttribute() {
		return staticAttribute;
	}

	public static void setStaticAttribute(String staticAttribute) {
		GuardedClass.staticAttribute = staticAttribute;
	}

}