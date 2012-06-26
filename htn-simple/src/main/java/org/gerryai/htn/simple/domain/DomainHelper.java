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
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface for a service that manages a domain.
 * @param <E> type of effect this domain helper uses
 * @param <D> type of domain this helper uses
 * @param <O> type of operator this domain helper uses
 * @param <M> type of method this domain helpers uses
 * @param <T> type of logical term this domain helper uses
 * @param <K> type of task this domain helper uses
 * @param <N> type of task network this domain helper uses
 * @param <C> type of constraint this domain helper uses
 * @param <I> the class of condition the domain will handle
 * @param <V> type of variable this domain will handle
 * @param <Const> type of constant this domain with handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface DomainHelper<
        E extends Effect<T>,
        D extends Domain<E, O, M, T, K, N, C, I, V>,
		O extends Operator<E, T, I, V>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition<T>,
		V extends Variable,
		Const extends Constant> {
	
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
	Set<M> getMethodsByTask(K task);
	
	/**
	 * Get a grounded version of the supplied effect by applying the given bindings.
	 * @param effect the effect to ground
	 * @param bindings the bindings to apply
	 * @return the grounded effect
	 */
	E getGroundedEffect(E effect, Map<V, Const> bindings);
	
	/**
	 * Get a grounded version of the supplied condition by applying the given bindings.
	 * @param condition the condition to ground
	 * @param bindings the bindings to apply
	 * @return the grounded condition
	 */
	I getGroundedCondition(I condition, Map<V, Const> bindings);
	
}
