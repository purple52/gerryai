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
package org.gerryai.htn.domain;

import java.util.Map;

import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;

/**
 * Interface for classes that represent a logical condition.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Condition {

    /**
     * Get the logical sentence that this condition requires to be true.
     * @return the sentence
     */
    Sentence getSentence();
    
    /**
     * Apply the given substitution to a copy of this condition.
     * @param substitution the substitution to apply
     * @return the new condition
     */
    Condition applyToCopy(Map<Term, Term> substitution);
}
