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

import java.util.Map;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * @param <M> type of method this service can decompose
 * @param <N> type of task network this service works with
 * @param <C> type of constraint this service works with
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface DecompositionService<
		M extends Method<N, C>,
		N extends TaskNetwork<C>,
		C extends Constraint> {

	/**
	 * Decomposes a task within a task network using the given method and unifier.
	 * @param substitution the substitution to unify the task and method
	 * @param taskNetwork the task network to decompose
	 * @param task the task within the network to decompose
	 * @param method the method with which to decompose the task
	 * @return the decomposed task network
	 * @throws InvalidConstraint 
	 */
	N decompose(Map<Term, Term> substitution, N taskNetwork, Task task, M method)  throws InvalidConstraint;
	
}
