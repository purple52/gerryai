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

/**
 * Interface that a problem must implement.
 * @param <O> type of operator this problem uses
 * @param <M> type of method this problem uses
 * @param <T> type of logical term this problem uses
 * @param <K> type of task this problem uses
 * @param <N> the type of task network this problem uses
 * @param <C> the type of constraint this problem uses
 * @param <I> the class of condition the problem will handle
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface Problem<
		O extends Operator<I>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition> {
	
	/**
	 * Get the state of the problem.
	 * @return the state
	 */
	State getState();
	
	/**
	 * Set the state of the problem.
	 * @param state state to set
	 */
	void setState(State state);
	
	/**
	 * Get the task network for this problem.
	 * @return the task network
	 */
	N getTaskNetwork();
	
	/**
	 * Set the task network for this problem.
	 * @param taskNetwork task network to set
	 */
	void setTaskNetwork(N taskNetwork);
	
	/**
	 * Get the domain for this problem.
	 * @return the domain
	 */
	Domain<O, M, T, K, N, C, I> getDomain();
	
	/**
	 * Set the domain for this problem.
	 * @param domain domain to set
	 */
	void setDomain(Domain<O, M, T, K, N, C, I> domain);

}
