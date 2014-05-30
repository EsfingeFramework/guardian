package org.esfinge.guardian.mac.test;

import org.esfinge.guardian.mac.annotation.authorization.OperationSensitivity;
import org.esfinge.guardian.mac.context.Level;
import org.esfinge.guardian.mac.test.annotation.OperationSensitivityTest;

public interface GuardedMACInterface {

	@OperationSensitivity(Level.PUBLIC)
	boolean execByAllSubjectsWithAnyLevel();

	@OperationSensitivity(Level.TOP_SECRET)
	boolean executeDependentOfSubjectLevel();
	
	@OperationSensitivityTest(Nivel.PUBLICO)
	abstract boolean executeByAllSubjectWithAnyLevelWithNonDefaultAnnotationWithoutRestriction();
	
	@OperationSensitivityTest(Nivel.SECRETO)
	abstract boolean executeByAllSubjectWithAnyLevelWithNonDefaultAnnotationWithRestriction();
	
	String getToBeUsedByTheSubject();
	
	void setToBeUsedByTheSubject(String toBeUsedByTheSubject);
}