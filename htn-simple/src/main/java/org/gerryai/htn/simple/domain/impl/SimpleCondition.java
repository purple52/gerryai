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
import org.gerryai.htn.simple.logic.ImmutablePredicate;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public final class SimpleCondition implements ImmutableCondition {

    /**
     * The predicate that this condition checks for.
     */
    private ImmutablePredicate predicate;
    
    /**
     * Constructor using a builder.
     * @param builder the builder to build from
     */
    private SimpleCondition(Builder builder) {
        predicate = builder.predicate;
    }

    /**
     * {@inheritDoc}
     */
    public ImmutablePredicate getPredicate() {
        return predicate;
    }
    
    /**
     * {@inheritDoc}
     */
    public ImmutableConditionBuilder createCopyBuilder() {
        return new Builder()
            .copy(this);
    }

    /**
     * Builder class for conditions.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static final class Builder implements ImmutableConditionBuilder {
   
        /**
         * The predicate that the condition being built will use.
         */
        private ImmutablePredicate predicate;
        
        /**
         * Protected constructor.
         */
        protected Builder() { }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableConditionBuilder setPredicate(ImmutablePredicate predicate) {
            this.predicate = predicate;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableConditionBuilder copy(ImmutableCondition condition) {
            this.predicate = condition.getPredicate();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public ImmutableConditionBuilder apply(
                Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution) {
            this.predicate = predicate.createCopyBuilder()
                    .apply(substitution)
                    .build();
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableCondition build() {
            return new SimpleCondition(this);
        }
    }
}
