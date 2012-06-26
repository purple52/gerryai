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
package org.gerryai.htn.simple.domain;

import java.util.Map;

import org.gerryai.htn.simple.logic.ImmutableFunction;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * Interface for a builder for immutable effects.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableEffectBuilder {

    /**
     * Set the function for the effect being built.
     * @param function the function
     * @return an updated builder
     */
    ImmutableEffectBuilder setFunction(ImmutableFunction function);
        
    /**
     * Set the value returned by the function for the effect being built.
     * @param value the value
     * @return an updated builder
     */
    ImmutableEffectBuilder setValue(Boolean value);
        
    /**
     * Copy from the provided effect, overwriting any values already set.
     * @param effect the effect to copy
     * @return the updated builder
     */
    ImmutableEffectBuilder copy(ImmutableEffect effect);
    
    /**
     * Apply the provided substituter to the arguments provided so far.
     * @param substitution the substitution to apply
     * @return the updated builder
     */
    ImmutableEffectBuilder apply(Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution);
    
    
    /**
     * Build the finished effect.
     * @return an updated builder
     */
    ImmutableEffect build();
}
