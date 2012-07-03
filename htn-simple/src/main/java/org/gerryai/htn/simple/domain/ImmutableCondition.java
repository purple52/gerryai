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
import org.gerryai.logic.Term;

/**
 * Extended interface for immutable conditions that can only be modified via a builder.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableCondition extends Condition {

    /**
     * Apply the given substitution to a copy of this condition.
     * TODO: Move to Condition
     * @param substitution the substitution to apply
     * @return the new condition
     */
    ImmutableCondition applyToCopy(Map<Term, Term> substitution);
    
}