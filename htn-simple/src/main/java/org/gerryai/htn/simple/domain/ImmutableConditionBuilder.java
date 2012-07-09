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

import org.gerryai.htn.domain.Condition;
import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;

/**
 * Interface for a builder for immutable conditions.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableConditionBuilder {

    /**
     * Set the logical sentence for the condition being built.
     * @param sentence the sentence
     * @return an updated builder
     */
    ImmutableConditionBuilder setSentence(Sentence sentence);
        
    /**
     * Copy from the provided condition, overwriting any values already set.
     * @param condition the condition to copy
     * @return the updated builder
     */
    ImmutableConditionBuilder copy(Condition condition);
    
    /**
     * Apply the provided substituter to the arguments provided so far.
     * @param substitution the substitution to apply
     * @return the updated builder
     */
    ImmutableConditionBuilder apply(Map<Term, Term> substitution);
    
    /**
     * Build the finished condition.
     * @return an updated builder
     */
    Condition build();
}
