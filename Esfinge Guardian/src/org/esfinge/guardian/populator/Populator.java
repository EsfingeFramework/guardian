package org.esfinge.guardian.populator;

import org.esfinge.guardian.context.AuthorizationContext;

public interface Populator {
	void populate(AuthorizationContext context);
}