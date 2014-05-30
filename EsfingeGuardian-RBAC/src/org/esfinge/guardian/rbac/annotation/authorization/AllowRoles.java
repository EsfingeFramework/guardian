package org.esfinge.guardian.rbac.annotation.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;
import org.esfinge.guardian.rbac.authorizer.AllowRolesAuthorizer;

@AuthorizerClass(AllowRolesAuthorizer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface AllowRoles {
	String[] value();
}
