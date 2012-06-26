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

import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.domain.ImmutableCondition;

/**
 * Interface for immutable implementations of state.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableState extends State {

    /**
     * Create a new builder object that can build a copy of this immutable state.
     * @return the builder
     */
    ImmutableStateBuilder createCopyBuilder();
    
    /**
     * Ask this state if the condition is true.
     * @param condition the condition to check
     * @return true if the condition is true in this state
     */
    boolean ask(ImmutableCondition condition);
    
}
