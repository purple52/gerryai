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

import java.util.Map;

/**
 * Interface that all logical terms and sentences must extend.
 * @param <T> the type of logical node being implemented
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface LogicalNode<T extends LogicalNode<?>> {

    /**
     * Apply the given substitution to a copy of this node.
     * @param substitution the substitution to apply
     * @return the new logical node
     */
    T applyToCopy(Map<Term, Term> substitution);
    
    /**
     * Check if the logical node is grounded - ie, all its terms are constants.
     * @return true if the node is grounded
     */
    boolean isGround();
}
