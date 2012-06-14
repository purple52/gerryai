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
 * Interface for builders for immutable terms.
 * @param <T> type of term being built
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableTermBuilder<T extends ImmutableTerm<T>> {

    /**
     * Copy the provided term as a basis for a new one.
     * @param term the term to copy
     * @return the updated builder
     */
    ImmutableTermBuilder<T> copy(T term);
    
    /**
     * Apply the substituter provided to the term.
     * @param substitution the substitution to apply
     * @return the updated builder
     */
    ImmutableTermBuilder<T> apply(Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution);
    
    /**
     * Build the term.
     * @return the finished constraint
     */
    ImmutableTerm<T> build();
}
