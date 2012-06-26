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

import java.util.List;
import java.util.Map;

/**
 * Interface for builders for immutable terms.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutablePredicateBuilder {

    /**
     * Set the symbolic name of the underlying predicate.
     * @param name the name
     * @return an updated builder
     */
    ImmutablePredicateBuilder setName(String name);

    /**
     * Add a term to the underlying predicate.
     * @param term the term to add
     * @return an updated builder
     */
    ImmutablePredicateBuilder addTerm(ImmutableTerm<?> term);

    /**
     * Add a list of terms to the underlying predicate.
     * @param terms the terms to add
     * @return an updated builder
     */
    ImmutablePredicateBuilder addTerm(List<ImmutableTerm<?>> terms);
    
    /**
     * Copy the provided condition as a basis for a new one.
     * @param condition the condition to copy
     * @return the updated builder
     */
    ImmutablePredicateBuilder copy(ImmutablePredicate condition);
    
    /**
     * Apply the substituter provided to the underlying predicate.
     * @param substitution the substitution to apply
     * @return the updated builder
     */
    ImmutablePredicateBuilder apply(Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution);
    
    /**
     * Build the condition.
     * @return the finished condition
     */
    ImmutablePredicate build();
}
