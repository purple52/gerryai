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

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
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
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		
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
		Set<Task> mockTasks = new HashSet<Task>();
		Term mockLiteral = mock(Term.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		SimpleBeforeConstraint constraint = factory.createBeforeConstraint(mockTasks, mockLiteral);
		
		assertEquals(mockTasks,constraint.getTasks());
		assertEquals(mockLiteral,constraint.getLiteral());
	}

	/**
	 * Test the creation of an after constraint.
	 */
	@Test
	public void testCreateAfterConstraint() {
		Set<Task> mockTasks = new HashSet<Task>();
		Term mockLiteral = mock(Term.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		SimpleAfterConstraint constraint = factory.createAfterConstraint(mockTasks, mockLiteral);
		
		assertEquals(mockTasks,constraint.getTasks());
		assertEquals(mockLiteral,constraint.getLiteral());
	}

	/**
	 * Test the creation of a between constraint.
	 */
	@Test
	public void testCreateBetweenConstraint() {
		Set<Task> mockTasksA = new HashSet<Task>();
		Set<Task> mockTasksB = new HashSet<Task>();
		Term mockLiteral = mock(Term.class);
		
		// Create factory under test
		SimpleConstraintFactory factory = new SimpleConstraintFactory();
		
		//Try and create the constraint
		SimpleBetweenConstraint constraint = factory.createBetweenConstraint(mockTasksA, mockTasksB, mockLiteral);
		
		assertEquals(mockTasksA,constraint.getPrecedingTasks());
		assertEquals(mockTasksB,constraint.getProcedingTasks());
		assertEquals(mockLiteral,constraint.getLiteral());
	}

}