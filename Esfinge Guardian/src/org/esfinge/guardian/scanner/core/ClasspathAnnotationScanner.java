package org.esfinge.guardian.scanner.core;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.AnnotationScannerException;
import org.esfinge.guardian.scanner.AnnotationScanner;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

public class ClasspathAnnotationScanner implements AnnotationScanner {

	@Override
	public Map<String, Set<String>> scan(AuthorizationContext context) throws AnnotationScannerException {
		URL[] urls = ClasspathUrlFinder.findClassPaths();
		
		AnnotationDB db = null;
		try {
			db = new AnnotationDB(); 
			db.scanArchives(urls);
		} catch(IOException e) {
			throw new AnnotationScannerException("Not possible to scan for annotations", e);
		}
		return db.getAnnotationIndex();
	}
}