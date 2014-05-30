package org.esfinge.guardian.test.tester;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.init.Repository;
import org.esfinge.guardian.test.GuardedClass;
import org.esfinge.guardian.test.GuardedInterface;
import org.esfinge.guardian.test.context.MockAuthorizationContext;
import org.esfinge.guardian.test.context.MockRepository;
import org.junit.Before;
import org.junit.Test;

public class AttributeHandlerTest {
	private Repository repository;
	private GuardedInterface gc;
	
	@Before
	public void setUp() {
		repository = MockRepository.createAlwaysAllowDenyAnnotationsRepository();
		gc = new GuardedClass();
	}
	
	@Test
	public void includeTheAttributeValueIntoTheSubjectTest() throws Exception {
		Method guardedMethod = gc.getClass().getMethod("alwaysExecutedMethod");
		
		AuthorizationContext context = MockAuthorizationContext.createMock(gc, guardedMethod, repository);
		context.getInvoker().invoke(context);
		
		String shouldBeInTheSubject = context.getSubject().get("mySubjectAttribute", String.class);
		
		assertEquals("This attribute is not into the subject", "shouldBeInTheSubject", shouldBeInTheSubject);
	}
	
	@Test
	public void includeStaticeAttributeValueIntoTheSubjectTest() throws Exception {
		Method guardedMethod = gc.getClass().getMethod("alwaysExecutedMethod");
		
		AuthorizationContext context = MockAuthorizationContext.createMock(gc, guardedMethod, repository);
		context.getInvoker().invoke(context);
		
		String shouldBeInTheSubject = context.getSubject().get("myStaticSubjectAttribute", String.class);
		
		assertEquals("This static attribute is not into the subject", "shouldBeInTheSubject", shouldBeInTheSubject);
	}	
}
