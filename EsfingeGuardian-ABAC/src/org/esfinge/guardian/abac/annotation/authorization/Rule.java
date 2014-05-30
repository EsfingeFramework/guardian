package org.esfinge.guardian.abac.annotation.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.abac.authorizer.RuleAuthorizer;
import org.esfinge.guardian.annotation.config.AuthorizerClass;

@AuthorizerClass(RuleAuthorizer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Rule {
	String value();
}
