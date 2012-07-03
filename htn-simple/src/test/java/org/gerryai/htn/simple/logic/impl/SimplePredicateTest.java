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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gerryai.logic.Predicate;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * Unit tests for SimplePredicate.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePredicateTest {

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#getName()}.
     */
    @Test
    public void testGetName() {
        String name = "testname";
        Predicate predicate = new SimplePredicate.Builder(name)
                .build();
        assertEquals(name, predicate.getName());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#getTerms()}.
     */
    @Test
    public void testGetTerms() {
        Term mockTermA = mock(Term.class);
        Term mockTermB = mock(Term.class);
        List<Term> mockTerms = new ArrayList<Term>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);
        
        String name = "testname";
        Predicate predicate = new SimplePredicate.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        assertEquals(mockTerms, predicate.getTerms());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#applyToCopy(java.util.Map)}.
     */
    @Test
    public void testApplyToCopy() {
        
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
        Predicate oldPredicate = new SimplePredicate.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        Predicate newPredicate = oldPredicate.applyToCopy(mockSubstitution);
        
        assertEquals(mockTermA, newPredicate.getTerms().get(0));
        assertEquals(mockTermC, newPredicate.getTerms().get(1));
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#isGround()}.
     */
    @Test
    public void testIsGroundTrue() {
        Term mockTermA = mock(Term.class);
        Term mockTermB = mock(Term.class);
        when(mockTermA.isGround()).thenReturn(true);
        when(mockTermB.isGround()).thenReturn(true);
        List<Term> mockTerms = new ArrayList<Term>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);
        
        String name = "testname";
        Predicate predicate = new SimplePredicate.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        assertTrue(predicate.isGround());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimplePredicate#isGround()}.
     */
    @Test
    public void testIsGroundFalse() {
        Term mockTermA = mock(Term.class);
        Term mockTermB = mock(Term.class);
        when(mockTermA.isGround()).thenReturn(true);
        when(mockTermB.isGround()).thenReturn(false);
        List<Term> mockTerms = new ArrayList<Term>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockTermB);
        
        String name = "testname";
        Predicate predicate = new SimplePredicate.Builder(name)
                .addTerm(mockTermA)
                .addTerm(mockTermB)
                .build();
        
        assertFalse(predicate.isGround());
    }
}
