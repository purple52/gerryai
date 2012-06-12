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
package org.gerryai.logic;

import java.util.List;

/**
 * Interface for a logical predicate.
 * @param <T> type of logical term this predicate uses.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Predicate<T extends Term> {

    /**
     * Get the symbolic name of this predicate.
     * @return the name
     */
    String getName();
    
    /**
     * Get the list of terms that make up this predicate.
     * @return the terms
     */
    List<T> getTerms();
}
