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
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskBuilder;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkBuilder;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 * 
 */
public class SimpleTaskNetworkTest {

    /**
     * Test method for
     * {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}
     * .
     */
    @Test
    public void testIsPrimitiveNoTasks() {

        Set<ImmutableTask> tasks = new HashSet<ImmutableTask>();

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);
        
        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test method for
     * {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}
     * .
     */
    @Test
    public void testIsPrimitiveOnePrimitiveTask() {
        
        Set<ImmutableTask> tasks = new HashSet<ImmutableTask>();
        ImmutableTask mockPrimitiveTask = mock(ImmutableTask.class);
        when(mockPrimitiveTask.isPrimitive()).thenReturn(true);
        tasks.add(mockPrimitiveTask);

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);
        
        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);

        assertTrue(taskNetwork.isPrimitive());
    }

    /**
     * Test method for
     * {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}
     * .
     */
    @Test
    public void testIsPrimitiveOneNonPrimitiveTask() {
        
        Set<ImmutableTask> tasks = new HashSet<ImmutableTask>();
        ImmutableTask mockNonPrimitiveTask = mock(ImmutableTask.class);
        when(mockNonPrimitiveTask.isPrimitive()).thenReturn(false);
        tasks.add(mockNonPrimitiveTask);

        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);

        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
        
        assertFalse(taskNetwork.isPrimitive());
    }

    /**
     * Test method for
     * {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}
     * .
     */
    @Test
    public void testIsPrimitiveManyTasksAllPrimitive() {

        Set<ImmutableTask> tasks = new HashSet<ImmutableTask>();
        ImmutableTask mockPrimitiveTaskOne = mock(ImmutableTask.class);
        when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
        ImmutableTask mockPrimitiveTaskTwo = mock(ImmutableTask.class);
        when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(true);
        ImmutableTask mockPrimitiveTaskThree = mock(ImmutableTask.class);
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
     * Test method for
     * {@link org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork#isPrimitive()}
     * .
     */
    @Test
    public void testIsPrimitiveManyTasksOneNonPrimitive() {

        Set<ImmutableTask> tasks = new HashSet<ImmutableTask>();
        ImmutableTask mockPrimitiveTaskOne = mock(ImmutableTask.class);
        when(mockPrimitiveTaskOne.isPrimitive()).thenReturn(true);
        ImmutableTask mockPrimitiveTaskTwo = mock(ImmutableTask.class);
        when(mockPrimitiveTaskTwo.isPrimitive()).thenReturn(false);
        ImmutableTask mockPrimitiveTaskThree = mock(ImmutableTask.class);
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
    public void testSimpleTaskNetworkBuilder() {
        // Create the builder under test
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        ImmutableTaskNetworkBuilder builder = new SimpleTaskNetwork.Builder(mockConstraintValidator);
        
        // Check that the argument and constraint lists have been initialised
        assertTrue(builder.getTasks().isEmpty());
        assertTrue(builder.getConstraints().isEmpty());
    }

    /**
     * Test adding single tasks.
     */
    @Test
    public void testAddTask() {
        ImmutableTask mockTaskA = mock(ImmutableTask.class);
        ImmutableTask mockTaskB = mock(ImmutableTask.class);
        
        // Create the builder under test
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
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
    public void testAddTasks() {
        ImmutableTask mockTaskA = mock(ImmutableTask.class);
        ImmutableTask mockTaskB = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasksOne = new HashSet<ImmutableTask>();
        mockTasksOne.add(mockTaskA);
        mockTasksOne.add(mockTaskB);

        ImmutableTask mockTaskC = mock(ImmutableTask.class);
        ImmutableTask mockTaskD = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasksTwo = new HashSet<ImmutableTask>();
        mockTasksTwo.add(mockTaskC);
        mockTasksTwo.add(mockTaskD);
        
        // Create the builder under test
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        ImmutableTaskNetworkBuilder builder = new SimpleTaskNetwork.Builder(mockConstraintValidator)
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
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        
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
    public void testAddConstraints() throws InvalidConstraint {
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        
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
        
        ImmutableTask mockTaskA = mock(ImmutableTask.class);
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
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
    public void testCopy() throws InvalidConstraint {
        
        ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
        ImmutableTask mockTaskA = mock(ImmutableTask.class);
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        mockTasks.add(mockTaskA);
        Set<ImmutableConstraint<?>> mockConstraints = new HashSet<ImmutableConstraint<?>>();
        mockConstraints.add(mockConstraintA);
        when(mockTaskNetwork.getTasks()).thenReturn(mockTasks);
        when(mockTaskNetwork.getConstraints()).thenReturn(mockConstraints);
        
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        
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
    public void testApply() throws InvalidConstraint {
        
        ImmutableTask mockTaskA = mock(ImmutableTask.class);
        ImmutableTask mockTaskB = mock(ImmutableTask.class);
        ImmutableTask mockTaskC = mock(ImmutableTask.class);
        ImmutableTask mockTaskD = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        
        ImmutableConstraint<?> mockConstraintA = mock(ImmutableConstraint.class);
        ImmutableConstraint<?> mockConstraintB = mock(ImmutableConstraint.class);
        ImmutableConstraint<?> mockConstraintC = mock(ImmutableConstraint.class);
        ImmutableConstraint<?> mockConstraintD = mock(ImmutableConstraint.class);
        Set<ImmutableConstraint<?>> mockConstraints = new HashSet<ImmutableConstraint<?>>();
        mockConstraints.add(mockConstraintA);
        mockConstraints.add(mockConstraintB);
 
        ConstraintValidator<ImmutableTask, ImmutableCondition> mockConstraintValidator = mock(ConstraintValidator.class);
        when(mockConstraintA.validate(mockConstraintValidator)).thenReturn(true);
        when(mockConstraintB.validate(mockConstraintValidator)).thenReturn(true);
        
        Map<Term, Term> mockSubstitution = mock(Map.class);
 
        ImmutableTaskNetwork initialTaskNetwork = new SimpleTaskNetwork.Builder(mockConstraintValidator)
                .addTasks(mockTasks)
                .addConstraints(mockConstraints)
                .build();
        
        ImmutableTaskBuilder mockTaskBuilderA = mock(ImmutableTaskBuilder.class);
        ImmutableTaskBuilder mockTaskBuilderA1 = mock(ImmutableTaskBuilder.class);
        when(mockTaskA.createCopyBuilder()).thenReturn(mockTaskBuilderA);
        when(mockTaskBuilderA.apply(mockSubstitution)).thenReturn(mockTaskBuilderA1);
        when(mockTaskBuilderA1.build()).thenReturn(mockTaskC);
        
        ImmutableTaskBuilder mockTaskBuilderB = mock(ImmutableTaskBuilder.class);
        ImmutableTaskBuilder mockTaskBuilderB1 = mock(ImmutableTaskBuilder.class);
        when(mockTaskB.createCopyBuilder()).thenReturn(mockTaskBuilderB);
        when(mockTaskBuilderB.apply(mockSubstitution)).thenReturn(mockTaskBuilderB1);
        when(mockTaskBuilderB1.build()).thenReturn(mockTaskD);
        
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
