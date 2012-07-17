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

import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Unit tests for SimpleBeforeConstraint.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleBeforeConstraintTest {

    /**
     * Test get/set tasks.
     */
    @Test
    public final void testConstructor() {
        Task mockTask = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTask);
        Condition mockCondition = mock(Condition.class);

        BeforeConstraint constraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        
        assertEquals(mockTasks, constraint.getTasks());
        assertEquals(mockCondition, constraint.getCondition());
    }

    /**
     * Test construction using copy and apply.
     */
    @Test
    public final void testApply() {
    	
        @SuppressWarnings("unchecked")
        Map<Term, Term> mockSubstitution = mock(Map.class);
        
        Task mockInitialTask = mock(Task.class);
        Set<Task> mockInitialTasks = new HashSet<Task>();
        mockInitialTasks.add(mockInitialTask);
        Task mockUpdatedTask = mock(Task.class);
        Set<Task> mockUpdatedTasks = new HashSet<Task>();
        mockUpdatedTasks.add(mockUpdatedTask);        
        when(mockInitialTask.applyToCopy(mockSubstitution)).thenReturn(mockUpdatedTask);
        
        Condition mockInitialCondition = mock(Condition.class);
        Condition mockUpdatedCondition = mock(Condition.class);
        when(mockInitialCondition.applyToCopy(mockSubstitution)).thenReturn(mockUpdatedCondition);
        
        BeforeConstraint initialConstraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockInitialTasks)
                .setCondition(mockInitialCondition)
                .build();
        
        BeforeConstraint constraint = initialConstraint.apply(mockSubstitution);

        assertEquals(mockUpdatedTasks, constraint.getTasks());
        assertEquals(mockUpdatedCondition, constraint.getCondition());
    }

    /**
     * Test construction using copy and replace.
     */
    @Test
    public final void testReplace() {
        Task mockTaskA = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockNewTasks = new HashSet<Task>();
        mockNewTasks.add(mockTaskB);
        mockNewTasks.add(mockTaskC);
        Multimap<Task, Task> mockTaskMap = HashMultimap.create();
        mockTaskMap.put(mockTaskA, mockTaskB);
        mockTaskMap.put(mockTaskA, mockTaskC);
        Condition mockCondition = mock(Condition.class);

        BeforeConstraint initialConstraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        
        BeforeConstraint constraint = initialConstraint.replace(mockTaskMap);

        assertEquals(mockNewTasks, constraint.getTasks());
    }

}
