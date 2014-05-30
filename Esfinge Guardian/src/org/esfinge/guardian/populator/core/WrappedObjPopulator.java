package org.esfinge.guardian.populator.core;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.context.WrappedObj;
import org.esfinge.guardian.context.entity.ContextType;
import org.esfinge.guardian.populator.Populator;

public class WrappedObjPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {
		WrappedObj<?>[] wrappedObjs = context.getWrappedObjs();
		
		if (wrappedObjs != null ) {
			for (WrappedObj<?> wrappedObj : wrappedObjs) {
	
				ContextType contextType = wrappedObj.getEntityType();
				Object key = wrappedObj.getKey();
				Object value = wrappedObj.getObject();
	
				switch (contextType) {
				case SUBJECT:
					context.getSubject().put(key, value);
					break;
				case ENVIRONMENT:
					context.getEnvironment().put(key, value);
					break;
				case RESOURCE:
					context.getResource().put(key, value);
					break;
				default:
					break;
				}
			}
		}
	}
}