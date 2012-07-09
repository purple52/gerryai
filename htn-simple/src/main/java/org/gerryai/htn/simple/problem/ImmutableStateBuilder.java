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
package org.gerryai.htn.simple.problem;

import org.gerryai.htn.domain.Effect;

/**
 * Interface for a builder class for immutable state objects.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableStateBuilder {

    /**
     * Copy the state provided.
     * @param state the state to copy
     * @return an updated builder
     */
    ImmutableStateBuilder copy(ImmutableState state);
    
    /**
     * Apply the supplied effect to the state to be built.
     * @param effect the effect to apply
     * @return an updated builder
     */
    ImmutableStateBuilder tell(Effect effect);
    
    /**
     * Build the finished state.
     * @return the state
     */
    ImmutableState build();
}
