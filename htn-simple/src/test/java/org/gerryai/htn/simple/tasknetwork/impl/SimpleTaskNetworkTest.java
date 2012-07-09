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
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.ImmutableConstraintBuilder;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkBuilder;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 * 
 */
public class SimpleTaskNetworkTest {

    /**
     * Test that a task network with no tasks is considered primitive.
     */
    @Test
    public final void testIsPrimitiveNoTasks() {

        Set<Task> tasks = new HashSet<Task>();

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);
        
        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network containing one primitive task is considered primitive.
     */
    @Test
    public final void testIsPrimitiveOnePrimitiveTask() {
        
        Set<Task> tasks = new HashSet<Task>();
        Task mockPrimitiveTask = mock(Task.class);
        when(mockPrimitiveTask.isPrimitive()).thenReturn(true);
        tasks.add(mockPrimitiveTask);

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);
        
        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network with one non-primitive task is considered non-primitive.
     */
    @Test
    public final void testIsPrimitiveOneNonPrimitiveTask() {
        
        Set<Task> tasks = new HashSet<Task>();
        Task mockNonPrimitiveTask = mock(Task.class);
        when(mockNonPrimitiveTask.isPrimitive()).thenReturn(false);
        tasks.add(mockNonPrimitiveTask);

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);

        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
        
        assertFalse(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network containing all primitive tasks is considered primitive.
     */
    @Test
    public final void testIsPrimitiveManyTasksAllPrimitive() {

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

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);
        
        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test that a task network with many primitive taska and one non-primitive task is considered non-primitive.
     */
    @Test
    public final void testIsPrimitiveManyTasksOneNonPrimitive() {

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

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);

        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
        
        assertFalse(taskNetwork.isPrimitive());
    }

    /**
     * Test builder construction.
     */
    @Test
    public final void testSimpleTaskNetworkBuilder() {
        // Create the builder under test
        ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
        ImmutableTaskNetworkBuilder builder = new SimpleTaskNetwork.Builder(mockConstraintValidator);
        
        // Check that the argument and constraint lists have been initialised
        assertTrue(builder.getTasks().isEmpty());
        assertTrue(builder.getConstraints().isEmpty());
    }

    /**
     * Test adding single tasks.
     */
    @Test
    public final void testAddTask() {
        Task mockTaskA = mock(Task.class);
        Task mockTaskB = mock(Task.class);
        
        // Create the builder under test
        ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
        ImmutableTaskNetworkBuilder builder = new SimpleTaskNetwork.Builder(mockConstraintValidator)
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
    public final void testAddTasks() {
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
        ImmutableTaskNetworkBuilder builder = new SimpleTaskNetwork.Builder(mockConstraintValidator)
                .addTasks(mockTasksOne)
                .addTasks(mockTasksTwo);
        
        Set<Task> expectedTasks = new HashSet<Task>();
        expectedTasks.add(mockTaskA);
        expectedTasks.add(mockTaskB);
        expectedTasks.add(mockTaskC);
        expectedTasks.add(mockTaskD);
        
        // Check that the arguments have been added in the correct order
        assertEquals(expectedTasks, builder.getTasks());
    }

    /**
     * Test adding single constraints.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public final void testAddConstraint() throws InvalidConstraint {
        ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
        
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
        ImmutableConstraint<?> mockConstraintB = mock(ImmutableConstraint.class);
        when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
        
        // Create the builder under test
        ImmutableTaskNetworkBuilder builder = new SimpleTaskNetwork.Builder(mockConstraintValidator)
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
    public final void testAddConstraints() throws InvalidConstraint {
        ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
        
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
        ImmutableConstraint<?> mockConstraintB = mock(ImmutableConstraint.class);
        when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
        
        Set<ImmutableConstraint<?>> mockConstraintsOne = new HashSet<ImmutableConstraint<?>>();
        mockConstraintsOne.add(mockConstraintA);
        mockConstraintsOne.add(mockConstraintB);

        ImmutableConstraint<?> mockConstraintC = mock(ImmutableConstraint.class);
        when(mockConstraintC.validate(mockConstraintValidator)).thenReturn(true);
        ImmutableConstraint<?> mockConstraintD = mock(ImmutableConstraint.class);
        when(mockConstraintD.validate(mockConstraintValidator)).thenReturn(true);
    
        Set<ImmutableConstraint<?>> mockConstraintsTwo = new HashSet<ImmutableConstraint<?>>();
        mockConstraintsTwo.add(mockConstraintC);
        mockConstraintsTwo.add(mockConstraintD);
        
        // Create the builder under test
        ImmutableTaskNetworkBuilder builder = new SimpleTaskNetwork.Builder(mockConstraintValidator)
                .addConstraints(mockConstraintsOne)
                .addConstraints(mockConstraintsTwo);
        
        Set<ImmutableConstraint<?>> expectedConstraints = new HashSet<ImmutableConstraint<?>>();
        expectedConstraints.add(mockConstraintA);
        expectedConstraints.add(mockConstraintB);        
        expectedConstraints.add(mockConstraintC);
        expectedConstraints.add(mockConstraintD);
                
        // Check that the arguments have been added in the correct order
        assertEquals(expectedConstraints, builder.getConstraints());
    }
    
    /**
     * Test build.
     * @throws InvalidConstraint only if the test fails
     */
    @Test
    public final void testBuild() throws InvalidConstraint {
        
        Task mockTaskA = mock(Task.class);
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
        when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
        
        ImmutableTaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockConstraintValidator)
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
    public final void testCopy() throws InvalidConstraint {
        
        ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
        Task mockTaskA = mock(Task.class);
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTaskA);
        Set<ImmutableConstraint<?>> mockConstraints = new HashSet<ImmutableConstraint<?>>();
        mockConstraints.add(mockConstraintA);
        when(mockTaskNetwork.getTasks()).thenReturn(mockTasks);
        when(mockTaskNetwork.getConstraints()).thenReturn(mockConstraints);
        
        ConstraintValidator mockConstraintValidator = mock(ConstraintValidator.class);
        
        ImmutableTaskNetwork taskNetwork = new SimpleTaskNetwork.Builder(mockConstraintValidator)
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
    @SuppressWarnings("unchecked")
    @Test
    public final void testApply() throws InvalidConstraint {
        
        Task mockTaskA = mock(Task.class);
        Task mockTaskB = mock(Task.class);
        Task mockTaskC = mock(Task.class);
        Task mockTaskD = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        ImmutableConstraint<?> mockConstraintB = mock(ImmutableConstraint.class);
        ImmutableConstraint<?> mockConstraintC = mock(ImmutableConstraint.class);
        ImmutableConstraint<?> mockConstraintD = mock(ImmutableConstraint.class);
        Set<ImmutableConstraint<?>> mockConstraints = new HashSet<ImmutableConstraint<?>>();
        mockConstraints.add(mockConstraintA);
        mockConstraints.add(mockConstraintB);
 
        ConstraintValidator mockConstraintValidator
        		= mock(ConstraintValidator.class);
        when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
        when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
        
        Map<Term, Term> mockSubstitution = mock(Map.class);
 
        ImmutableTaskNetwork initialTaskNetwork = new SimpleTaskNetwork.Builder(mockConstraintValidator)
                .addTasks(mockTasks)
                .addConstraints(mockConstraints)
                .build();
        
        when(mockTaskA.applyToCopy(mockSubstitution)).thenReturn(mockTaskC);
        when(mockTaskB.applyToCopy(mockSubstitution)).thenReturn(mockTaskD);
        
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderA = mock(ImmutableConstraintBuilder.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderA1 = mock(ImmutableConstraintBuilder.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderA2 = mock(ImmutableConstraintBuilder.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderA3 = mock(ImmutableConstraintBuilder.class);
        when(mockConstraintA.createCopyBuilder()).thenReturn(mockConstraintBuilderA);
        when(mockConstraintBuilderA.apply(mockSubstitution)).thenReturn(mockConstraintBuilderA1);
        when(mockConstraintBuilderA1.replace(mockTaskA, mockTaskC)).thenReturn(mockConstraintBuilderA2);
        when(mockConstraintBuilderA2.replace(mockTaskB, mockTaskD)).thenReturn(mockConstraintBuilderA3);
        when(mockConstraintBuilderA2.replace(mockTaskA, mockTaskC)).thenReturn(mockConstraintBuilderA3);
        when(mockConstraintBuilderA1.replace(mockTaskB, mockTaskD)).thenReturn(mockConstraintBuilderA2);
        when(mockConstraintBuilderA3.build()).thenReturn(mockConstraintC);
        
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderB = mock(ImmutableConstraintBuilder.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderB1 = mock(ImmutableConstraintBuilder.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderB2 = mock(ImmutableConstraintBuilder.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderB3 = mock(ImmutableConstraintBuilder.class);
        when(mockConstraintB.createCopyBuilder()).thenReturn(mockConstraintBuilderB);
        when(mockConstraintBuilderB.apply(mockSubstitution)).thenReturn(mockConstraintBuilderB1);
        when(mockConstraintBuilderB1.replace(mockTaskA, mockTaskC)).thenReturn(mockConstraintBuilderB2);
        when(mockConstraintBuilderB2.replace(mockTaskB, mockTaskD)).thenReturn(mockConstraintBuilderB3);
        when(mockConstraintBuilderB2.replace(mockTaskA, mockTaskC)).thenReturn(mockConstraintBuilderB3);
        when(mockConstraintBuilderB1.replace(mockTaskB, mockTaskD)).thenReturn(mockConstraintBuilderB2);
        when(mockConstraintBuilderB3.build()).thenReturn(mockConstraintD);
        
        ImmutableTaskNetwork taskNetwork = initialTaskNetwork.createCopyBuilder(mockConstraintValidator)
                .apply(mockSubstitution)
                .build();
        
        assertEquals(2, taskNetwork.getTasks().size());
        assertTrue(taskNetwork.getTasks().contains(mockTaskC));
        assertTrue(taskNetwork.getTasks().contains(mockTaskD));
        assertEquals(2, taskNetwork.getConstraints().size());
        assertTrue(taskNetwork.getConstraints().contains(mockConstraintC));
        assertTrue(taskNetwork.getConstraints().contains(mockConstraintD));
    }
}
