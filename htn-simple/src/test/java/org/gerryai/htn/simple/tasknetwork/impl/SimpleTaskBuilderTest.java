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

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.simple.logic.SubstitutableTerm;

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
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder();
		
		// Check that the arguments list has been initialised
		assertTrue(builder.getArguments().isEmpty());
	}

	/**
	 * Test that the builder can record a name to set.
	 * Implicitly tests getName()
	 */
	@Test
	public void testSetName() {
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder()
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

		SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
		SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
		
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder()
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
		
		SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
		SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
		List<SubstitutableTerm> mockTermsOne = new ArrayList<SubstitutableTerm>();
		mockTermsOne.add(mockTermA);
		mockTermsOne.add(mockTermB);

		SubstitutableTerm mockTermC = mock(SubstitutableTerm.class);
		SubstitutableTerm mockTermD = mock(SubstitutableTerm.class);
		List<SubstitutableTerm> mockTermsTwo = new ArrayList<SubstitutableTerm>();
		mockTermsTwo.add(mockTermC);
		mockTermsTwo.add(mockTermD);
		
		// Create the builder under test
		SimpleTaskBuilder builder = new SimpleTaskBuilder()
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

		SubstitutableTerm mockTerm = mock(SubstitutableTerm.class);
		
		// Create the builder under test
		SimpleTask primitiveTask = new SimpleTaskBuilder()
				.setName("testname")
				.addArgument(mockTerm)
				.setIsPrimitive(true)
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

		SubstitutableTerm mockTerm = mock(SubstitutableTerm.class);
		
		// Create the builder under test
		SimpleTask primitiveTask = new SimpleTaskBuilder()
				.setName("testname")
				.addArgument(mockTerm)
				.setIsPrimitive(false)
				.build();
		assertEquals("testname",primitiveTask.getName());
		assertEquals(1, primitiveTask.getArguments().size());
		assertTrue(primitiveTask.getArguments().contains(mockTerm));
		assertFalse(primitiveTask.isPrimitive());
	}
}
