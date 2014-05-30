package org.esfinge.guardian.context;

import org.esfinge.guardian.context.entity.ContextType;


public class WrappedObj<E> {
	private Object key;
	private E object;
	private ContextType entityType;

	WrappedObj(String name, E object, ContextType entityType) {
		this.key = name;
		this.object = object;
		this.entityType = entityType; 
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public E getObject() {
		return object;
	}

	public void setObject(E object) {
		this.object = object;
	}

	public ContextType getEntityType() {
		return entityType;
	}

	public void setEntityType(ContextType entityType) {
		this.entityType = entityType;
	}
}