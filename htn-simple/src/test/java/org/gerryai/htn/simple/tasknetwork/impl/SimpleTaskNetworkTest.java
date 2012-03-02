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
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.ImmutableConstraintBuilder;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskBuilder;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkBuilder;
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
     * Test substitution is applied.
     */
    //TODO: Remove this warning supression
    @SuppressWarnings("unchecked")
    @Test
    public void testApply() {
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);
        ImmutableTaskNetworkBuilder mockBuilder = mock(ImmutableTaskNetworkBuilder.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        ImmutableTask mockTaskA = mock(ImmutableTask.class);
        ImmutableTask mockTaskB = mock(ImmutableTask.class);
        ImmutableTask mockTaskC = mock(ImmutableTask.class);
        ImmutableTask mockTaskD = mock(ImmutableTask.class);
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        when(mockBuilder.getTasks()).thenReturn(mockTasks);
        ImmutableTaskBuilder<SubstitutableTerm, ImmutableTask> mockTaskBuilderA = mock(ImmutableTaskBuilder.class);
        ImmutableTaskBuilder<SubstitutableTerm, ImmutableTask> mockTaskBuilderB = mock(ImmutableTaskBuilder.class);
        when(mockTaskBuilderA.apply(mockSubstituter)).thenReturn(mockTaskBuilderA);
        when(mockTaskBuilderB.apply(mockSubstituter)).thenReturn(mockTaskBuilderB);
        when(mockTaskBuilderA.build()).thenReturn(mockTaskC);
        when(mockTaskBuilderB.build()).thenReturn(mockTaskD);
        when(mockTaskA.createCopyBuilder()).thenReturn(mockTaskBuilderA);
        when(mockTaskB.createCopyBuilder()).thenReturn(mockTaskBuilderB);
        
        @SuppressWarnings("rawtypes")
        ImmutableConstraint mockConstraintA = mock(ImmutableConstraint.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraint mockConstraintB = mock(ImmutableConstraint.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraint mockConstraintC = mock(ImmutableConstraint.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraint mockConstraintD = mock(ImmutableConstraint.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderA = mock(ImmutableConstraintBuilder.class);
        @SuppressWarnings("rawtypes")
        ImmutableConstraintBuilder mockConstraintBuilderB = mock(ImmutableConstraintBuilder.class);
        when(mockConstraintA.createCopyBuilder()).thenReturn(mockConstraintBuilderA);
        when(mockConstraintB.createCopyBuilder()).thenReturn(mockConstraintBuilderB);
        when(mockConstraintBuilderA.apply(mockSubstituter)).thenReturn(mockConstraintBuilderA);
        when(mockConstraintBuilderB.apply(mockSubstituter)).thenReturn(mockConstraintBuilderB);
        when(mockConstraintBuilderA.build()).thenReturn(mockConstraintC);
        when(mockConstraintBuilderB.build()).thenReturn(mockConstraintD);
        Set<ImmutableConstraint<?>> mockConstraints = new HashSet<ImmutableConstraint<?>>();
        mockConstraints.add(mockConstraintA);
        mockConstraints.add(mockConstraintB);
        when(mockBuilder.getConstraints()).thenReturn(mockConstraints);

        SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork(mockBuilder);

        taskNetwork.apply(mockSubstituter);

        assertTrue(taskNetwork.getTasks().contains(mockTaskC));
        assertTrue(taskNetwork.getTasks().contains(mockTaskD));
        assertTrue(taskNetwork.getConstraints().contains(mockConstraintC));
        assertTrue(taskNetwork.getConstraints().contains(mockConstraintD));
    }
}
