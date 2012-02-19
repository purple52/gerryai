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

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.TaskBuilder;
import org.junit.Test;

/**
 * Unit tests for the SimpleTask class.
 * 
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskTest {

    /**
     * Test name field.
     */
    @Test
    public void testName() {
        @SuppressWarnings("unchecked")
        TaskBuilder<SubstitutableTerm, SubstitutableTask> mockBuilder = mock(TaskBuilder.class);
        when(mockBuilder.getName()).thenReturn("testname");

        SimpleTask task = new SimpleTask(mockBuilder);

        assertEquals("testname", task.getName());
    }

    /**
     * Test arguments field.
     */
    @Test
    public void testArguments() {
        @SuppressWarnings("unchecked")
        TaskBuilder<SubstitutableTerm, SubstitutableTask> mockBuilder = mock(TaskBuilder.class);
        SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
        SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
        List<SubstitutableTerm> mockTerms = new ArrayList<SubstitutableTerm>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);
        when(mockBuilder.getArguments()).thenReturn(mockTerms);

        SimpleTask task = new SimpleTask(mockBuilder);

        // Test the returned list contains the correct terms - note it won't be
        // the same list instance because we get given an immutable list.
        assertEquals(2, task.getArguments().size());
        assertEquals(mockTermA, task.getArguments().get(0));
        assertEquals(mockTermB, task.getArguments().get(1));
    }

    /**
     * Test isPrimitive field.
     */
    @Test
    public void testIsPrimitive() {
        @SuppressWarnings("unchecked")
        TaskBuilder<SubstitutableTerm, SubstitutableTask> mockBuilder = mock(TaskBuilder.class);

        when(mockBuilder.getIsPrimitive()).thenReturn(true);
        SimpleTask taskA = new SimpleTask(mockBuilder);

        assertTrue(taskA.isPrimitive());

        when(mockBuilder.getIsPrimitive()).thenReturn(false);
        SimpleTask taskB = new SimpleTask(mockBuilder);

        assertFalse(taskB.isPrimitive());
    }

    /**
     * Test substitution is applied.
     */
    @Test
    public void testApply() {
        @SuppressWarnings("unchecked")
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);
        @SuppressWarnings("unchecked")
        TaskBuilder<SubstitutableTerm, SubstitutableTask> mockBuilder = mock(TaskBuilder.class);
        @SuppressWarnings("unchecked")
        List<SubstitutableTerm> mockTermList = mock(List.class);
        when(mockBuilder.getArguments()).thenReturn(mockTermList);

        SimpleTask task = new SimpleTask(mockBuilder);
        task.apply(mockSubstituter);

        verify(mockSubstituter).visit(mockTermList);
    }

}
