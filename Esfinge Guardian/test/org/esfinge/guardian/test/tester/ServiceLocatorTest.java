package org.esfinge.guardian.test.tester;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.esfinge.guardian.test.TestInterface;
import org.esfinge.guardian.test.TestListInterface;
import org.esfinge.guardian.test.impl.TestImplementation1;
import org.esfinge.guardian.test.impl.TestImplementation2;
import org.esfinge.guardian.utils.ServiceLocator;
import org.junit.Test;

public class ServiceLocatorTest {
	
	@Test
	public void getServiceImplementationTest() {
		TestInterface ti = ServiceLocator.getServiceImplementation(TestInterface.class);
		assertTrue(ti instanceof TestInterface);
	}
	
	@Test
	public void getServiceImplementationListCorrectOrder() {
		List<TestListInterface> lista = ServiceLocator.getServiceImplementationList(TestListInterface.class);
		boolean t1 = (lista.get(0) instanceof TestImplementation1);
		boolean t2 = (lista.get(1) instanceof TestImplementation2);
		assertTrue(t1 && t2);
	}
	
	@Test
	public void getServiceImplementationListIncorrectOrder() {
		List<TestListInterface> lista = ServiceLocator.getServiceImplementationList(TestListInterface.class);
		boolean t1 = (lista.get(1) instanceof TestImplementation1);
		boolean t2 = (lista.get(0) instanceof TestImplementation2);
		assertFalse(t1 && t2);
	}
}
