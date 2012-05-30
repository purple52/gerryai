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
package org.gerryai.htn.problem;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface that a problem must implement.
 * @param <D> type of domain thsi problem uses
 * @param <O> type of operator this problem uses
 * @param <M> type of method this problem uses
 * @param <T> type of logical term this problem uses
 * @param <K> type of task this problem uses
 * @param <N> the type of task network this problem uses
 * @param <C> the type of constraint this problem uses
 * @param <I> the class of condition the problem will handle
 * @param <V> type of variable this problem uses
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface Problem<
        D extends Domain<O, M, T, K, N, C, I, V>,
		O extends Operator<I, V>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition,
		V extends Variable> {
	
	/**
	 * Get the state of the problem.
	 * @return the state
	 */
	State getState();
	
	/**
	 * Get the task network for this problem.
	 * @return the task network
	 */
	N getTaskNetwork();
	
	/**
	 * Get the domain for this problem.
	 * @return the domain
	 */
	D getDomain();

}
