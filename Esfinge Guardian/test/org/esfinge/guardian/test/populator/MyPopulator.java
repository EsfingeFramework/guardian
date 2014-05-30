package org.esfinge.guardian.test.populator;

import org.esfinge.guardian.annotation.config.PopulatorClass;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.populator.Populator;

@PopulatorClass(MyPopulator.class)
public class MyPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {
		context.getEnvironment().put("my.key", true);
	}
}
