package org.esfinge.guardian.abac.test;

import org.esfinge.guardian.abac.annotation.authorization.Rule;
import org.esfinge.guardian.annotation.context.Subject;

public class GuardedABACClass implements GuardedABACInterface {
	
	@Subject("mySubjectAttribute")
	private String toBeUsedByTheSubject = "shouldBeInTheSubject";
	
	@Subject("myStaticSubjectAttribute")
	static private String staticAttribute = "shouldBeInTheSubject";
	
	@Override
	@Rule("rule_with_#{}")
	public void wrongRuleFormat() {
		System.err.println("this method must never execute");
	}
	
	@Override
	@Rule("2 > 3")
	public void execAlwaysFalseRule() {
		System.err.println("this method must never execute");
	}
	
	@Override
	@Rule("2 == 2")
	public boolean execAlwaysTrueRule() {
		return true;
	}
	
	@Override
	@Rule("clazz_key=='my_obj'")
	public boolean putIntoCtxByClassAnnotation() {
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
		GuardedABACClass.staticAttribute = staticAttribute;
	}

}