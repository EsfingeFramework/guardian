package org.esfinge.guardian.context.entity;

import java.util.HashMap;
import java.util.Map;


public class ContextMap {
	private Map<Object, Object> properties = new HashMap<Object, Object>();
	
	public void put(Object key, Object value) {
		properties.put(key, value);
	}
	
	public Object get(Object key) {
		return properties.get(key);
	}
	
	public <T> T get(Object key, T type) {
		@SuppressWarnings("unchecked")
		T value = (T) properties.get(key);
		return value;
	}
	
	public <T> T get(Object key, Class<T> type) {
		@SuppressWarnings("unchecked")
		T value = (T) properties.get(key);
		return value;
	}
	
	public void putAll(ContextMap cMap) {
		properties.putAll( cMap.getPropertiesMap() );
	}

	public Map<Object, Object> getProps() {
		return properties;
	}
	
	public Map<Object, Object> getPropertiesMap() {
		return properties;
	}
}
