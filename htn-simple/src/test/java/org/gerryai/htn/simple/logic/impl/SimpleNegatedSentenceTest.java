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

import java.util.HashMap;
import java.util.Map;

import org.gerryai.logic.NegatedSentence;
import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleNegatedSentenceTest {

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleNegatedSentence#getSentence()}.
     */
    @Test
    public void testGetSentence() {
        Sentence mockSentence = mock(Sentence.class);
        NegatedSentence negatedSentence = new SimpleNegatedSentence.Builder()
                .sentence(mockSentence);
        
        assertEquals(mockSentence, negatedSentence.getSentence());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleNegatedSentence#applyToCopy(java.util.Map)}.
     */
    @Test
    public void testApplyToCopy() {
        Map<Term, Term> mockSubstitution = new HashMap<Term, Term>();
        Sentence mockSentenceA = mock(Sentence.class);
        Sentence mockSentenceB = mock(Sentence.class);
        when(mockSentenceA.applyToCopy(mockSubstitution)).thenReturn(mockSentenceB);
        NegatedSentence oldNegatedSentence = new SimpleNegatedSentence.Builder()
                .sentence(mockSentenceA);

        NegatedSentence  newNegatedSentence = oldNegatedSentence.applyToCopy(mockSubstitution);
        assertEquals(mockSentenceB, newNegatedSentence.getSentence());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleNegatedSentence#isGround()}.
     */
    @Test
    public void testIsGroundTrue() {
        Sentence mockSentence = mock(Sentence.class);
        when(mockSentence.isGround()).thenReturn(true);
        NegatedSentence negatedSentence = new SimpleNegatedSentence.Builder()
                .sentence(mockSentence);
        
        assertTrue(negatedSentence.isGround());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.logic.impl.SimpleNegatedSentence#isGround()}.
     */
    @Test
    public void testIsGroundFalse() {
        Sentence mockSentence = mock(Sentence.class);
        when(mockSentence.isGround()).thenReturn(false);
        NegatedSentence negatedSentence = new SimpleNegatedSentence.Builder()
                .sentence(mockSentence);
        
        assertFalse(negatedSentence.isGround());
    }
}
