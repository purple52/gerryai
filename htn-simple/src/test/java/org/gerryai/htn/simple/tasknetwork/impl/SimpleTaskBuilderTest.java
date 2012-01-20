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
package org.gerryai.htn.simple.tasknetwork.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * Unit tests for a simple task builder
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskBuilderTest {

	/**
	 * Test builder construction.
	 */
	@Test
	public void testSimpleTaskBuilder() {
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder(mockDomainHelper);
		
		// Check that the arguments list has been initialised
		assertTrue(builder.getArguments().isEmpty());
	}

	/**
	 * Test that the builder can record a name to set.
	 * Implicitly tests getName()
	 */
	@Test
	public void testSetName() {
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder(mockDomainHelper)
				.setName("testname");
		
		// Check that the name has been set
		assertEquals("testname",builder.getName());
	}

	/**
	 * Test that single arguments can be added in the correct order.
	 * Implicitly tests getArguments()
	 */
	@Test
	public void testAddArgument() {
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		Term mockTermA = mock(Term.class);
		Term mockTermB = mock(Term.class);
		
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder(mockDomainHelper)
				.addArgument(mockTermA)
				.addArgument(mockTermB);
		
		// Check that the argument list contains only the argument we have just added
		assertEquals(2, builder.getArguments().size());
		assertEquals(mockTermA, builder.getArguments().get(0));
		assertEquals(mockTermB, builder.getArguments().get(1));
	}

	/**
	 * Test that lists of arguments can be added in the correct order.
	 * Implicitly tests getArguments()
	 */
	@Test
	public void testAddArguments() {
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		
		Term mockTermA = mock(Term.class);
		Term mockTermB = mock(Term.class);
		List<Term> mockTermsOne = new ArrayList<Term>();
		mockTermsOne.add(mockTermA);
		mockTermsOne.add(mockTermB);

		Term mockTermC = mock(Term.class);
		Term mockTermD = mock(Term.class);
		List<Term> mockTermsTwo = new ArrayList<Term>();
		mockTermsTwo.add(mockTermC);
		mockTermsTwo.add(mockTermD);
		
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder(mockDomainHelper)
				.addArguments(mockTermsOne)
				.addArguments(mockTermsTwo);
		
		// Check that the arguments have been added in the correct order
		assertEquals(4, builder.getArguments().size());
		assertEquals(mockTermA, builder.getArguments().get(0));
		assertEquals(mockTermB, builder.getArguments().get(1));
		assertEquals(mockTermC, builder.getArguments().get(2));
		assertEquals(mockTermD, builder.getArguments().get(3));
		}

	/**
	 * Test building a primitive task.
	 * @throws OperatorNotFound only if test fails
	 */
	@Test
	public void testBuildPrimitive() throws OperatorNotFound {
		Operator mockOperator = mock(Operator.class);
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		when(mockDomainHelper.getOperatorByName("testname")).thenReturn(mockOperator);
		Term mockTerm = mock(Term.class);
		
		// Create the builder under test
		SimpleAbstractTask primitiveTask = new SimpleTaskBuilder(mockDomainHelper)
				.setName("testname")
				.addArgument(mockTerm)
				.build();
		assertEquals("testname",primitiveTask.getName());
		assertEquals(1, primitiveTask.getArguments().size());
		assertTrue(primitiveTask.getArguments().contains(mockTerm));
		assertTrue(primitiveTask.isPrimitive());
	}

	/**
	 * Test building a non-primitive task.
	 * @throws OperatorNotFound only if test fails
	 */
	@Test
	public void testBuildNonPrimitive() throws OperatorNotFound {
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		when(mockDomainHelper.getOperatorByName("testname")).thenThrow(new OperatorNotFound());
		Term mockTerm = mock(Term.class);
		
		// Create the builder under test
		SimpleAbstractTask primitiveTask = new SimpleTaskBuilder(mockDomainHelper)
				.setName("testname")
				.addArgument(mockTerm)
				.build();
		assertEquals("testname",primitiveTask.getName());
		assertEquals(1, primitiveTask.getArguments().size());
		assertTrue(primitiveTask.getArguments().contains(mockTerm));
		assertFalse(primitiveTask.isPrimitive());
	}
}
