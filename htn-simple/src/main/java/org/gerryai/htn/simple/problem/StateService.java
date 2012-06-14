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

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.problem.State;
import org.gerryai.logic.Term;

/**
 * Interface for a service that can handle states, conditional checks.
 * @param <T> type of logical term this service uses
 * @param <I> type of condition this service uses
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface StateService<T extends Term, I extends Condition<T>> {

    /**
     * Check if the given condition is satisfied by the given state.
     * @param state the state to check
     * @param condition the condition to check for
     * @return true if the condition is satisfied
     */
    boolean isConditionSatisified(State state, I condition);
}
