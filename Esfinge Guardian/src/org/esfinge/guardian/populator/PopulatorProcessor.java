package org.esfinge.guardian.populator;

import java.util.ArrayList;
import java.util.List;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.utils.ServiceLocator;

public class PopulatorProcessor {
	static public void process(AuthorizationContext context) {
		List<PopulatorFinder> findersList = ServiceLocator.getServiceImplementationList(PopulatorFinder.class);
		List<Populator> populators = new ArrayList<Populator>();
		
		for (PopulatorFinder finder : findersList) {
			populators.addAll( finder.find(context) );
		}
		
		for (Populator populator : populators) {
			populator.populate(context);
		}
	}
}