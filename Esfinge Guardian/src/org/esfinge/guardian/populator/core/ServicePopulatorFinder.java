package org.esfinge.guardian.populator.core;

import java.util.List;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.populator.Populator;
import org.esfinge.guardian.populator.PopulatorFinder;
import org.esfinge.guardian.utils.ServiceLocator;

public class ServicePopulatorFinder implements PopulatorFinder {

	@Override
	public List<Populator> find(AuthorizationContext context) {
		return ServiceLocator.getServiceImplementationList(Populator.class);
	}
}
