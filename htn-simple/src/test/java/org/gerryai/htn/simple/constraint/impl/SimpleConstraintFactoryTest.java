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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableValidatableAfterConstraint;
import org.gerryai.htn.simple.constraint.ImmutableValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.ImmutableValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.ImmutableValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
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
	public final void testCreatePrecedenceConstraint() {
		
		ImmutableTask mockTaskA = mock(ImmutableTask.class);
		ImmutableTask mockTaskB = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasksOne = new HashSet<ImmutableTask>();
        mockTasksOne.add(mockTaskA);
        Set<ImmutableTask> mockTasksTwo = new HashSet<ImmutableTask>();
        mockTasksOne.add(mockTaskB);
        
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		ImmutableValidatablePrecedenceConstraint constraint
				= factory.createPrecedenceConstraint(mockTasksOne, mockTasksTwo);
		
		assertEquals(mockTasksOne, constraint.getPrecedingTasks());
		assertEquals(mockTasksTwo, constraint.getProcedingTasks());
	}

	/**
	 * Test the creation of a before constraint.
	 */
	@Test
	public final void testCreateBeforeConstraint() {
		Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
		ImmutableCondition mockCondition = mock(ImmutableCondition.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		ImmutableValidatableBeforeConstraint constraint = factory.createBeforeConstraint(mockTasks, mockCondition);
		
		assertEquals(mockTasks, constraint.getTasks());
		assertEquals(mockCondition, constraint.getCondition());
	}

	/**
	 * Test the creation of an after constraint.
	 */
	@Test
	public final void testCreateAfterConstraint() {
		Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
		ImmutableCondition mockCondition = mock(ImmutableCondition.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		ImmutableValidatableAfterConstraint constraint = factory.createAfterConstraint(mockTasks, mockCondition);
		
		assertEquals(mockTasks, constraint.getTasks());
		assertEquals(mockCondition, constraint.getCondition());
	}

	/**
	 * Test the creation of a between constraint.
	 */
	@Test
	public final void testCreateBetweenConstraint() {
		Set<ImmutableTask> mockTasksA = new HashSet<ImmutableTask>();
		Set<ImmutableTask> mockTasksB = new HashSet<ImmutableTask>();
		ImmutableCondition mockCondition = mock(ImmutableCondition.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		ImmutableValidatableBetweenConstraint constraint =
				factory.createBetweenConstraint(mockTasksA, mockTasksB, mockCondition);
		
		assertEquals(mockTasksA, constraint.getPrecedingTasks());
		assertEquals(mockTasksB, constraint.getProcedingTasks());
		assertEquals(mockCondition, constraint.getCondition());
	}

}
