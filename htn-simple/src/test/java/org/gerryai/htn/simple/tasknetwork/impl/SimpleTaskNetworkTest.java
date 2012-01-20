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

import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;
import org.gerryai.htn.tasknetwork.Task;
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
		SimpleTaskNetworkBuilder mockBuilder = mock(SimpleTaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<Task> tasks = new HashSet<Task>();
		taskNetwork.setTasks(tasks);
		
		assertTrue(taskNetwork.isPrimitive());
	}

	/**
	 * Test method for {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}.
	 */
	@Test
	public void testIsPrimitiveOnePrimitiveTask() {
		SimpleTaskNetworkBuilder mockBuilder = mock(SimpleTaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<Task> tasks = new HashSet<Task>();
		Task mockPrimitiveTask = mock(Task.class);
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
		SimpleTaskNetworkBuilder mockBuilder = mock(SimpleTaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<Task> tasks = new HashSet<Task>();
		Task mockNonPrimitiveTask = mock(Task.class);
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
		SimpleTaskNetworkBuilder mockBuilder = mock(SimpleTaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<Task> tasks = new HashSet<Task>();
		Task mockPrimitiveTaskOne = mock(Task.class);
		when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
		Task mockPrimitiveTaskTwo = mock(Task.class);
		when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(true);		
		Task mockPrimitiveTaskThree = mock(Task.class);
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
		SimpleTaskNetworkBuilder mockBuilder = mock(SimpleTaskNetworkBuilder.class);
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
		Set<Task> tasks = new HashSet<Task>();
		Task mockPrimitiveTaskOne = mock(Task.class);
		when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
		Task mockPrimitiveTaskTwo = mock(Task.class);
		when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(false);		
		Task mockPrimitiveTaskThree = mock(Task.class);
		when(mockPrimitiveTaskThree.isPrimitive()).thenReturn(true);	
		tasks.add(mockPrimitiveTaskOne);
		tasks.add(mockPrimitiveTaskTwo);
		tasks.add(mockPrimitiveTaskThree);
		taskNetwork.setTasks(tasks);
		
		assertFalse(taskNetwork.isPrimitive());
	}
}
