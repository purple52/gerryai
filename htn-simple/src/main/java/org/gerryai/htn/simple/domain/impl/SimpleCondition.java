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

import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableConditionBuilder;
import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public final class SimpleCondition implements ImmutableCondition {

    /**
     * The sentence that this condition checks for.
     */
    private Sentence sentence;
    
    /**
     * Constructor using a builder.
     * @param builder the builder to build from
     */
    private SimpleCondition(Builder builder) {
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
    public ImmutableCondition applyToCopy(Map<Term, Term> substitution) {
        return new Builder()
            .copy(this)
            .apply(substitution)
            .build();
    }

    /**
     * Builder class for conditions.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static final class Builder implements ImmutableConditionBuilder {
   
        /**
         * The sentence that the condition being built will use.
         */
        private Sentence sentence;
        
        /**
         * Protected constructor.
         */
        protected Builder() { }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableConditionBuilder setSentence(Sentence sentence) {
            this.sentence = sentence;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableConditionBuilder copy(ImmutableCondition condition) {
            this.sentence = condition.getSentence();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public ImmutableConditionBuilder apply(Map<Term, Term> substitution) {
            this.sentence = sentence.applyToCopy(substitution);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableCondition build() {
            return new SimpleCondition(this);
        }
    }
    
    @Override
    public String toString() {
        return sentence.toString();
    }
}
