package org.esfinge.guardian.abac.test;

import org.esfinge.guardian.abac.annotation.authorization.Rule;


public interface GuardedABACInterface {
	
	@Rule("clazz_key=='my_obj'")
	abstract boolean putIntoCtxByClassAnnotation();
	
	@Rule("2 == 2")
	abstract boolean execAlwaysTrueRule();
	
	@Rule("2 > 3")
	abstract void execAlwaysFalseRule();
	
	@Rule("rule_with_#{}")
	abstract void wrongRuleFormat();
	
	String getToBeUsedByTheSubject();
	
	void setToBeUsedByTheSubject(String toBeUsedByTheSubject);
}