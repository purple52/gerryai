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
import org.gerryai.htn.simple.logic.ImmutableFunction;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public final class SimpleEffect implements ImmutableEffect {

    /**
     * The function that this effect applies to the a state.
     */
    private ImmutableFunction function;
    
    /**
     * The value that the function returns.
     */
    private Boolean value;
    
    /**
     * Constructor using a builder.
     * @param builder the builder to build from
     */
    private SimpleEffect(Builder builder) {
        function = builder.function;
        value = builder.value;
    }

    /**
     * {@inheritDoc}
     */
    public ImmutableFunction getFunction() {
        return function;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean getValue() {
        return value;
    }
    
    /**
     * {@inheritDoc}
     */
    public ImmutableEffectBuilder createCopyBuilder() {
        return new Builder()
            .copy(this);
    }

    /**
     * Builder class for effects.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static final class Builder implements ImmutableEffectBuilder {
   
        /**
         * The function that the effect being built will use.
         */
        private ImmutableFunction function;
        
        /**
         * The value that the function returns.
         */
        private Boolean value;
        
        /**
         * Protected constructor.
         */
        protected Builder() { }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableEffectBuilder setFunction(ImmutableFunction function) {
            this.function = function;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableEffectBuilder setValue(Boolean value) {
            this.value = value;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ImmutableEffectBuilder copy(ImmutableEffect effect) {
            this.function = effect.getFunction();
            this.value = effect.getValue();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public ImmutableEffectBuilder apply(
                Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution) {
            this.function = function.createCopyBuilder()
                    .apply(substitution)
                    .build();
            return null;
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
