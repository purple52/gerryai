/**
 *  Gerry AI - Open framework for automated planning algorithms
 *  Copyright (C) 2012  David Edwards
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gerryai.htn.simple.domain.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.simple.constraint.SubstitutableValidatableConstraint;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainHelperTest {

	/**
	 * Test that the helper can give us back correct domain.
	 */
	@Test
	public void testGetDomain() {
		
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		
		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Check that the helper gives back the correct domain
		assertEquals(mockDomain,domainHelper.getDomain());
	}
	
	/**
	 * Test that no operator is found if operator list is empty
	 */
	@Test(expected=OperatorNotFound.class)
	public void testGetOperatorByNameEmptyList() throws OperatorNotFound {
		
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
		SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		when(mockDomain.getOperators()).thenReturn(new HashSet<SubstitutableOperator>());

		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Try and find a non-existent operator
		domainHelper.getOperatorByName("anything");
	}
	
	/**
	 * Test that no operator is found if the operator really doesn't exist
	 */
	@Test(expected=OperatorNotFound.class)
	public void testGetOperatorByNameNotFound() throws OperatorNotFound {
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		SubstitutableOperator mockOperatorA = mock(SubstitutableOperator.class);
		when(mockOperatorA.getName()).thenReturn("operatorA");
		SubstitutableOperator mockOperatorB = mock(SubstitutableOperator.class);
		when(mockOperatorB.getName()).thenReturn("operatorB");
		Set<SubstitutableOperator> operators = new HashSet<SubstitutableOperator>();
		operators.add(mockOperatorA);
		operators.add(mockOperatorB);
		when(mockDomain.getOperators()).thenReturn(operators);

		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Try and find a non-existent operator
		domainHelper.getOperatorByName("operatorC");
	}

	/**
	 * Test that operator is found if the operator does exist
	 */
	@Test
	public void testGetOperatorByNameFound() throws OperatorNotFound {
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
		SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		SubstitutableOperator mockOperatorA = mock(SubstitutableOperator.class);
		when(mockOperatorA.getName()).thenReturn("operatorA");
		SubstitutableOperator mockOperatorB = mock(SubstitutableOperator.class);
		when(mockOperatorB.getName()).thenReturn("operatorB");
		Set<SubstitutableOperator> operators = new HashSet<SubstitutableOperator>();
		operators.add(mockOperatorA);
		operators.add(mockOperatorB);
		when(mockDomain.getOperators()).thenReturn(operators);

		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Try and find an operator that exists
		assertEquals(mockOperatorA, domainHelper.getOperatorByName("operatorA"));
		assertEquals(mockOperatorB, domainHelper.getOperatorByName("operatorB"));
	}
	
	/**
	 * Test that no method is found if method list is empty
	 */
	@Test
	public void testGetMethodByTaskEmptyList() {
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		when(mockDomain.getMethods()).thenReturn(new HashSet<SubstitutableMethod>());

		SubstitutableTask mockTask = mock(SubstitutableTask.class);
		
		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Try and find a non-existent method
		assertTrue(domainHelper.getMethodsByTask(mockTask).isEmpty());
	}
	
	/**
	 * Test that no methods are found if there really are no methods that decompose a task
	 */
	@Test
	public void testGetMethodByTaskNotFound() {
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		when(mockTaskA.getName()).thenReturn("taskA");
		SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
		when(mockTaskB.getName()).thenReturn("taskB");
		SubstitutableTask mockTaskC = mock(SubstitutableTask.class);
		when(mockTaskC.getName()).thenReturn("taskC");
		
		SubstitutableMethod mockMethodA = mock(SubstitutableMethod.class);
		when(mockMethodA.getTask()).thenReturn(mockTaskA);
		SubstitutableMethod mockMethodB = mock(SubstitutableMethod.class);
		when(mockMethodB.getTask()).thenReturn(mockTaskB);
		
		Set<SubstitutableMethod> methods = new HashSet<SubstitutableMethod>();
		methods.add(mockMethodA);
		methods.add(mockMethodB);
		when(mockDomain.getMethods()).thenReturn(methods);

		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Try and find a non-existent operator
		assertTrue(domainHelper.getMethodsByTask(mockTaskC).isEmpty());
	}
	
	/**
	 * Test that a method is found if is a single method that decompose a task
	 */
	@Test
	public void testGetMethodByTaskOneFound() {
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		when(mockTaskA.getName()).thenReturn("taskA");
		SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
		when(mockTaskB.getName()).thenReturn("taskB");
		
		SubstitutableMethod mockMethodA = mock(SubstitutableMethod.class);
		when(mockMethodA.getTask()).thenReturn(mockTaskA);
		SubstitutableMethod mockMethodB = mock(SubstitutableMethod.class);
		when(mockMethodB.getTask()).thenReturn(mockTaskB);
		
		Set<SubstitutableMethod> methods = new HashSet<SubstitutableMethod>();
		methods.add(mockMethodA);
		methods.add(mockMethodB);
		when(mockDomain.getMethods()).thenReturn(methods);

		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Try and find an existing method
		assertEquals(1,domainHelper.getMethodsByTask(mockTaskA).size());
		assertTrue(domainHelper.getMethodsByTask(mockTaskA).contains(mockMethodA));
		assertEquals(1,domainHelper.getMethodsByTask(mockTaskB).size());
		assertTrue(domainHelper.getMethodsByTask(mockTaskB).contains(mockMethodB));
	}
	
	/**
	 * Test that more than one methods are found if more than one methods decompose a task
	 */
	@Test
	public void testGetMethodByTaskTwoFound() {
		@SuppressWarnings("unchecked")
		Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				SubstitutableValidatableConstraint, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		when(mockTaskA.getName()).thenReturn("taskA");
		SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
		when(mockTaskB.getName()).thenReturn("taskB");
		
		SubstitutableMethod mockMethodA = mock(SubstitutableMethod.class);
		when(mockMethodA.getTask()).thenReturn(mockTaskA);
		SubstitutableMethod mockMethodB = mock(SubstitutableMethod.class);
		when(mockMethodB.getTask()).thenReturn(mockTaskB);
		SubstitutableMethod mockMethodC = mock(SubstitutableMethod.class);
		when(mockMethodC.getTask()).thenReturn(mockTaskB);
		
		Set<SubstitutableMethod> methods = new HashSet<SubstitutableMethod>();
		methods.add(mockMethodA);
		methods.add(mockMethodB);
		methods.add(mockMethodC);
		when(mockDomain.getMethods()).thenReturn(methods);

		// Create the domain helper under test
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(mockDomain);
		
		// Try and find methods
		assertEquals(1,domainHelper.getMethodsByTask(mockTaskA).size());
		assertTrue(domainHelper.getMethodsByTask(mockTaskA).contains(mockMethodA));
		assertEquals(2,domainHelper.getMethodsByTask(mockTaskB).size());
		assertTrue(domainHelper.getMethodsByTask(mockTaskB).contains(mockMethodB));
		assertTrue(domainHelper.getMethodsByTask(mockTaskB).contains(mockMethodC));
	}
}
