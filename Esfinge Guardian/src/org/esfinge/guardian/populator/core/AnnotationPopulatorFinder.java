package org.esfinge.guardian.populator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.esfinge.guardian.annotation.config.PopulatorClass;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.exception.PopulatorClassAnnotationMisuseException;
import org.esfinge.guardian.init.ClasspathAnnotations;
import org.esfinge.guardian.populator.Populator;
import org.esfinge.guardian.populator.PopulatorFinder;

public class AnnotationPopulatorFinder implements PopulatorFinder {

	@Override
	public List<Populator> find(AuthorizationContext context) {
		List<Populator> populators = new ArrayList<Populator>();

		ClasspathAnnotations classpathAnnotations = ClasspathAnnotations.getInstance(context);

		Map<String, Set<String>> allAnnotationsMap = classpathAnnotations.getClasspathAnnotations();

		Set<String> annotatedClasses = allAnnotationsMap.get(PopulatorClass.class.getName());
		
		if (annotatedClasses != null) {
			for (String className : annotatedClasses) {
				try {
					@SuppressWarnings("unchecked")
					Class<Populator> populatorClass = (Class<Populator>) Class.forName(className);
	
					Populator populator = populatorClass.newInstance();
					populators.add(populator);
	
				} catch (Exception e) {
					throw new PopulatorClassAnnotationMisuseException("Wrong use of PopulatorClass Annotation", e);
				}
			}
		}

		return populators;
	}
}
