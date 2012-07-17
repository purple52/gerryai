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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * Unit tests for SimpleTaskNetwork.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkTest {

	/**
	 * Test that an empty network contains empty lists.
	 */
	@Test
	public final void testEmptyTaskNetwork() {
		ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
		TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory)
				.build();
		
		assertTrue(taskNetwork.getTasks().isEmpty());
		assertTrue(taskNetwork.getAfterConstraints().isEmpty());
		assertTrue(taskNetwork.getBeforeConstraints().isEmpty());
		assertTrue(taskNetwork.getBetweenConstraints().isEmpty());
		assertTrue(taskNetwork.getPrecedenceConstraints().isEmpty());
	}
	
    /**
     * Test that a task network with no tasks is considered primitive.
     */
    @Test
    public final void testIsPrimitiveNoTasks() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory)
        		.build();

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network containing one primitive task is considered primitive.
     */
    @Test
    public final void testIsPrimitiveOnePrimitiveTask() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        Set<Task> mockTasks = new HashSet<Task>();
        Task mockPrimitiveTask = mock(Task.class);
        when(mockPrimitiveTask.isPrimitive()).thenReturn(true);
        mockTasks.add(mockPrimitiveTask);
        when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);
        
        TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory)
        		.build();

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network with one non-primitive task is considered non-primitive.
     */
    @Test
    public final void testIsPrimitiveOneNonPrimitiveTask() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        Set<Task> mockTasks = new HashSet<Task>();
        Task mockNonPrimitiveTask = mock(Task.class);
        when(mockNonPrimitiveTask.isPrimitive()).thenReturn(false);
        mockTasks.add(mockNonPrimitiveTask);
        when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);
        
        TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory)
        		.build();
        
        assertFalse(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network containing all primitive tasks is considered primitive.
     */
    @Test
    public final void testIsPrimitiveManyTasksAllPrimitive() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
		ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        Set<Task> mockTasks = new HashSet<Task>();
        Task mockPrimitiveTaskOne = mock(Task.class);
        when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
        Task mockPrimitiveTaskTwo = mock(Task.class);
        when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(true);
        Task mockPrimitiveTaskThree = mock(Task.class);
        when(mockPrimitiveTaskThree.isPrimitive()).thenReturn(true);
        mockTasks.add(mockPrimitiveTaskOne);
        mockTasks.add(mockPrimitiveTaskTwo);
        mockTasks.add(mockPrimitiveTaskThree);
        when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);
        
        TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory)
        		.build();

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network with many primitive tasks and one non-primitive task is considered non-primitive.
     */
    @Test
    public final void testIsPrimitiveManyTasksOneNonPrimitive() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        Set<Task> mockTasks = new HashSet<Task>();
        Task mockPrimitiveTaskOne = mock(Task.class);
        when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
        Task mockPrimitiveTaskTwo = mock(Task.class);
        when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(false);
        Task mockPrimitiveTaskThree = mock(Task.class);
        when(mockPrimitiveTaskThree.isPrimitive()).thenReturn(true);
        mockTasks.add(mockPrimitiveTaskOne);
        mockTasks.add(mockPrimitiveTaskTwo);
        mockTasks.add(mockPrimitiveTaskThree);
        when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);

        TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory)
        		.build();
        
        assertFalse(taskNetwork.isPrimitive());
    }

    /**
     * Test that builder returns tasks from validator.
     */
    @Test
    public final void testGetTasks() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        Task mockTaskA = mock(Task.class);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);
        
        // Create the builder under test
        TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory)
        		.build();
        
        assertEquals(mockTasks, taskNetwork.getTasks());
    }
    
    /**
     * Test adding single tasks.
     */
    @Test
    public final void testAddTask() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        Task mockTaskA = mock(Task.class);
        Task mockTaskB = mock(Task.class);
        
        new SimpleTaskNetwork.Builder(mockValidatorFactory)
                .addTask(mockTaskA)
                .addTask(mockTaskB)
                .build();
        
        verify(mockConstraintValidator).add(mockTaskA);
        verify(mockConstraintValidator).add(mockTaskB);
    }

    /**
     * Test adding sets of tasks.
     */
    @Test
    public final void testAddTasks() {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
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
        
        new SimpleTaskNetwork.Builder(mockValidatorFactory)
                .addTasks(mockTasksOne)
                .addTasks(mockTasksTwo)
                .build();
        
        verify(mockConstraintValidator).add(mockTaskA);
        verify(mockConstraintValidator).add(mockTaskB);
        verify(mockConstraintValidator).add(mockTaskC);
        verify(mockConstraintValidator).add(mockTaskD);
    }

    /**
     * Test adding single after constraints.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public final void testAddAfterConstraints() throws InvalidConstraint {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        AfterConstraint mockConstraintA = mock(AfterConstraint.class);
        AfterConstraint mockConstraintB = mock(AfterConstraint.class);
        
        new SimpleTaskNetwork.Builder(mockValidatorFactory)
                .addAfterConstraint(mockConstraintA)
                .addAfterConstraint(mockConstraintB)
                .build();
        
        verify(mockConstraintValidator).add(mockConstraintA);
        verify(mockConstraintValidator).add(mockConstraintB);
    }

    /**
     * Test adding single before constraints.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public final void testAddBeforeConstraints() throws InvalidConstraint {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        BeforeConstraint mockConstraintA = mock(BeforeConstraint.class);
        BeforeConstraint mockConstraintB = mock(BeforeConstraint.class);
        
        new SimpleTaskNetwork.Builder(mockValidatorFactory)
                .addBeforeConstraint(mockConstraintA)
                .addBeforeConstraint(mockConstraintB)
                .build();
        
        verify(mockConstraintValidator).add(mockConstraintA);
        verify(mockConstraintValidator).add(mockConstraintB);
    }
 
    /**
     * Test adding single between constraints.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public final void testAddBetweenConstraint() throws InvalidConstraint {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        BetweenConstraint mockConstraintA = mock(BetweenConstraint.class);
        BetweenConstraint mockConstraintB = mock(BetweenConstraint.class);
        
        new SimpleTaskNetwork.Builder(mockValidatorFactory)
                .addBetweenConstraint(mockConstraintA)
                .addBetweenConstraint(mockConstraintB)
                .build();
        
        verify(mockConstraintValidator).add(mockConstraintA);
        verify(mockConstraintValidator).add(mockConstraintB);
    }

    /**
     * Test adding single precedence constraints.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public final void testAddPrecedenceConstraint() throws InvalidConstraint {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
        PrecedenceConstraint mockConstraintA = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockConstraintB = mock(PrecedenceConstraint.class);
        
        new SimpleTaskNetwork.Builder(mockValidatorFactory)
                .addPrecedenceConstraint(mockConstraintA)
                .addPrecedenceConstraint(mockConstraintB)
                .build();
        
        verify(mockConstraintValidator).add(mockConstraintA);
        verify(mockConstraintValidator).add(mockConstraintB);
    }
    
    /**
     * Test building by copying.
     * @throws InvalidConstraint only if test fails
     */
    @Test
    public final void testCopy() throws InvalidConstraint {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		    	
    	Task mockTaskA = mock(Task.class);
    	Task mockTaskB = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        
        AfterConstraint mockAfterConstraintA = mock(AfterConstraint.class);
        AfterConstraint mockAfterConstraintB = mock(AfterConstraint.class);
        Set<AfterConstraint> mockAfterConstraints = new HashSet<AfterConstraint>();
        mockAfterConstraints.add(mockAfterConstraintA);
        mockAfterConstraints.add(mockAfterConstraintB);
        
        BeforeConstraint mockBeforeConstraintA = mock(BeforeConstraint.class);
        BeforeConstraint mockBeforeConstraintB = mock(BeforeConstraint.class);
        Set<BeforeConstraint> mockBeforeConstraints = new HashSet<BeforeConstraint>();
        mockBeforeConstraints.add(mockBeforeConstraintA);  
        mockBeforeConstraints.add(mockBeforeConstraintB);
        
        BetweenConstraint mockBetweenConstraintA = mock(BetweenConstraint.class);
        BetweenConstraint mockBetweenConstraintB = mock(BetweenConstraint.class);
        Set<BetweenConstraint> mockBetweenConstraints = new HashSet<BetweenConstraint>();
        mockBetweenConstraints.add(mockBetweenConstraintA);
        mockBetweenConstraints.add(mockBetweenConstraintB);
        
        PrecedenceConstraint mockPrecedenceConstraintA = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockPrecedenceConstraintB = mock(PrecedenceConstraint.class);
        Set<PrecedenceConstraint> mockPrecedenceConstraints = new HashSet<PrecedenceConstraint>();
        mockPrecedenceConstraints.add(mockPrecedenceConstraintA);  
        mockPrecedenceConstraints.add(mockPrecedenceConstraintB);
        
        TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
        when(mockTaskNetwork.getTasks()).thenReturn(mockTasks);
        when(mockTaskNetwork.getAfterConstraints()).thenReturn(mockAfterConstraints);
        when(mockTaskNetwork.getBeforeConstraints()).thenReturn(mockBeforeConstraints);
        when(mockTaskNetwork.getBetweenConstraints()).thenReturn(mockBetweenConstraints);
        when(mockTaskNetwork.getPrecedenceConstraints()).thenReturn(mockPrecedenceConstraints);
        when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);
        when(mockConstraintValidator.getAfterConstraints()).thenReturn(mockAfterConstraints);
        when(mockConstraintValidator.getBeforeConstraints()).thenReturn(mockBeforeConstraints);
        when(mockConstraintValidator.getBetweenConstraints()).thenReturn(mockBetweenConstraints);
        when(mockConstraintValidator.getPrecedenceConstraints()).thenReturn(mockPrecedenceConstraints);
        
        
        TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory, mockTaskNetwork)
                .build();
        
        verify(mockConstraintValidator).add(mockTaskA);
        verify(mockConstraintValidator).add(mockTaskB);
        assertEquals(2, taskNetwork.getTasks().size());
        assertTrue(taskNetwork.getTasks().contains(mockTaskA));
        assertTrue(taskNetwork.getTasks().contains(mockTaskB));
        
        verify(mockConstraintValidator).add(mockAfterConstraintA);
        verify(mockConstraintValidator).add(mockAfterConstraintB);
        assertEquals(2, taskNetwork.getAfterConstraints().size());
        assertTrue(taskNetwork.getAfterConstraints().contains(mockAfterConstraintA));
        assertTrue(taskNetwork.getAfterConstraints().contains(mockAfterConstraintB));
        
        verify(mockConstraintValidator).add(mockBeforeConstraintA);
        verify(mockConstraintValidator).add(mockBeforeConstraintB);
        assertEquals(2, taskNetwork.getBeforeConstraints().size());
        assertTrue(taskNetwork.getBeforeConstraints().contains(mockBeforeConstraintA));
        assertTrue(taskNetwork.getBeforeConstraints().contains(mockBeforeConstraintB));
        
        verify(mockConstraintValidator).add(mockBetweenConstraintA);
        verify(mockConstraintValidator).add(mockBetweenConstraintB);
        assertEquals(2, taskNetwork.getBetweenConstraints().size());
        assertTrue(taskNetwork.getBetweenConstraints().contains(mockBetweenConstraintA));
        assertTrue(taskNetwork.getBetweenConstraints().contains(mockBetweenConstraintB));
        
        verify(mockConstraintValidator).add(mockPrecedenceConstraintA);
        verify(mockConstraintValidator).add(mockPrecedenceConstraintB);
        assertEquals(2, taskNetwork.getPrecedenceConstraints().size());
        assertTrue(taskNetwork.getPrecedenceConstraints().contains(mockPrecedenceConstraintA));
        assertTrue(taskNetwork.getPrecedenceConstraints().contains(mockPrecedenceConstraintB));
    }
    
	/**
	 * Test apply.
	 * @throws InvalidConstraint only if the test fails
	 */
    @Test
    public final void testApply() throws InvalidConstraint {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
    	Task mockTaskA = mock(Task.class);
    	Task mockTaskB = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        
        AfterConstraint mockAfterConstraintA = mock(AfterConstraint.class);
        AfterConstraint mockAfterConstraintB = mock(AfterConstraint.class);
        Set<AfterConstraint> mockAfterConstraints = new HashSet<AfterConstraint>();
        mockAfterConstraints.add(mockAfterConstraintA);
        mockAfterConstraints.add(mockAfterConstraintB);
        
        BeforeConstraint mockBeforeConstraintA = mock(BeforeConstraint.class);
        BeforeConstraint mockBeforeConstraintB = mock(BeforeConstraint.class);
        Set<BeforeConstraint> mockBeforeConstraints = new HashSet<BeforeConstraint>();
        mockBeforeConstraints.add(mockBeforeConstraintA);  
        mockBeforeConstraints.add(mockBeforeConstraintB);
        
        BetweenConstraint mockBetweenConstraintA = mock(BetweenConstraint.class);
        BetweenConstraint mockBetweenConstraintB = mock(BetweenConstraint.class);
        Set<BetweenConstraint> mockBetweenConstraints = new HashSet<BetweenConstraint>();
        mockBetweenConstraints.add(mockBetweenConstraintA);
        mockBetweenConstraints.add(mockBetweenConstraintB);
        
        PrecedenceConstraint mockPrecedenceConstraintA = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockPrecedenceConstraintB = mock(PrecedenceConstraint.class);
        Set<PrecedenceConstraint> mockPrecedenceConstraints = new HashSet<PrecedenceConstraint>();
        mockPrecedenceConstraints.add(mockPrecedenceConstraintA);  
        mockPrecedenceConstraints.add(mockPrecedenceConstraintB);
        
		when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);
        when(mockConstraintValidator.getAfterConstraints()).thenReturn(mockAfterConstraints);
        when(mockConstraintValidator.getBeforeConstraints()).thenReturn(mockBeforeConstraints);
        when(mockConstraintValidator.getBetweenConstraints()).thenReturn(mockBetweenConstraints);
        when(mockConstraintValidator.getPrecedenceConstraints()).thenReturn(mockPrecedenceConstraints);
        
		@SuppressWarnings("unchecked")
        Map<Term, Term> mockSubstitution = mock(Map.class);
		
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		
		TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory, mockTaskNetwork)
        		.apply(mockSubstitution)
        		.build();

    	verify(mockConstraintValidator).apply(mockSubstitution);

        assertEquals(2, taskNetwork.getTasks().size());
        assertTrue(taskNetwork.getTasks().contains(mockTaskA));
        assertTrue(taskNetwork.getTasks().contains(mockTaskB));
        
        assertEquals(2, taskNetwork.getAfterConstraints().size());
        assertTrue(taskNetwork.getAfterConstraints().contains(mockAfterConstraintA));
        assertTrue(taskNetwork.getAfterConstraints().contains(mockAfterConstraintB));
        
        assertEquals(2, taskNetwork.getBeforeConstraints().size());
        assertTrue(taskNetwork.getBeforeConstraints().contains(mockBeforeConstraintA));
        assertTrue(taskNetwork.getBeforeConstraints().contains(mockBeforeConstraintB));
        
        assertEquals(2, taskNetwork.getBetweenConstraints().size());
        assertTrue(taskNetwork.getBetweenConstraints().contains(mockBetweenConstraintA));
        assertTrue(taskNetwork.getBetweenConstraints().contains(mockBetweenConstraintB));

        assertEquals(2, taskNetwork.getPrecedenceConstraints().size());
        assertTrue(taskNetwork.getPrecedenceConstraints().contains(mockPrecedenceConstraintA));
        assertTrue(taskNetwork.getPrecedenceConstraints().contains(mockPrecedenceConstraintB));
    }
    
    /**
     * Test replace.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public final void testReplace() throws InvalidConstraint {
    	ConstraintValidatorFactory mockValidatorFactory = mock(ConstraintValidatorFactory.class);
    	ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
		when(mockValidatorFactory.create()).thenReturn(mockConstraintValidator);
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
       
		AfterConstraint mockAfterConstraintA = mock(AfterConstraint.class);
		AfterConstraint mockAfterConstraintB = mock(AfterConstraint.class);
		Set<AfterConstraint> mockAfterConstraints = new HashSet<AfterConstraint>();
		mockAfterConstraints.add(mockAfterConstraintA);
		mockAfterConstraints.add(mockAfterConstraintB);
       
		BeforeConstraint mockBeforeConstraintA = mock(BeforeConstraint.class);
		BeforeConstraint mockBeforeConstraintB = mock(BeforeConstraint.class);
		Set<BeforeConstraint> mockBeforeConstraints = new HashSet<BeforeConstraint>();
		mockBeforeConstraints.add(mockBeforeConstraintA);  
		mockBeforeConstraints.add(mockBeforeConstraintB);
       
		BetweenConstraint mockBetweenConstraintA = mock(BetweenConstraint.class);
		BetweenConstraint mockBetweenConstraintB = mock(BetweenConstraint.class);
		Set<BetweenConstraint> mockBetweenConstraints = new HashSet<BetweenConstraint>();
		mockBetweenConstraints.add(mockBetweenConstraintA);
		mockBetweenConstraints.add(mockBetweenConstraintB);
       
		PrecedenceConstraint mockPrecedenceConstraintA = mock(PrecedenceConstraint.class);
		PrecedenceConstraint mockPrecedenceConstraintB = mock(PrecedenceConstraint.class);
		Set<PrecedenceConstraint> mockPrecedenceConstraints = new HashSet<PrecedenceConstraint>();
		mockPrecedenceConstraints.add(mockPrecedenceConstraintA);  
		mockPrecedenceConstraints.add(mockPrecedenceConstraintB);
       
		when(mockConstraintValidator.getTasks()).thenReturn(mockTasks);
		when(mockConstraintValidator.getAfterConstraints()).thenReturn(mockAfterConstraints);
		when(mockConstraintValidator.getBeforeConstraints()).thenReturn(mockBeforeConstraints);
		when(mockConstraintValidator.getBetweenConstraints()).thenReturn(mockBetweenConstraints);
		when(mockConstraintValidator.getPrecedenceConstraints()).thenReturn(mockPrecedenceConstraints);
       
		Task mockOldTask = mock(Task.class);
		TaskNetwork mockNewTaskNetwork = mock(TaskNetwork.class);
		
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		
		TaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockValidatorFactory, mockTaskNetwork)
       		.replace(mockOldTask, mockNewTaskNetwork)
       		.build();

		verify(mockConstraintValidator).replace(mockOldTask, mockNewTaskNetwork);

		assertEquals(2, taskNetwork.getTasks().size());
		assertTrue(taskNetwork.getTasks().contains(mockTaskA));
		assertTrue(taskNetwork.getTasks().contains(mockTaskB));
       
		assertEquals(2, taskNetwork.getAfterConstraints().size());
		assertTrue(taskNetwork.getAfterConstraints().contains(mockAfterConstraintA));
		assertTrue(taskNetwork.getAfterConstraints().contains(mockAfterConstraintB));
       
		assertEquals(2, taskNetwork.getBeforeConstraints().size());
		assertTrue(taskNetwork.getBeforeConstraints().contains(mockBeforeConstraintA));
		assertTrue(taskNetwork.getBeforeConstraints().contains(mockBeforeConstraintB));
       
		assertEquals(2, taskNetwork.getBetweenConstraints().size());
		assertTrue(taskNetwork.getBetweenConstraints().contains(mockBetweenConstraintA));
		assertTrue(taskNetwork.getBetweenConstraints().contains(mockBetweenConstraintB));

		assertEquals(2, taskNetwork.getPrecedenceConstraints().size());
		assertTrue(taskNetwork.getPrecedenceConstraints().contains(mockPrecedenceConstraintA));
		assertTrue(taskNetwork.getPrecedenceConstraints().contains(mockPrecedenceConstraintB));
   }
}
