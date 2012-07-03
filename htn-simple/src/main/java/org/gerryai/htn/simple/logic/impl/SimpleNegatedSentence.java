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

import java.util.Map;

import org.gerryai.logic.NegatedSentence;
import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;
import org.gerryai.logic.builder.NegatedSentenceBuilder;

/**
 * Simple implementation of an immutable negated sentence.
 * @author David Edwards <david@more.fool.me.uk>
 */
public final class SimpleNegatedSentence implements NegatedSentence {

    /**
     * The sentence being negated.
     */
    private Sentence<?> sentence;
    
    /**
     * {@inheritDoc}
     */
    public Sentence<?> getSentence() {
        return sentence;
    }
    
    /**
     * Constructor.
     * @param builder the builder to build from
     */
    private SimpleNegatedSentence(Builder builder) {
        this.sentence = builder.sentence;
    }
    
    /**
     * {@inheritDoc}
     */
    public NegatedSentence applyToCopy(Map<Term, Term> substitution) {
        return new Builder()
            .copy(this)
            .apply(substitution)
            .build();
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isGround() {
        return sentence.isGround();
    }
    
    /**
     * Builder class for negated sentences.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static final class Builder implements NegatedSentenceBuilder {
 
        /**
         * The sentence being negated.
         */
        private Sentence<?> sentence;
 
        /**
         * Use the provided negated sentence as a base.
         * @param sentence the sentence to copy
         * @return an updtaed builder
         */
        private Builder copy(NegatedSentence sentence) {
            this.sentence = sentence;
            return this;
        }
        
        /**
         * Apply the supplied substitution to the sentence being built.
         * @param substitution the substitution to apply
         * @return an updated builder
         */
        private Builder apply(Map<Term, Term> substitution) {
            this.sentence = sentence.applyToCopy(substitution);
            return this;
        }
        
        /**
         * Build the sentence.
         * @return the sentence
         */
        private NegatedSentence build() {
            return new SimpleNegatedSentence(this);
        }

        /**
         * {@inheritDoc}
         */
        public NegatedSentence sentence(Sentence<?> sentence) {
            this.sentence = sentence;
            return new SimpleNegatedSentence(this);
        }
        
    }

}
