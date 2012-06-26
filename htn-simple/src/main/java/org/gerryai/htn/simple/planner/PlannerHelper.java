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
package org.gerryai.htn.simple.planner;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.planner.impl.NonPrimitiveTaskNotFound;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface for a helper class to support a planner.
 * @param <E> type of effect this helper handles
 * @param <ST> type of state this helper handles
 * @param <P> type of plan this helper handles
 * @param <A> type of action this planner works with
 * @param <O> type of operator this planner helper uses
 * @param <M> type of method the planner helper works with
 * @param <T> type of term the planner works with
 * @param <K> type of task this planner works with
 * @param <N> type of task network this planner works with
 * @param <C> type of constraint this planner works with
 * @param <I> type of condition the planner uses
 * @param <V> type of variable the planner uses
 * @param <S> type of constant the planner works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface PlannerHelper<
        E extends Effect<T>,
        ST extends State,
        P extends Plan<E, T, A, O, I, V, S>,
        A extends Action<E, T, O, I, V, S>,
		O extends Operator<E, T, I, V>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition<T>,
		V extends Variable,
		S extends Constant> {
	
	/**
	 * Check for obvious reasons why the given task network is unsolvable.
	 * @param taskNetwork task network to check
	 * @return whether the task network is unsolvable
	 */
	boolean isUnsolvable(N taskNetwork);
	
	/**
	 * Try to find a plan for a primitive task network by converting the tasks into 
	 * grounded operators represented as actions.
	 * @param state the state to work from
	 * @param taskNetwork the task network to convert
	 * @return the plan
	 * @throws PlanNotFound if no plan exists
	 */
	P findPlanForPrimitive(ST state, N taskNetwork) throws PlanNotFound;
	
	/**
	 * Try to get a non-primitive task from a given network.
	 * @param taskNetwork the task network
	 * @return a non-primitive task
	 * @throws NonPrimitiveTaskNotFound if no non-primitive tasks are present in the task network
	 */
	K getNonPrimitiveTask(N taskNetwork) throws NonPrimitiveTaskNotFound;
	
	/**
	 * Try to decompose the given task within a task network using the given method.
	 * @param taskNetwork the task network
	 * @param task the task to decompose
	 * @param method the method to use to decompose the task
	 * @return the decomposed task network
	 * @throws DecompositionNotFound if the method could not be used to decompose the given task
     * @throws InvalidConstraint if the decomposition restulted in an invalid constraint
	 */
	N decompose(N taskNetwork, K task, M method) throws DecompositionNotFound, InvalidConstraint;
}
