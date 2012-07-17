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
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Unit tests for SipleBetweenConstraint.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleBetweenConstraintTest {

    /**
     * Test get/set preceding tasks.
     */
    @Test
    public final void testConstructor() {
        Task mockPrecedingTask = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockPrecedingTask);
        Task mockProceedingTask = mock(Task.class);
        Set<Task> mockProceedingTasks = new HashSet<Task>();
        mockProceedingTasks.add(mockProceedingTask);
        Condition mockCondition = mock(Condition.class);
        
        BetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProceedingTasks)
                .setCondition(mockCondition)
                .build();
        
        assertEquals(mockPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockProceedingTasks, constraint.getProcedingTasks());
        assertEquals(mockCondition, constraint.getCondition());
    }

    /**
     * Test construction using copy and apply.
     */
    @Test
    public final void testApply() {

        @SuppressWarnings("unchecked")
        Map<Term, Term> mockSubstitution = mock(Map.class);
        
        Task mockInitialPrecedingTask = mock(Task.class);
        Set<Task> mockInitialPrecedingTasks = new HashSet<Task>();
        mockInitialPrecedingTasks.add(mockInitialPrecedingTask);
        Task mockUpdatedPrecedingTask = mock(Task.class);
        Set<Task> mockUpdatedPrecedingTasks = new HashSet<Task>();
        mockUpdatedPrecedingTasks.add(mockUpdatedPrecedingTask);        
        when(mockInitialPrecedingTask.applyToCopy(mockSubstitution)).thenReturn(mockUpdatedPrecedingTask);
        
        Task mockInitialProceedingTask = mock(Task.class);
        Set<Task> mockInitialProceedingTasks = new HashSet<Task>();
        mockInitialProceedingTasks.add(mockInitialProceedingTask);
        Task mockUpdatedProceedingTask = mock(Task.class);
        Set<Task> mockUpdatedProceedingTasks = new HashSet<Task>();
        mockUpdatedProceedingTasks.add(mockUpdatedProceedingTask);
        when(mockInitialProceedingTask.applyToCopy(mockSubstitution)).thenReturn(mockUpdatedProceedingTask);

        Condition mockConditionA = mock(Condition.class);
        Condition mockConditionB = mock(Condition.class);
        when(mockConditionA.applyToCopy(mockSubstitution)).thenReturn(mockConditionB);

        BetweenConstraint initialConstraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockInitialPrecedingTasks)
                .addProcedingTasks(mockInitialProceedingTasks)
                .setCondition(mockConditionA)
                .build();
        
        BetweenConstraint constraint = initialConstraint.apply(mockSubstitution);

        assertEquals(mockUpdatedPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockUpdatedProceedingTasks, constraint.getProcedingTasks());
        assertEquals(mockConditionB, constraint.getCondition());
    }

    /**
     * Test construction using copy and replace.
     */
    @Test
    public final void testReplace() {
        Task mockPrecedingTask = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockPrecedingTask);
        Task mockProceedingTask = mock(Task.class);
        Set<Task> mockProceedingTasks = new HashSet<Task>();
        mockProceedingTasks.add(mockProceedingTask);
        
        Task mockTaskB = mock(Task.class);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockNewPrecedingTasks = new HashSet<Task>();
        mockNewPrecedingTasks.add(mockTaskB);
        mockNewPrecedingTasks.add(mockTaskC);
        Task mockTaskD = mock(Task.class);
        Task mockTaskE = mock(Task.class);
        Set<Task> mockNewProcedingTasks = new HashSet<Task>();
        mockNewProcedingTasks.add(mockTaskD);
        mockNewProcedingTasks.add(mockTaskE);
        
        Multimap<Task, Task> mockTaskMap = HashMultimap.create();
        mockTaskMap.put(mockPrecedingTask, mockTaskB);
        mockTaskMap.put(mockPrecedingTask, mockTaskC);
        mockTaskMap.put(mockProceedingTask, mockTaskD);
        mockTaskMap.put(mockProceedingTask, mockTaskE);
        
        Condition mockCondition = mock(Condition.class);

        BetweenConstraint initialConstraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProceedingTasks)
                .setCondition(mockCondition)
                .build();
        
        BetweenConstraint constraint = initialConstraint.replace(mockTaskMap);

        assertEquals(mockNewPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockNewProcedingTasks, constraint.getProcedingTasks());
    }
}
