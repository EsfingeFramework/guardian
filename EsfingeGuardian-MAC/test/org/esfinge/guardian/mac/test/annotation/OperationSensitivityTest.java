package org.esfinge.guardian.mac.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;
import org.esfinge.guardian.mac.authorizer.SensitivityLevelAuthorizer;
import org.esfinge.guardian.mac.test.Nivel;

@AuthorizerClass(SensitivityLevelAuthorizer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationSensitivityTest {
	Nivel value();
}
