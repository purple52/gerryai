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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.tasknetwork.Task;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskNetworBuilderTest {

	/**
	 * Test builder construction.
	 */
	@Test
	public void testSimpleTaskNetworkBuilder() {
		// Create the builder under test
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder();
		
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
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder()
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
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder()
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
	 */
	@Test
	public void testAddConstraint() {
		Constraint mockConstraintA = mock(Constraint.class);
		Constraint mockConstraintB = mock(Constraint.class);
		
		// Create the builder under test
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder()
				.addConstraint(mockConstraintA)
				.addConstraint(mockConstraintB);
		
		// Check that the task set contains only the tasks we have just added
		assertEquals(2, builder.getConstraints().size());
		assertTrue(builder.getConstraints().contains(mockConstraintA));
		assertTrue(builder.getConstraints().contains(mockConstraintB));
	}

	/**
	 * Test adding sets of constraints.
	 */
	@Test
	public void testAddConstraints() {
		Constraint mockConstraintA = mock(Constraint.class);
		Constraint mockConstraintB = mock(Constraint.class);
		Set<Constraint> mockConstraintsOne = new HashSet<Constraint>();
		mockConstraintsOne.add(mockConstraintA);
		mockConstraintsOne.add(mockConstraintB);

		Constraint mockConstraintC = mock(Constraint.class);
		Constraint mockConstraintD = mock(Constraint.class);
		Set<Constraint> mockConstraintsTwo = new HashSet<Constraint>();
		mockConstraintsTwo.add(mockConstraintC);
		mockConstraintsTwo.add(mockConstraintD);
		
		// Create the builder under test
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder()
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
	 */
	public void testBuild() {
		Task mockTaskA = mock(Task.class);
		Constraint mockConstraintA = mock(Constraint.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetworkBuilder()
				.addTask(mockTaskA)
				.addConstraint(mockConstraintA)
				.build();
		
		assertEquals(1, taskNetwork.getTasks().size());
		assertTrue(taskNetwork.getTasks().contains(mockTaskA));
		assertEquals(1, taskNetwork.getConstraints().size());
		assertTrue(taskNetwork.getConstraints().contains(mockConstraintA));
	}
}
