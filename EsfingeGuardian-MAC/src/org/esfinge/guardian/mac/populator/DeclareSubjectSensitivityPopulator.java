package org.esfinge.guardian.mac.populator;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.mac.annotation.context.DeclareSubjectSensitivity;
import org.esfinge.guardian.mac.context.Level;
import org.esfinge.guardian.mac.utils.MacConfig;
import org.esfinge.guardian.populator.Populator;

public class DeclareSubjectSensitivityPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {
		MacConfig config = new MacConfig();
		Class<?> clazz = context.getGuardedObj().getClass();
		Level level = null;
		if (clazz.isAnnotationPresent(DeclareSubjectSensitivity.class)) {
			level = (Level) clazz.getAnnotation(DeclareSubjectSensitivity.class).value();
			
			context.getSubject().put(config.getAuthorizationLevelKey(), level);
		}
	}
}