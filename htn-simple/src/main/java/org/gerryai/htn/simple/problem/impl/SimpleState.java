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

import java.util.HashMap;
import java.util.Map;

import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableEffect;
import org.gerryai.htn.simple.logic.ImmutablePredicate;
import org.gerryai.htn.simple.logic.ImmutableFunction;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.problem.ImmutableStateBuilder;

/**
 * Implementation of an immutable state representation.
 * @author David Edwards <david@more.fool.me.uk>
 */
public final class SimpleState implements ImmutableState {

    /**
     * Map of all functions this state knows about, and whether they are true.
     */
    private Map<ImmutableFunction, Boolean> booleanFunctionMap;
    
    /**
     * Private constructor taking a builder to build from.
     * @param builder the builder to build from
     */
    private SimpleState(Builder builder) {
        booleanFunctionMap = builder.booleanFunctionMap;
    }

    /**
     * {@inheritDoc}
     */
    public boolean ask(ImmutableCondition condition) {
        ImmutablePredicate predicate = condition.getPredicate();
        return booleanFunctionMap.containsKey(predicate)
            && booleanFunctionMap.get(predicate).booleanValue();
    }

    /**
     * {@inheritDoc}
     */
    public ImmutableStateBuilder createCopyBuilder() {
        return new Builder().copy(this);
    }

    /**
     * Builder class for SimpleState objects.
     * @author David Edwards <david@more.fool.me.uk>
     */
    protected static class Builder implements ImmutableStateBuilder {

        /**
         * Map of all functions this state knows about, and whether they are true.
         */
        private Map<ImmutableFunction, Boolean> booleanFunctionMap;
        
        /**
         * Constructor.
         */
        protected Builder() {
            booleanFunctionMap = new HashMap<ImmutableFunction, Boolean>();
        }

        /**
         * {@inheritDoc}
         */
        public final ImmutableStateBuilder copy(ImmutableState state) {
            //TODO: Implement
            return this;
        }
 
        /**
         * {@inheritDoc}
         */
        public final ImmutableStateBuilder tell(ImmutableEffect effect) {
            booleanFunctionMap.put(effect.getFunction(), effect.getValue());
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final ImmutableState build() {
            return new SimpleState(this);
        }


        
    }
}
