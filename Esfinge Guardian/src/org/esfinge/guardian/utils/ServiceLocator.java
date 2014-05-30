package org.esfinge.guardian.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.esfinge.guardian.exception.ServiceLocatorMisuseException;

public class ServiceLocator {
	
	@SuppressWarnings("rawtypes")
	static private Map<Class, Object> classRelatedServices = new HashMap<Class, Object>();
	
	@SuppressWarnings("unchecked")
	public static <E> E getServiceImplementation(Class<E> clazzType) {
		if (classRelatedServices.containsKey(clazzType)) {
			E objE; 
			try {
				objE = (E) classRelatedServices.get(clazzType);
			} catch(Exception e) {
				throw new ServiceLocatorMisuseException("Wrong use of ServiceLocator.getServiceImplementation");
			} 
			return objE;
		}
		
		ServiceLoader<E> loader = ServiceLoader.load(clazzType);
		Iterator<E> it = loader.iterator();
		E current = null;
		if (it.hasNext()) {
			try {
				current = it.next();
			} catch (Exception e) {
				throw new ServiceLocatorMisuseException("Wrong use of ServiceLocator.getServiceImplementation");
			}
			classRelatedServices.put(clazzType, current);
		}
		return current;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getServiceImplementationList(Class<E> clazzType) {
		if (classRelatedServices.containsKey(clazzType)) {
			List<E> objE; 
			try {
				objE = (List<E>) classRelatedServices.get(clazzType);
			} catch(Exception e) {
				throw new ServiceLocatorMisuseException("Wrong use of ServiceLocator.getServiceImplementationList");
			} 
			return objE;
		}
		
		ServiceLoader<E> loader = ServiceLoader.load(clazzType);
		Iterator<E> it = loader.iterator();
		List<E> list = new ArrayList<E>();
		while (it.hasNext()){
			list.add(it.next());
		}
		classRelatedServices.put(clazzType, list);
		return list;
	}
}