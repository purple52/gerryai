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

import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;

/**
 * Interface for a builder for immutable effects.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableEffectBuilder {

    /**
     * Set the logical sentence for the effect being built.
     * @param sentence the sentence
     * @return an updated builder
     */
    ImmutableEffectBuilder setSentence(Sentence sentence);

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
    ImmutableEffectBuilder apply(Map<Term, Term> substitution);
    
    
    /**
     * Build the finished effect.
     * @return an updated builder
     */
    ImmutableEffect build();
}
