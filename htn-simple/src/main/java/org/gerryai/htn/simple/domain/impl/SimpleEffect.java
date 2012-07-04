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

import java.util.Map;

import org.gerryai.htn.simple.domain.ImmutableEffect;
import org.gerryai.htn.simple.domain.ImmutableEffectBuilder;
import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public final class SimpleEffect implements ImmutableEffect {

    /**
     * The sentence that this effect asserts.
     */
    private Sentence sentence;
    
    /**
     * Constructor using a builder.
     * @param builder the builder to build from
     */
    private SimpleEffect(Builder builder) {
        sentence = builder.sentence;
    }

    /**
     * {@inheritDoc}
     */
    public Sentence getSentence() {
        return sentence;
    }
    
    /**
     * {@inheritDoc}
     */
    public ImmutableEffect applyToCopy(Map<Term, Term> substitution) {
        return new Builder()
            .copy(this)
            .apply(substitution)
            .build();
    }

    /**
     * Builder class for effects.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static final class Builder implements ImmutableEffectBuilder {
   
        /**
         * The sentence that the effect being built will use.
         */
        private Sentence sentence;
        
        /**
         * Protected constructor.
         */
        protected Builder() { }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableEffectBuilder setSentence(Sentence sentence) {
            this.sentence = sentence;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableEffectBuilder copy(ImmutableEffect effect) {
            this.sentence = effect.getSentence();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public ImmutableEffectBuilder apply(Map<Term, Term> substitution) {
            this.sentence = sentence.applyToCopy(substitution);
            return this;
        }
        
        /**
         * Build the finished effect.
         * @return an updated builder
         */
        public ImmutableEffect build() {
            return new SimpleEffect(this);
        }
    }

}
