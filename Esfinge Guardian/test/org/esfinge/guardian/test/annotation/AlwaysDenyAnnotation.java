package org.esfinge.guardian.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;
import org.esfinge.guardian.test.authorizer.AlwaysDenyAuthorizer;

@AuthorizerClass(AlwaysDenyAuthorizer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AlwaysDenyAnnotation {
	String value() default "";
}
