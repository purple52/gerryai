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
package org.gerryai.htn.simple.decomposition;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Interface for a service that can find a unifier for a task and method.
 * @param <M> the type of method this service works with
 * @param <T> the type of logical term this service works with
 * @param <K> the type of task this service works with
 * @param <N> the type of task network this service works with
 * @param <C> the type of constraint this service works with
 * @param <I> the class of condition the service will handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface UnificationService<
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition<T>> {
	
	/**
	 * Given a task and a method, try and find the most general unifier (MGU).
	 * @param task the task to unify
	 * @param method the method to unify with
	 * @return the most general unifier
	 * @throws UnifierNotFound if no unifier could be found for this task and method
	 */
    ImmutableSubstitution findUnifier(K task, M method) throws UnifierNotFound;
	
}
