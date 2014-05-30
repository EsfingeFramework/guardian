package org.esfinge.guardian.mac.annotation.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;
import org.esfinge.guardian.mac.authorizer.SensitivityLevelAuthorizer;
import org.esfinge.guardian.mac.context.Level;

@AuthorizerClass(SensitivityLevelAuthorizer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
public @interface ObjectSensitivity {
	Level value();
}