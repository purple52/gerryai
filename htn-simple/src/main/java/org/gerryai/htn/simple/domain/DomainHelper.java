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
import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Variable;

/**
 * Interface for a service that manages a domain.
 * @param <E> type of effect this domain helper uses
 * @param <D> type of domain this helper uses
 * @param <O> type of operator this domain helper uses
 * @param <M> type of method this domain helpers uses
 * @param <N> type of task network this domain helper uses
 * @param <C> type of constraint this domain helper uses
 * @param <I> the class of condition the domain will handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface DomainHelper<
        E extends Effect,
        D extends Domain<E, O, M, N, C, I>,
		O extends Operator<E, I>,
		M extends Method<N, C>,
		N extends TaskNetwork<C>,
		C extends Constraint,
		I extends Condition> {
	
	/**
	 * Get the domain that this service is managing.
	 * @return the domain
	 */
	D getDomain();
	
	/**
	 * Get an operator by name.
	 * @param name the name of the operator to get
	 * @return the operator
	 * @throws OperatorNotFound if no such operator exists
	 */
	O getOperatorByName(String name) throws OperatorNotFound;
	
	/**
	 * Get a set of methods that match the given task.
	 * @param task the task being matched
	 * @return a set of matching methods
	 */
	Set<M> getMethodsByTask(Task task);
	
	/**
	 * Get a grounded version of the supplied effect by applying the given bindings.
	 * @param effect the effect to ground
	 * @param bindings the bindings to apply
	 * @return the grounded effect
	 */
	E getGroundedEffect(E effect, Map<Variable, Constant> bindings);
	
	/**
	 * Get a grounded version of the supplied condition by applying the given bindings.
	 * @param condition the condition to ground
	 * @param bindings the bindings to apply
	 * @return the grounded condition
	 */
	I getGroundedCondition(I condition, Map<Variable, Constant> bindings);
	
}
