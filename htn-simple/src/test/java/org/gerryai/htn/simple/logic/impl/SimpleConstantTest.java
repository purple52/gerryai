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

import java.util.HashMap;
import java.util.Map;

import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleConstantTest {

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleConstant#getName()}.
     */
    @Test
    public void testGetName() {
        String name = "testname";
        Constant constant = new SimpleConstant(name);
        assertEquals(name, constant.getName());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleConstant#applyToCopy(java.util.Map)}.
     */
    @Test
    public void testApplyToCopyEmpty() {
        
        Map<Term, Term> substitution = new HashMap<Term, Term>();
        
        String name = "testname";
        Constant constant = new SimpleConstant(name);
        Term term = constant.applyToCopy(substitution);
        
        assertEquals(constant, term);
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleConstant#applyToCopy(java.util.Map)}.
     */
    @Test
    public void testApplyToCopyNotSubstituted() {
 
        String nameA = "testnameA";
        String nameB = "testnameB";
        Constant constantA = new SimpleConstant(nameA);
        Constant constantB = new SimpleConstant(nameB);
        Term mockTerm = mock(Term.class);
        Map<Term, Term> substitution = new HashMap<Term, Term>();
        substitution.put(constantA, mockTerm);
        
        Term term = constantB.applyToCopy(substitution);
        
        assertEquals(constantB, term);
    }
 
    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleConstant#applyToCopy(java.util.Map)}.
     */
    @Test
    public void testApplyToCopySubstituted() {
 
        String nameA = "testnameA";
        Constant constantA = new SimpleConstant(nameA);
        Term mockTerm = mock(Term.class);
        Map<Term, Term> substitution = new HashMap<Term, Term>();
        substitution.put(constantA, mockTerm);
        
        Term term = constantA.applyToCopy(substitution);
        
        assertEquals(mockTerm, term);
    }
    
    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleConstant#isGround()}.
     */
    @Test
    public void testIsGround() {
        String name = "testname";
        Constant constant = new SimpleConstant(name);
        assertTrue(constant.isGround());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleConstant#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        String nameA = "testname";
        String nameB = "testname";
        Constant constantA = new SimpleConstant(nameA);
        Constant constantB = new SimpleConstant(nameB);
        assertTrue(constantA.equals(constantB));
    }

}
