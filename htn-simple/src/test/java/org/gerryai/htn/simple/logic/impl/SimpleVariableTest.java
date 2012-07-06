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

import java.util.HashMap;
import java.util.Map;

import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.junit.Test;

/**
 * Unit tests for the SimpleVariable class.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleVariableTest {

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleVariable#getName()}.
     */
    @Test
    public final void testGetName() {
        String name = "testname";
        Variable variable = new SimpleVariable(name);
        assertEquals(name, variable.getName());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleVariable#applyToCopy(java.util.Map)}.
	 * Check that an empty substitution does nothing.
     */
    @Test
    public final void testApplyToCopyEmpty() {
        
        Map<Term, Term> substitution = new HashMap<Term, Term>();
        
        String name = "testname";
        Variable variable = new SimpleVariable(name);
        Term term = variable.applyToCopy(substitution);
        
        assertEquals(variable, term);
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleVariable#applyToCopy(java.util.Map)}.
	 * Check that a substitution that does nothing has no effect.
     */
    @Test
    public final void testApplyToCopyNotSubstituted() {
 
        String nameA = "testnameA";
        String nameB = "testnameB";
        Variable variableA = new SimpleVariable(nameA);
        Variable variableB = new SimpleVariable(nameB);
        Term mockTerm = mock(Term.class);
        Map<Term, Term> substitution = new HashMap<Term, Term>();
        substitution.put(variableA, mockTerm);
        
        Term term = variableB.applyToCopy(substitution);
        
        assertEquals(variableB, term);
    }
 
    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleVariable#applyToCopy(java.util.Map)}.
     * Check that a substitution that replaces this variable is applied correctly.
     */
    @Test
    public final void testApplyToCopySubstituted() {
 
        String nameA = "testnameA";
        Variable variableA = new SimpleVariable(nameA);
        Term mockTerm = mock(Term.class);
        Map<Term, Term> substitution = new HashMap<Term, Term>();
        substitution.put(variableA, mockTerm);
        
        Term term = variableA.applyToCopy(substitution);
        
        assertEquals(mockTerm, term);
    }
    
    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleVariable#isGround()}.
     */
    @Test
    public final void testIsGround() {
        String name = "testname";
        Variable variable = new SimpleVariable(name);
        assertFalse(variable.isGround());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleVariable#equals(java.lang.Object)}.
     */
    @Test
    public final void testEqualsObject() {
        String nameA = "testname";
        String nameB = "testname";
        Variable variableA = new SimpleVariable(nameA);
        Variable variableB = new SimpleVariable(nameB);
        assertTrue(variableA.equals(variableB));
    }

}
