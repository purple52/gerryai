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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.junit.Test;

/**
 * Unit tests for a simple task network builder.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkBuilderTest {

	/**
	 * Test builder construction.
	 */
	@Test
	public void testSimpleTaskNetworkBuilder() {
		// Create the builder under test
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		SimpleTaskNetworkBuilder<ConstraintValidator> builder = new SimpleTaskNetworkBuilder<ConstraintValidator>(mockConstraintValidator);
		
		// Check that the argument and constraint lists have been initialised
		assertTrue(builder.getTasks().isEmpty());
		assertTrue(builder.getConstraints().isEmpty());
	}

	/**
	 * Test adding single tasks.
	 */
	@Test
	public void testAddTask() {
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		
		// Create the builder under test
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		SimpleTaskNetworkBuilder<ConstraintValidator> builder = new SimpleTaskNetworkBuilder<ConstraintValidator>(mockConstraintValidator)
				.addTask(mockTaskA)
				.addTask(mockTaskB);
		
		// Check that the task set contains only the tasks we have just added
		assertEquals(2, builder.getTasks().size());
		assertTrue(builder.getTasks().contains(mockTaskA));
		assertTrue(builder.getTasks().contains(mockTaskB));
	}

	/**
	 * Test adding sets of tasks.
	 */
	@Test
	public void testAddTasks() {
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksOne = new HashSet<Task>();
		mockTasksOne.add(mockTaskA);
		mockTasksOne.add(mockTaskB);

		Task mockTaskC = mock(Task.class);
		Task mockTaskD = mock(Task.class);
		Set<Task> mockTasksTwo = new HashSet<Task>();
		mockTasksTwo.add(mockTaskC);
		mockTasksTwo.add(mockTaskD);
		
		// Create the builder under test
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		SimpleTaskNetworkBuilder<ConstraintValidator> builder = new SimpleTaskNetworkBuilder<ConstraintValidator>(mockConstraintValidator)
				.addTasks(mockTasksOne)
				.addTasks(mockTasksTwo);
		
		// Check that the arguments have been added in the correct order
		assertEquals(4, builder.getTasks().size());
		assertTrue(builder.getTasks().contains(mockTaskA));
		assertTrue(builder.getTasks().contains(mockTaskB));
		assertTrue(builder.getTasks().contains(mockTaskC));
		assertTrue(builder.getTasks().contains(mockTaskD));
	}

	/**
	 * Test adding single constraints.
	 * @throws InvalidConstraint only if the test fails
	 */
	@Test
	public void testAddConstraint() throws InvalidConstraint {
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		
		@SuppressWarnings("unchecked")
		ValidatableConstraint<ConstraintValidator> mockConstraintA = (ValidatableConstraint<ConstraintValidator>) mock(ValidatableConstraint.class);
		when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
		@SuppressWarnings("unchecked")
		ValidatableConstraint<ConstraintValidator> mockConstraintB = (ValidatableConstraint<ConstraintValidator>) mock(ValidatableConstraint.class);
		when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
		
		// Create the builder under test
		SimpleTaskNetworkBuilder<ConstraintValidator> builder = new SimpleTaskNetworkBuilder<ConstraintValidator>(mockConstraintValidator)
				.addConstraint(mockConstraintA)
				.addConstraint(mockConstraintB);
		
		// Check that the task set contains only the tasks we have just added
		assertEquals(2, builder.getConstraints().size());
		assertTrue(builder.getConstraints().contains(mockConstraintA));
		assertTrue(builder.getConstraints().contains(mockConstraintB));
	}

	/**
	 * Test adding sets of constraints.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testAddConstraints() throws InvalidConstraint {
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		
		@SuppressWarnings("unchecked")
		ValidatableConstraint<ConstraintValidator> mockConstraintA = mock(ValidatableConstraint.class);
		when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
		@SuppressWarnings("unchecked")
		ValidatableConstraint<ConstraintValidator> mockConstraintB = mock(ValidatableConstraint.class);
		when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
		
		Set<ValidatableConstraint<ConstraintValidator>> mockConstraintsOne = new HashSet<ValidatableConstraint<ConstraintValidator>>();
		mockConstraintsOne.add(mockConstraintA);
		mockConstraintsOne.add(mockConstraintB);

		@SuppressWarnings("unchecked")
		ValidatableConstraint<ConstraintValidator> mockConstraintC = mock(ValidatableConstraint.class);
		when(mockConstraintC.validate(mockConstraintValidator)).thenReturn(true);
		@SuppressWarnings("unchecked")
		ValidatableConstraint<ConstraintValidator> mockConstraintD = mock(ValidatableConstraint.class);
		when(mockConstraintD.validate(mockConstraintValidator)).thenReturn(true);
	
		Set<ValidatableConstraint<ConstraintValidator>> mockConstraintsTwo = new HashSet<ValidatableConstraint<ConstraintValidator>>();
		mockConstraintsTwo.add(mockConstraintC);
		mockConstraintsTwo.add(mockConstraintD);
		
		// Create the builder under test
		SimpleTaskNetworkBuilder<ConstraintValidator> builder = new SimpleTaskNetworkBuilder<ConstraintValidator>(mockConstraintValidator)
				.addConstraints(mockConstraintsOne)
				.addConstraints(mockConstraintsTwo);
		
		// Check that the arguments have been added in the correct order
		assertEquals(4, builder.getConstraints().size());
		assertTrue(builder.getConstraints().contains(mockConstraintA));
		assertTrue(builder.getConstraints().contains(mockConstraintB));
		assertTrue(builder.getConstraints().contains(mockConstraintC));
		assertTrue(builder.getConstraints().contains(mockConstraintD));
	}
	
	/**
	 * Test build.
	 * @throws InvalidConstraint only if the test fails
	 */
	public void testBuild() throws InvalidConstraint {
		Task mockTaskA = mock(Task.class);
		
		@SuppressWarnings("unchecked")
		ValidatableConstraint<ConstraintValidator> mockConstraintA = mock(ValidatableConstraint.class);
		
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetworkBuilder<ConstraintValidator>(mockConstraintValidator)
				.addTask(mockTaskA)
				.addConstraint(mockConstraintA)
				.build();
		
		assertEquals(1, taskNetwork.getTasks().size());
		assertTrue(taskNetwork.getTasks().contains(mockTaskA));
		assertEquals(1, taskNetwork.getConstraints().size());
		assertTrue(taskNetwork.getConstraints().contains(mockConstraintA));
	}
}
