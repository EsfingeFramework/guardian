package org.esfinge.guardian.mac.test;

import org.esfinge.guardian.annotation.context.Subject;
import org.esfinge.guardian.mac.annotation.authorization.OperationSensitivity;
import org.esfinge.guardian.mac.context.Level;
import org.esfinge.guardian.mac.test.annotation.OperationSensitivityTest;

public class GuardedMACClass implements GuardedMACInterface {
	
	@Subject("mySubjectAttribute")
	private String toBeUsedByTheSubject = "shouldBeInTheSubject";
	
	@Subject("myStaticSubjectAttribute")
	static private String staticAttribute = "shouldBeInTheSubject";
	
	
	@Override
	@OperationSensitivity(Level.PUBLIC)
	public boolean execByAllSubjectsWithAnyLevel() {
		return true;
	}
	
	@Override
	@OperationSensitivity(Level.TOP_SECRET)
	public boolean executeDependentOfSubjectLevel() {
		return true;
	}
	
	@Override
	@OperationSensitivityTest(Nivel.PUBLICO)
	public boolean executeByAllSubjectWithAnyLevelWithNonDefaultAnnotationWithoutRestriction() {
		return true;
	}
	
	@Override
	@OperationSensitivityTest(Nivel.SECRETO)
	public boolean executeByAllSubjectWithAnyLevelWithNonDefaultAnnotationWithRestriction() {
		return true;
	}
	
	/* SETTERS AND GETTERS */
	
	@Override
	public String getToBeUsedByTheSubject() {
		return toBeUsedByTheSubject;
	}

	@Override
	public void setToBeUsedByTheSubject(String toBeUsedByTheSubject) {
		this.toBeUsedByTheSubject = toBeUsedByTheSubject;
	}

	public static String getStaticAttribute() {
		return staticAttribute;
	}

	public static void setStaticAttribute(String staticAttribute) {
		GuardedMACClass.staticAttribute = staticAttribute;
	}

}