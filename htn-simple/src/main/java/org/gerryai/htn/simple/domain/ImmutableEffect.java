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

import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.simple.logic.ImmutableFunction;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * Extended interface for immutable conditions that can only be modified via a builder.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableEffect extends Effect<ImmutableTerm<?>> {

    /**
     * Create a builder that will start with a copy of this effect.
     * @return the builder
     */
    ImmutableEffectBuilder createCopyBuilder();
    
    /**
     * Get the function that this effect applies to the current state.
     * @return the function
     */
    ImmutableFunction getFunction();
    
    /**
     * Get the value of this function.
     * @return the value
     */
    Boolean getValue();
}
