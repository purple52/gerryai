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
package org.gerryai.htn.simple.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gerryai.logic.Function;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * Unit tests for SimpleFunction.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleFunctionTest {

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#getName()}.
     */
    @Test
    public final void testGetName() {
        String name = "testname";
        Function function = new SimpleFunction.Builder(name)
                .build();
        assertEquals(name, function.getName());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#getTerms()}.
     */
    @Test
    public final void testGetTerms() {
        Term mockTermA = mock(Term.class);
        Term mockTermB = mock(Term.class);
        List<Term> mockTerms = new ArrayList<Term>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);
        
        String name = "testname";
        Function function = new SimpleFunction.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        assertEquals(mockTerms, function.getTerms());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#applyToCopy(java.util.Map)}.
     */
    @Test
    public final void testApplyToCopy() {
        
        Map<Term, Term> mockSubstitution = new HashMap<Term, Term>();

        Term mockTermA = mock(Term.class);
        Term mockTermB = mock(Term.class);
        Term mockTermC = mock(Term.class);
        when(mockTermA.applyToCopy(mockSubstitution)).thenReturn(mockTermA);
        when(mockTermB.applyToCopy(mockSubstitution)).thenReturn(mockTermC);
        
        List<Term> mockTerms = new ArrayList<Term>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);

        
        String name = "testname";
        Function oldFunction = new SimpleFunction.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        Function newFunction = oldFunction.applyToCopy(mockSubstitution);
        
        assertEquals(mockTermA, newFunction.getTerms().get(0));
        assertEquals(mockTermC, newFunction.getTerms().get(1));
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#isGround()}.
     * Check that the function is ground when all its terms are ground.
     */
    @Test
    public final void testIsGroundTrue() {
        Term mockTermA = mock(Term.class);
        Term mockTermB = mock(Term.class);
        when(mockTermA.isGround()).thenReturn(true);
        when(mockTermB.isGround()).thenReturn(true);
        List<Term> mockTerms = new ArrayList<Term>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);
        
        String name = "testname";
        Function function = new SimpleFunction.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        assertTrue(function.isGround());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#isGround()}.
     * Check that a function is not ground if any of its terms are not ground.
     */
    @Test
    public final void testIsGroundFalse() {
        Term mockTermA = mock(Term.class);
        Term mockTermB = mock(Term.class);
        when(mockTermA.isGround()).thenReturn(true);
        when(mockTermB.isGround()).thenReturn(false);
        List<Term> mockTerms = new ArrayList<Term>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);
        
        String name = "testname";
        Function function = new SimpleFunction.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        assertFalse(function.isGround());
    }
}
