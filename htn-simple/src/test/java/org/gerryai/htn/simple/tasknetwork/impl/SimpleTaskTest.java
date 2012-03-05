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
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskBuilder;
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
        ImmutableTaskBuilder mockBuilder = mock(ImmutableTaskBuilder.class);
        when(mockBuilder.getName()).thenReturn("testname");

        SimpleTask task = new SimpleTask(mockBuilder);

        assertEquals("testname", task.getName());
    }

    /**
     * Test arguments field.
     */
    @Test
    public void testArguments() {
        ImmutableTaskBuilder mockBuilder = mock(ImmutableTaskBuilder.class);
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
        ImmutableTaskBuilder mockBuilder = mock(ImmutableTaskBuilder.class);

        when(mockBuilder.isPrimitive()).thenReturn(true);
        SimpleTask taskA = new SimpleTask(mockBuilder);

        assertTrue(taskA.isPrimitive());

        when(mockBuilder.isPrimitive()).thenReturn(false);
        SimpleTask taskB = new SimpleTask(mockBuilder);

        assertFalse(taskB.isPrimitive());
    }

    /**
     * Test builder construction.
     */
    @Test
    public void testSimpleTaskBuilder() {
        // Create the builder under test
        ImmutableTaskBuilder builder = new SimpleTask.Builder();
        
        // Check that the arguments list has been initialised
        assertTrue(builder.getArguments().isEmpty());
    }

    /**
     * Test that the builder can record a name to set.
     * Implicitly tests getName()
     */
    @Test
    public void testSetName() {
        // Create the builder under test
        ImmutableTaskBuilder builder = new SimpleTask.Builder()
                .setName("testname");
        
        // Check that the name has been set
        assertEquals("testname", builder.getName());
    }

    /**
     * Test that single arguments can be added in the correct order.
     * Implicitly tests getArguments()
     */
    @Test
    public void testAddArgument() {

        SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
        SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
        
        // Create the builder under test
        ImmutableTaskBuilder builder = new SimpleTask.Builder()
                .addArgument(mockTermA)
                .addArgument(mockTermB);
        
        // Check that the argument list contains only the argument we have just added
        assertEquals(2, builder.getArguments().size());
        assertEquals(mockTermA, builder.getArguments().get(0));
        assertEquals(mockTermB, builder.getArguments().get(1));
    }

    /**
     * Test that lists of arguments can be added in the correct order.
     * Implicitly tests getArguments()
     */
    @Test
    public void testAddArguments() {
        
        SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
        SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
        List<SubstitutableTerm> mockTermsOne = new ArrayList<SubstitutableTerm>();
        mockTermsOne.add(mockTermA);
        mockTermsOne.add(mockTermB);

        SubstitutableTerm mockTermC = mock(SubstitutableTerm.class);
        SubstitutableTerm mockTermD = mock(SubstitutableTerm.class);
        List<SubstitutableTerm> mockTermsTwo = new ArrayList<SubstitutableTerm>();
        mockTermsTwo.add(mockTermC);
        mockTermsTwo.add(mockTermD);
        
        // Create the builder under test
        ImmutableTaskBuilder builder = new SimpleTask.Builder()
                .addArguments(mockTermsOne)
                .addArguments(mockTermsTwo);
        
        // Check that the arguments have been added in the correct order
        assertEquals(4, builder.getArguments().size());
        assertEquals(mockTermA, builder.getArguments().get(0));
        assertEquals(mockTermB, builder.getArguments().get(1));
        assertEquals(mockTermC, builder.getArguments().get(2));
        assertEquals(mockTermD, builder.getArguments().get(3));
    }

    /**
     * Test building a primitive task.
     */
    @Test
    public void testBuildPrimitive() {

        SubstitutableTerm mockTerm = mock(SubstitutableTerm.class);
        
        // Create the builder under test
        ImmutableTask primitiveTask = new SimpleTask.Builder()
                .setName("testname")
                .addArgument(mockTerm)
                .setIsPrimitive(true)
                .build();
        assertEquals("testname",primitiveTask.getName());
        assertEquals(1, primitiveTask.getArguments().size());
        assertTrue(primitiveTask.getArguments().contains(mockTerm));
        assertTrue(primitiveTask.isPrimitive());
    }

    /**
     * Test building a non-primitive task.
     */
    @Test
    public void testBuildNonPrimitive() {

        SubstitutableTerm mockTerm = mock(SubstitutableTerm.class);
        
        // Create the builder under test
        ImmutableTask primitiveTask = new SimpleTask.Builder()
                .setName("testname")
                .addArgument(mockTerm)
                .setIsPrimitive(false)
                .build();
        assertEquals("testname",primitiveTask.getName());
        assertEquals(1, primitiveTask.getArguments().size());
        assertTrue(primitiveTask.getArguments().contains(mockTerm));
        assertFalse(primitiveTask.isPrimitive());
    }
    
    /**
     * Test a simple build just copying a base task.
     */
    @Test
    public void testCopy() {
        String name = "testname";
        SubstitutableTerm mockTerm = mock(SubstitutableTerm.class);
        List<SubstitutableTerm> terms = new ArrayList<SubstitutableTerm>();
        terms.add(mockTerm);
        ImmutableTask initialTask = new SimpleTask.Builder()
                .setName(name)
                .addArguments(terms)
                .setIsPrimitive(true)
                .build();
        
        ImmutableTask newTask = initialTask.createCopyBuilder()
                .build();
        
        assertEquals(name, newTask.getName());
        assertEquals(terms, newTask.getArguments());
        assertTrue(newTask.isPrimitive());
    }
    
      /**
     * Test a copying a base task and applying a substituter.
     */
    @Test
    public void testBuilderApply() {
        String name = "testname";
        SubstitutableTerm mockTerm = mock(SubstitutableTerm.class);
        List<SubstitutableTerm> terms = new ArrayList<SubstitutableTerm>();
        terms.add(mockTerm);
        ImmutableTask initialTask = new SimpleTask.Builder()
        .setName(name)
        .addArguments(terms)
        .setIsPrimitive(true)
        .build();
        
        @SuppressWarnings("unchecked")
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);
        
        // Create the builder under test
        ImmutableTask newTask = initialTask.createCopyBuilder()
                .apply(mockSubstituter)
                .build();
        
        assertEquals(name, newTask.getName());
        assertEquals(terms, newTask.getArguments());
        assertTrue(newTask.isPrimitive());
        
        verify(mockSubstituter).visit(terms);
    }
}
