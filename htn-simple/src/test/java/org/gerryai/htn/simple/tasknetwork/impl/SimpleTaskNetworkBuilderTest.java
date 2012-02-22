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

import org.gerryai.htn.simple.constraint.SubstitutableValidatableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
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
		@SuppressWarnings("unchecked")
		ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder(mockConstraintValidator);
		
		// Check that the argument and constraint lists have been initialised
		assertTrue(builder.getTasks().isEmpty());
		assertTrue(builder.getConstraints().isEmpty());
	}

	/**
	 * Test adding single tasks.
	 */
	@Test
	public void testAddTask() {
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
		
		// Create the builder under test
		@SuppressWarnings("unchecked")
		ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder(mockConstraintValidator)
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
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
		Set<SubstitutableTask> mockTasksOne = new HashSet<SubstitutableTask>();
		mockTasksOne.add(mockTaskA);
		mockTasksOne.add(mockTaskB);

		SubstitutableTask mockTaskC = mock(SubstitutableTask.class);
		SubstitutableTask mockTaskD = mock(SubstitutableTask.class);
		Set<SubstitutableTask> mockTasksTwo = new HashSet<SubstitutableTask>();
		mockTasksTwo.add(mockTaskC);
		mockTasksTwo.add(mockTaskD);
		
		// Create the builder under test
		@SuppressWarnings("unchecked")
		ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder(mockConstraintValidator)
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
		@SuppressWarnings("unchecked")
		ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
		
		SubstitutableValidatableConstraint mockConstraintA = mock(SubstitutableValidatableConstraint.class);
		when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
		SubstitutableValidatableConstraint mockConstraintB = mock(SubstitutableValidatableConstraint.class);
		when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
		
		// Create the builder under test
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder(mockConstraintValidator)
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
		@SuppressWarnings("unchecked")
		ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
		
		SubstitutableValidatableConstraint mockConstraintA = mock(SubstitutableValidatableConstraint.class);
		when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
		SubstitutableValidatableConstraint mockConstraintB = mock(SubstitutableValidatableConstraint.class);
		when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
		
		Set<SubstitutableValidatableConstraint> mockConstraintsOne = new HashSet<SubstitutableValidatableConstraint>();
		mockConstraintsOne.add(mockConstraintA);
		mockConstraintsOne.add(mockConstraintB);

		SubstitutableValidatableConstraint mockConstraintC = mock(SubstitutableValidatableConstraint.class);
		when(mockConstraintC.validate(mockConstraintValidator)).thenReturn(true);
		SubstitutableValidatableConstraint mockConstraintD = mock(SubstitutableValidatableConstraint.class);
		when(mockConstraintD.validate(mockConstraintValidator)).thenReturn(true);
	
		Set<SubstitutableValidatableConstraint> mockConstraintsTwo = new HashSet<SubstitutableValidatableConstraint>();
		mockConstraintsTwo.add(mockConstraintC);
		mockConstraintsTwo.add(mockConstraintD);
		
		// Create the builder under test
		SimpleTaskNetworkBuilder builder = new SimpleTaskNetworkBuilder(mockConstraintValidator)
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
	@Test
	public void testBuild() throws InvalidConstraint {
	    
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		SubstitutableValidatableConstraint mockConstraintA = mock(SubstitutableValidatableConstraint.class);
		@SuppressWarnings("unchecked")
		ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
		
		SubstitutableTaskNetwork taskNetwork = new SimpleTaskNetworkBuilder(mockConstraintValidator)
				.addTask(mockTaskA)
				.addConstraint(mockConstraintA)
				.build();
		
		assertEquals(1, taskNetwork.getTasks().size());
		assertTrue(taskNetwork.getTasks().contains(mockTaskA));
		assertEquals(1, taskNetwork.getConstraints().size());
		assertTrue(taskNetwork.getConstraints().contains(mockConstraintA));
	}
	
	/**
	 * Test building by copying.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testCopy() throws InvalidConstraint {
	    
	    SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
	    SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
        SubstitutableValidatableConstraint mockConstraintA = mock(SubstitutableValidatableConstraint.class);
        Set<SubstitutableTask> mockTasks = new HashSet<SubstitutableTask>();
        mockTasks.add(mockTaskA);
        Set<SubstitutableValidatableConstraint> mockConstraints = new HashSet<SubstitutableValidatableConstraint>();
        mockConstraints.add(mockConstraintA);
        when(mockTaskNetwork.getTasks()).thenReturn(mockTasks);
        when(mockTaskNetwork.getConstraints()).thenReturn(mockConstraints);
        
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        
	    SubstitutableTaskNetwork taskNetwork = new SimpleTaskNetworkBuilder(mockConstraintValidator)
                .copy(mockTaskNetwork)
                .build();
	    
	    assertEquals(1, taskNetwork.getTasks().size());
	    assertTrue(taskNetwork.getTasks().contains(mockTaskA));
	    assertEquals(1, taskNetwork.getConstraints().size());
	    assertTrue(taskNetwork.getConstraints().contains(mockConstraintA));
	}
	
	 /**
     * Test apply.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public void testApply() throws InvalidConstraint {
        
        SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
        SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockTasks = new HashSet<SubstitutableTask>();
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        SubstitutableValidatableConstraint mockConstraintA = mock(SubstitutableValidatableConstraint.class);
        SubstitutableValidatableConstraint mockConstraintB = mock(SubstitutableValidatableConstraint.class);
        Set<SubstitutableValidatableConstraint> mockConstraints = new HashSet<SubstitutableValidatableConstraint>();
        mockConstraints.add(mockConstraintA);
        mockConstraints.add(mockConstraintB);
        
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
        when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
        
        @SuppressWarnings("unchecked")
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);
        
        SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
        when(mockTaskNetwork.getTasks()).thenReturn(mockTasks);
        when(mockTaskNetwork.getConstraints()).thenReturn(mockConstraints);
        
        SubstitutableTaskNetwork taskNetwork = new SimpleTaskNetworkBuilder(mockConstraintValidator)
                .copy(mockTaskNetwork)
                .apply(mockSubstituter)
                .build();
        
        assertEquals(2, taskNetwork.getTasks().size());
        assertTrue(taskNetwork.getTasks().contains(mockTaskA));
        assertTrue(taskNetwork.getTasks().contains(mockTaskB));
        assertEquals(2, taskNetwork.getConstraints().size());
        assertTrue(taskNetwork.getConstraints().contains(mockConstraintA));
        assertTrue(taskNetwork.getConstraints().contains(mockConstraintB));
    }
	
}
