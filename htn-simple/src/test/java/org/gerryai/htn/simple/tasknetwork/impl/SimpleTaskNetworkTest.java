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
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskNetworkTest {

	/**
	 * Test method for {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}.
	 */
	@Test
	public void testIsPrimitiveNoTasks() {

		@SuppressWarnings("unchecked")
		TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockBuilder = mock(TaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		taskNetwork.setTasks(tasks);
		
		assertTrue(taskNetwork.isPrimitive());
	}

	/**
	 * Test method for {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}.
	 */
	@Test
	public void testIsPrimitiveOnePrimitiveTask() {
		@SuppressWarnings("unchecked")
		TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockBuilder = mock(TaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		SubstitutableTask mockPrimitiveTask = mock(SubstitutableTask.class);
		when(mockPrimitiveTask.isPrimitive()).thenReturn(true);
		tasks.add(mockPrimitiveTask);
		taskNetwork.setTasks(tasks);
		
		assertTrue(taskNetwork.isPrimitive());
	}
	
	/**
	 * Test method for {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}.
	 */
	@Test
	public void testIsPrimitiveOneNonPrimitiveTask() {
		@SuppressWarnings("unchecked")
		TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockBuilder = mock(TaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		SubstitutableTask mockNonPrimitiveTask = mock(SubstitutableTask.class);
		when(mockNonPrimitiveTask.isPrimitive()).thenReturn(false);
		tasks.add(mockNonPrimitiveTask);
		taskNetwork.setTasks(tasks);
		
		assertFalse(taskNetwork.isPrimitive());
	}
	
	/**
	 * Test method for {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}.
	 */
	@Test
	public void testIsPrimitiveManyTasksAllPrimitive() {
		@SuppressWarnings("unchecked")
		TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockBuilder = mock(TaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		SubstitutableTask mockPrimitiveTaskOne = mock(SubstitutableTask.class);
		when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
		SubstitutableTask mockPrimitiveTaskTwo = mock(SubstitutableTask.class);
		when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(true);		
		SubstitutableTask mockPrimitiveTaskThree = mock(SubstitutableTask.class);
		when(mockPrimitiveTaskThree.isPrimitive()).thenReturn(true);	
		tasks.add(mockPrimitiveTaskOne);
		tasks.add(mockPrimitiveTaskTwo);
		tasks.add(mockPrimitiveTaskThree);
		taskNetwork.setTasks(tasks);
		
		assertTrue(taskNetwork.isPrimitive());
	}
	
	/**
	 * Test method for {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}.
	 */
	@Test
	public void testIsPrimitiveManyTasksOneNonPrimitive() {
		@SuppressWarnings("unchecked")
		TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockBuilder = mock(TaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		SubstitutableTask mockPrimitiveTaskOne = mock(SubstitutableTask.class);
		when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
		SubstitutableTask mockPrimitiveTaskTwo = mock(SubstitutableTask.class);
		when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(false);		
		SubstitutableTask mockPrimitiveTaskThree = mock(SubstitutableTask.class);
		when(mockPrimitiveTaskThree.isPrimitive()).thenReturn(true);	
		tasks.add(mockPrimitiveTaskOne);
		tasks.add(mockPrimitiveTaskTwo);
		tasks.add(mockPrimitiveTaskThree);
		taskNetwork.setTasks(tasks);
		
		assertFalse(taskNetwork.isPrimitive());
	}
}
