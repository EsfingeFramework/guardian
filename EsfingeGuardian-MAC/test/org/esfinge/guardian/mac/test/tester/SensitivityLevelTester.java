package org.esfinge.guardian.mac.test.tester;

import static org.junit.Assert.assertEquals;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.context.WrappedObj;
import org.esfinge.guardian.exception.AuthorizationException;
import org.esfinge.guardian.mac.context.Level;
import org.esfinge.guardian.mac.exception.EnumNotDefinedException;
import org.esfinge.guardian.mac.test.GuardedMACClass;
import org.esfinge.guardian.mac.test.GuardedMACInterface;
import org.esfinge.guardian.mac.test.Nivel;
import org.esfinge.guardian.mac.utils.MacConfig;
import org.junit.Before;
import org.junit.Test;

public class SensitivityLevelTester {

	MacConfig macConfig;

	@Before
	public void setUp() {
		macConfig = new MacConfig();
	}

	@Test(expected = AuthorizationException.class)
	public void testSubjectLevelLowerThanOperationLevel() {
		WrappedObj<Level> authLevel = AuthorizationContext.wrapAsSubjectProp(macConfig.getAuthorizationLevelKey(),
				Level.CLASSIFIED);

		GuardedMACInterface gc = AuthorizationContext.guardObject(new GuardedMACClass(), authLevel);

		gc.executeDependentOfSubjectLevel();
	}

	@Test
	public void testSubjectLevelEqualsToOperationLevel() {
		WrappedObj<Level> authLevel = AuthorizationContext.wrapAsSubjectProp(macConfig.getAuthorizationLevelKey(),
				Level.TOP_SECRET);

		GuardedMACInterface gc = AuthorizationContext.guardObject(new GuardedMACClass(), authLevel);

		assertEquals("This method must always execute", true, gc.executeDependentOfSubjectLevel());
	}

	@Test
	public void testSubjectLevelGreaterThanOperationLevel() {
		WrappedObj<Level> authLevel = AuthorizationContext.wrapAsSubjectProp(macConfig.getAuthorizationLevelKey(),
				Level.CLASSIFIED);

		GuardedMACInterface gc = AuthorizationContext.guardObject(new GuardedMACClass(), authLevel);

		assertEquals("This method must always execute", true, gc.execByAllSubjectsWithAnyLevel());
	}

	@Test(expected = EnumNotDefinedException.class)
	public void testSensitivityLevelUseWithNoSubjectLevel() {
		GuardedMACInterface gc = AuthorizationContext.guardObject(new GuardedMACClass());

		assertEquals("This method must always execute", true, gc.execByAllSubjectsWithAnyLevel());
	}

	@Test
	public void testSensitivityLevelUseWithNonDefaultAnnotationWithoutRestriction() {
		WrappedObj<Nivel> authLevel = AuthorizationContext.wrapAsSubjectProp(macConfig.getAuthorizationLevelKey(),
				Nivel.PUBLICO);

		GuardedMACInterface gc = AuthorizationContext.guardObject(new GuardedMACClass(), authLevel);

		assertEquals("This method must always execute", true,
				gc.executeByAllSubjectWithAnyLevelWithNonDefaultAnnotationWithoutRestriction());
	}

	@Test(expected = AuthorizationException.class)
	public void testSensitivityLevelUseWithNonDefaultAnnotationWithRestriction() {
		WrappedObj<Nivel> authLevel = AuthorizationContext.wrapAsSubjectProp(macConfig.getAuthorizationLevelKey(),
				Nivel.PUBLICO);

		GuardedMACInterface gc = AuthorizationContext.guardObject(new GuardedMACClass(), authLevel);

		gc.executeByAllSubjectWithAnyLevelWithNonDefaultAnnotationWithRestriction();
	}
}