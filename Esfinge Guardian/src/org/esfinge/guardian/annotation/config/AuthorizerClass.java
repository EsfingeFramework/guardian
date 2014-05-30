package org.esfinge.guardian.annotation.config;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.authorizer.Authorizer;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface AuthorizerClass {
	Class<? extends Authorizer<? extends Annotation>> value();
}
