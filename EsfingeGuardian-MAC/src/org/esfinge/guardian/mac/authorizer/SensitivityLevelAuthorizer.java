package org.esfinge.guardian.mac.authorizer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.mac.exception.EnumNotDefinedException;
import org.esfinge.guardian.mac.exception.WrongFormatSensitivityLevelException;
import org.esfinge.guardian.mac.utils.MacConfig;
import org.esfinge.guardian.utils.AnnotationUtils;

public class SensitivityLevelAuthorizer implements Authorizer<Annotation> {

	@Override
	public Boolean authorize(AuthorizationContext context, Annotation sensitivityLevel) {
		Class<? extends Annotation> sensitivityType = sensitivityLevel.annotationType();
		MacConfig config = new MacConfig();
		Method value = null;
		try {
			value = sensitivityType.getMethod("value");
		} catch (Exception e) {
			throw new WrongFormatSensitivityLevelException("Please revise the format of " + sensitivityType);
		}
		
		Class<?> enumClass = value.getReturnType();
		if (!enumClass.isEnum()) {
			throw new EnumNotDefinedException(sensitivityType.getName() + " must have an enum property defined as value()");
		}
		
		Enum<?> subjectAuthorizationLevel = 
				context.
					getSubject().
						get(config.getAuthorizationLevelKey(), Enum.class);
		
		if (subjectAuthorizationLevel == null) {
			throw new EnumNotDefinedException("An authorization level must be defined for the subject");
		}
		
		String declaredLevelName = AnnotationUtils.extractAnnotationValue(sensitivityLevel);
		
		Enum<?> declaredAuthorizationLevel = parseEnum(enumClass.getEnumConstants(), declaredLevelName);
		
		return (declaredAuthorizationLevel.ordinal() >= subjectAuthorizationLevel.ordinal());
	}
	
	private <E> Enum<?> parseEnum(E[] constants, String levelName) {
		for (E constant : constants) {
			if (levelName.equals(constant.toString())) {
				return (Enum<?>) constant;
			}
		}
		throw new EnumNotDefinedException("Declared enum did not match subject");
	}
}