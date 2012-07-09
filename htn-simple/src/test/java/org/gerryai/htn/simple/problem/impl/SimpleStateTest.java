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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
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
     * Test for simple tell/ask.
     */
    @Test
    public final void testAsk() {
        
        Sentence mockSentenceA = mock(Sentence.class);
        
        Effect mockEffectA = mock(Effect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentenceA);

        Condition mockConditionA = mock(Condition.class);
        when(mockConditionA.getSentence()).thenReturn(mockSentenceA);
        
        ImmutableState state = new SimpleState.Builder()
                .tell(mockEffectA)
                .build();
        
        assertTrue(state.ask(mockConditionA));
    }

    /**
     * Test asking a negated sentence on an empty state.
     */
    @Test
    public final void testAskNegatedEmpty() {
        
        Sentence mockSentence = mock(Sentence.class);
        NegatedSentence mockNegatedSentence = mock(NegatedSentence.class);
        when(mockNegatedSentence.getSentence()).thenReturn(mockSentence);
        Effect mockEffectA = mock(Effect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentence);

        Condition mockConditionA = mock(Condition.class);
        when(mockConditionA.getSentence()).thenReturn(mockNegatedSentence);
        
        ImmutableState state = new SimpleState.Builder()
                .build();
        
        assertTrue(state.ask(mockConditionA));
    }
    /**
     * Test adding a new assertion remembers old and new assertions.
     */
    @Test
    public final void testCopyTell() {
        
        Sentence mockSentenceA = mock(Sentence.class);
        Sentence mockSentenceB = mock(Sentence.class);
        
        Effect mockEffectA = mock(Effect.class);
        Effect mockEffectB = mock(Effect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentenceA);
        when(mockEffectB.getSentence()).thenReturn(mockSentenceB);

        Condition mockConditionA = mock(Condition.class);
        Condition mockConditionB = mock(Condition.class);
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

    /**
     * Test that asserting a negated sentence revokes the negated assertion.
     */
    @Test
    public final void testTellRevoke() {
        
        Sentence mockSentence = mock(Sentence.class);
        NegatedSentence mockNegatedSentence = mock(NegatedSentence.class);
        when(mockNegatedSentence.getSentence()).thenReturn(mockSentence);

        Effect mockEffectA = mock(Effect.class);
        Effect mockEffectB = mock(Effect.class);
        when(mockEffectA.getSentence()).thenReturn(mockSentence);
        when(mockEffectB.getSentence()).thenReturn(mockNegatedSentence);

        Condition mockCondition = mock(Condition.class);
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
