package org.esfinge.guardian.test.repository;

import static org.junit.Assert.*;

import org.esfinge.guardian.init.Repository;
import org.junit.Test;

public class RepositoryTester {
	
	@Test
	public void compareReferences() {
		Repository r1 = Repository.getInstance();
		Repository r2 = Repository.getInstance();
		assertSame(r1, r2);
	}
}
