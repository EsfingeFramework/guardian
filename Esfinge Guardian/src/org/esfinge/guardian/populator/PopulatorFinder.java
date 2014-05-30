package org.esfinge.guardian.populator;

import java.util.List;

import org.esfinge.guardian.context.AuthorizationContext;

public interface PopulatorFinder {
	public List<Populator> find(AuthorizationContext context); 
}
