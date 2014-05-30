package org.esfinge.guardian.scanner.core;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.context.WrappedObj;
import org.esfinge.guardian.exception.AnnotationScannerException;
import org.esfinge.guardian.scanner.AnnotationScanner;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;

public class WebAnnotationScanner implements AnnotationScanner {

	@Override
	public Map<String, Set<String>> scan(AuthorizationContext context)
			throws AnnotationScannerException {

		WrappedObj<?>[] wraps = context.getWrappedObjs();
		ServletContext sc = null;
		if (wraps != null) {
			for (WrappedObj<?> wObj : wraps) {
				Object obj = wObj.getObject();
				if (obj instanceof ServletContext) {
					sc = (ServletContext) obj;
				}
			}
		}

		if (sc == null) {
			String message = "It is required to have javax.servlet.ServletContext wrapped as a WrappedObj in order to use this class";
			throw new AnnotationScannerException(message);
		}

		URL[] urls1 = ClasspathUrlFinder.findResourceBases("");
		URL[] urls2 = WarUrlFinder.findWebInfLibClasspaths(sc);

		AnnotationDB db1 = null;
		AnnotationDB db2 = null;
		try {
			db1 = new AnnotationDB();
			db1.scanArchives(urls1);

			db2 = new AnnotationDB();
			db2.scanArchives(urls2);

		} catch (IOException e) {
			throw new AnnotationScannerException(
					"Not possible to scan for annotations", e);
		}
		Map<String, Set<String>> map1 = db1.getAnnotationIndex();
		Map<String, Set<String>> map2 = db2.getAnnotationIndex();
		
		Map<String, Set<String>> merged = mergeAll(map1, map2); 
		
		return merged;
	}

	private Map<String, Set<String>> mergeAll(Map<String, Set<String>> map1, Map<String, Set<String>> map2) {
		Map<String, Set<String>> merged = new HashMap<String, Set<String>>();

		for (Map.Entry<String, Set<String>> entry : map1.entrySet()) {
			String key = entry.getKey();
			Set<String> setMap1 = entry.getValue();

			Set<String> setMap2 = map2.get(key);
			if (setMap2 != null) {
				setMap1.addAll(setMap2);
			}
			merged.put(key, setMap1);
		}
		
		for (Map.Entry<String, Set<String>> entry : map2.entrySet()) {
			String key = entry.getKey();
			if (!map1.containsKey(key)) {
				merged.put(key, entry.getValue());
			}
		}
		return merged;
	}
}