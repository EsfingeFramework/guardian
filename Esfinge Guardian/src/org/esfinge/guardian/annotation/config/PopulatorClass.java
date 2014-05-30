package org.esfinge.guardian.annotation.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.populator.Populator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PopulatorClass {
	Class<? extends Populator> value();
}
