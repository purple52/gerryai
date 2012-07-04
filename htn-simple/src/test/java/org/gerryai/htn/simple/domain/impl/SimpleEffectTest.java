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
package org.gerryai.htn.simple.domain.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.gerryai.htn.domain.Effect;
import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleEffectTest {

    /**
     * Test method for {@link org.gerryai.htn.simple.domain.impl.SimpleEffect#getSentence()}.
     */
    @Test
    public void testGetSentence() {
        Sentence mockSentence = mock(Sentence.class);
        
        Effect effect = new SimpleEffect.Builder()
                .setSentence(mockSentence)
                .build();
        
        assertEquals(mockSentence, effect.getSentence());
    }

    /**
     * Test method for {@link org.gerryai.htn.simple.domain.impl.SimpleEffect#applyToCopy(java.util.Map)}.
     */
    @Test
    public void testApplyToCopy() {
        Map<Term, Term> mockSubstitution = new HashMap<Term, Term>();
        Sentence mockSentenceA = mock(Sentence.class);
        Sentence mockSentenceB = mock(Sentence.class);
        when(mockSentenceA.applyToCopy(mockSubstitution)).thenReturn(mockSentenceB);
        Effect oldEffect = new SimpleEffect.Builder()
                .setSentence(mockSentenceA)
                .build();
        
        Effect newEffect = oldEffect.applyToCopy(mockSubstitution);
        assertEquals(mockSentenceB, newEffect.getSentence());
    }

}
