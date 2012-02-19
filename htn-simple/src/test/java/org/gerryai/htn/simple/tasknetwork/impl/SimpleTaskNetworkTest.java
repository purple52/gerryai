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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.SubstitutableValidatableConstraint;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
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

        Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
        @SuppressWarnings("unchecked")
        TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, SubstitutableValidatableConstraint> mockBuilder = mock(TaskNetworkBuilder.class);
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
        
        Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
        SubstitutableTask mockPrimitiveTask = mock(SubstitutableTask.class);
        when(mockPrimitiveTask.isPrimitive()).thenReturn(true);
        tasks.add(mockPrimitiveTask);
        @SuppressWarnings("unchecked")
        TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, SubstitutableValidatableConstraint> mockBuilder = mock(TaskNetworkBuilder.class);
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
        
        Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
        SubstitutableTask mockNonPrimitiveTask = mock(SubstitutableTask.class);
        when(mockNonPrimitiveTask.isPrimitive()).thenReturn(false);
        tasks.add(mockNonPrimitiveTask);
        @SuppressWarnings("unchecked")
        TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, SubstitutableValidatableConstraint> mockBuilder = mock(TaskNetworkBuilder.class);
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
        @SuppressWarnings("unchecked")
        TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, SubstitutableValidatableConstraint> mockBuilder = mock(TaskNetworkBuilder.class);
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
        @SuppressWarnings("unchecked")
        TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, SubstitutableValidatableConstraint> mockBuilder = mock(TaskNetworkBuilder.class);
        when(mockBuilder.getTasks()).thenReturn(tasks);

        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);
        
        assertFalse(taskNetwork.isPrimitive());
    }

    /**
     * Test substitution is applied.
     */
    @Test
    public void testApply() {
        @SuppressWarnings("unchecked")
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);
        @SuppressWarnings("unchecked")
        TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, SubstitutableValidatableConstraint> mockBuilder = mock(TaskNetworkBuilder.class);
        Set<SubstitutableTask> mockTasks = new HashSet<SubstitutableTask>();
        SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
        SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        when(mockBuilder.getTasks()).thenReturn(mockTasks);
        Set<SubstitutableValidatableConstraint> mockConstraints = new HashSet<SubstitutableValidatableConstraint>();
        SubstitutableValidatableConstraint mockConstraintA = mock(SubstitutableValidatableConstraint.class);
        SubstitutableValidatableConstraint mockConstraintB = mock(SubstitutableValidatableConstraint.class);
        mockConstraints.add(mockConstraintA);
        mockConstraints.add(mockConstraintB);
        when(mockBuilder.getConstraints()).thenReturn(mockConstraints);

        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);

        taskNetwork.apply(mockSubstituter);

        verify(mockTaskA).apply(mockSubstituter);
        verify(mockTaskB).apply(mockSubstituter);
        verify(mockConstraintA).apply(mockSubstituter);
        verify(mockConstraintB).apply(mockSubstituter);
    }
}
