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
package org.gerryai.htn.simple.constraint.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleConstraintFactoryTest {

	/**
	 * Test the creation of a precedence constraint.
	 */
	@Test
	public void testCreatePrecedenceConstraint() {
		
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		SimplePrecedenceConstraint constraint = factory.createPrecedenceConstraint(mockTaskA, mockTaskB);
		
		assertEquals(mockTaskA,constraint.getPrecedingTask());
		assertEquals(mockTaskB,constraint.getProcedingTask());
	}

	/**
	 * Test the creation of a before constraint.
	 */
	@Test
	public void testCreateBeforeConstraint() {
		Set<SubstitutableTask> mockTasks = new HashSet<SubstitutableTask>();
		SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		SimpleBeforeConstraint constraint = factory.createBeforeConstraint(mockTasks, mockCondition);
		
		assertEquals(mockTasks,constraint.getTasks());
		assertEquals(mockCondition,constraint.getCondition());
	}

	/**
	 * Test the creation of an after constraint.
	 */
	@Test
	public void testCreateAfterConstraint() {
		Set<SubstitutableTask> mockTasks = new HashSet<SubstitutableTask>();
		SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		SimpleAfterConstraint constraint = factory.createAfterConstraint(mockTasks, mockCondition);
		
		assertEquals(mockTasks,constraint.getTasks());
		assertEquals(mockCondition,constraint.getCondition());
	}

	/**
	 * Test the creation of a between constraint.
	 */
	@Test
	public void testCreateBetweenConstraint() {
		Set<SubstitutableTask> mockTasksA = new HashSet<SubstitutableTask>();
		Set<SubstitutableTask> mockTasksB = new HashSet<SubstitutableTask>();
		SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		SimpleBetweenConstraint constraint = factory.createBetweenConstraint(mockTasksA, mockTasksB, mockCondition);
		
		assertEquals(mockTasksA,constraint.getPrecedingTasks());
		assertEquals(mockTasksB,constraint.getProcedingTasks());
		assertEquals(mockCondition,constraint.getCondition());
	}

}
