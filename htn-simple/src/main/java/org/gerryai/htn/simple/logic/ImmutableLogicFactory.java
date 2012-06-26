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
package org.gerryai.htn.simple.logic;

import java.util.Map;

/**
 * Interface for a logic factory that handles immutable logical terms.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableLogicFactory extends LogicFactory<
        ImmutableFunction,
        ImmutableVariable<?>,
        ImmutableConstant<?>,
		ImmutablePredicate,
		ImmutableTerm<?>> {

    /**
     * Apply a set of bindings to a condition.
     * @param condition the original condition
     * @param bindings the bindings to apply
     * @return the ground condition
     */
    ImmutablePredicate copyApply(ImmutablePredicate condition,
            Map<ImmutableVariable<?>, ImmutableConstant<?>> bindings);

    /**
     * Apply a set of bindings to a function.
     * @param function the original function
     * @param bindings the bindings to apply
     * @return the ground function
     */
    ImmutableFunction copyApply(ImmutableFunction function,
            Map<ImmutableVariable<?>, ImmutableConstant<?>> bindings);
}
