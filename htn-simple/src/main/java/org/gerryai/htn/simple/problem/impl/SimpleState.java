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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.problem.ImmutableStateBuilder;
import org.gerryai.logic.NegatedSentence;
import org.gerryai.logic.Sentence;

/**
 * Implementation of an immutable state representation.
 * TODO: COMPLETE IMPLEMENTATION!
 * @author David Edwards <david@more.fool.me.uk>
 */
public final class SimpleState implements ImmutableState {

    /**
     * Map of all functions this state knows about, and whether they are true.
     */
    private Set<Sentence> sentences;
    
    /**
     * Private constructor taking a builder to build from.
     * @param builder the builder to build from
     */
    private SimpleState(Builder builder) {
        sentences = builder.sentences;
    }

    /**
     * {@inheritDoc}
     */
    public boolean ask(Condition condition) {
        return ask(condition.getSentence());
    }

    /**
     * Check if the underlying sentence is true or false.
     * @param sentence the sentence to check
     * @return the truth of the sentence
     */
    private boolean ask(Sentence sentence) {
        if (sentence instanceof NegatedSentence) {
            return !ask(((NegatedSentence) sentence).getSentence());
        } else {
            return sentences.contains(sentence);
        }
    }
    /**
     * {@inheritDoc}
     */
    public ImmutableStateBuilder createCopyBuilder() {
        return new Builder().copy(this);
    }

    /**
     * {@inheritDoc}
     */
    public Set<Sentence> getAssertions() {
        return Collections.unmodifiableSet(sentences);
    }

    /**
     * Builder class for SimpleState objects.
     * @author David Edwards <david@more.fool.me.uk>
     */
    protected static class Builder implements ImmutableStateBuilder {

        /**
         * Map of all functions this state knows about, and whether they are true.
         */
        private Set<Sentence> sentences;
        
        /**
         * Constructor.
         */
        protected Builder() {
            sentences = new HashSet<Sentence>();
        }

        /**
         * {@inheritDoc}
         */
        public final ImmutableStateBuilder copy(ImmutableState state) {
            sentences = new HashSet<Sentence>(state.getAssertions());
            return this;
        }
 
        /**
         * {@inheritDoc}
         */
        public final ImmutableStateBuilder tell(Effect effect) {
            return this.tell(effect.getSentence());
        }
 
        /**
         * {@inheritDoc}
         */
        public final ImmutableStateBuilder tell(Sentence sentence) {
            if (sentence instanceof NegatedSentence) {
                return this.revoke(((NegatedSentence) sentence).getSentence());
            } else {
                sentences.add(sentence);
            }
            return this;
        }
     
        /**
         * {@inheritDoc}
         */
        public final ImmutableStateBuilder revoke(Sentence sentence) {
            sentences.remove(sentence);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final ImmutableState build() {
            return new SimpleState(this);
        }
        
    }
    
    @Override
    public String toString() {
        return sentences.toString();
    }
}
