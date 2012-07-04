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
package org.gerryai.htn.simple.problem.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableEffect;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.logic.NegatedSentence;
import org.gerryai.logic.Sentence;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleStateTest {

    /**
     * Test method for {@link org.gerryai.htn.simple.problem.impl.SimpleState#ask(org.gerryai.htn.simple.domain.ImmutableCondition)}.
     */
    @Test
    public void testAsk() {
        
        Sentence mockSentenceA = mock(Sentence.class);
        
        ImmutableEffect mockEffectA = mock(ImmutableEffect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentenceA);

        ImmutableCondition mockConditionA = mock(ImmutableCondition.class);
        when(mockConditionA.getSentence()).thenReturn(mockSentenceA);
        
        ImmutableState state = new SimpleState.Builder()
                .tell(mockEffectA)
                .build();
        
        assertTrue(state.ask(mockConditionA));
    }

    @Test
    public void testAskNegatedEmpty() {
        
        Sentence mockSentence = mock(Sentence.class);
        NegatedSentence mockNegatedSentence = mock(NegatedSentence.class);
        when(mockNegatedSentence.getSentence()).thenReturn(mockSentence);
        ImmutableEffect mockEffectA = mock(ImmutableEffect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentence);

        ImmutableCondition mockConditionA = mock(ImmutableCondition.class);
        when(mockConditionA.getSentence()).thenReturn(mockNegatedSentence);
        
        ImmutableState state = new SimpleState.Builder()
                .build();
        
        assertTrue(state.ask(mockConditionA));
    }
    /**
     * Test method for {@link org.gerryai.htn.simple.problem.impl.SimpleState#createCopyBuilder()}.
     */
    @Test
    public void testCopyTell() {
        
        Sentence mockSentenceA = mock(Sentence.class);
        Sentence mockSentenceB = mock(Sentence.class);
        
        ImmutableEffect mockEffectA = mock(ImmutableEffect.class);
        ImmutableEffect mockEffectB = mock(ImmutableEffect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentenceA);
        when(mockEffectB.getSentence()).thenReturn(mockSentenceB);

        ImmutableCondition mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition mockConditionB = mock(ImmutableCondition.class);
        when(mockConditionA.getSentence()).thenReturn(mockSentenceA);
        when(mockConditionB.getSentence()).thenReturn(mockSentenceB);
        
        ImmutableState oldState = new SimpleState.Builder()
                .tell(mockEffectA)
                .build();
        
        ImmutableState newState = oldState.createCopyBuilder()
                .tell(mockEffectB)
                .build();
        
        assertTrue(newState.ask(mockConditionA));
        assertTrue(newState.ask(mockConditionB));
    }

    @Test
    public void testTellRevoke() {
        
        Sentence mockSentence = mock(Sentence.class);
        NegatedSentence mockNegatedSentence = mock(NegatedSentence.class);
        when(mockNegatedSentence.getSentence()).thenReturn(mockSentence);

        ImmutableEffect mockEffectA = mock(ImmutableEffect.class);
        ImmutableEffect mockEffectB = mock(ImmutableEffect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentence);
        when(mockEffectB.getSentence()).thenReturn(mockNegatedSentence);

        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        when(mockCondition.getSentence()).thenReturn(mockSentence);
        
        ImmutableState oldState = new SimpleState.Builder()
                .tell(mockEffectA)
                .build();
        
        ImmutableState newState = oldState.createCopyBuilder()
                .tell(mockEffectB)
                .build();
        
        assertTrue(oldState.ask(mockCondition));
        assertFalse(newState.ask(mockCondition));
    }
}
