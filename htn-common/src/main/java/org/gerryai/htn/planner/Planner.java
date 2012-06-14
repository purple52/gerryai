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
package org.gerryai.htn.planner;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface that a planner must implement.
 * @param <P> type of plan this planner creates
 * @param <A> type of action this planner works with
 * @param <O> type of operator this planner works with
 * @param <M> type of method this planner works with
 * @param <T> type of logical term this planner works with
 * @param <K> type of task this planner works with
 * @param <N> type of task network this planner works with
 * @param <C> type of constraint this planner works with
 * @param <I> the class of condition the planner will handle
 * @param <V> type of variable the planner will handle
 * @param <S> type of constant the planner will handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Planner<
        P extends Plan<T, A, O, I, V, S>,
        A extends Action<T, O, I, V, S>,
		O extends Operator<T, I, V>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition<T>,
		V extends Variable,
		S extends Constant> {

	/**
	 * Finds a plan that achieves the given task network.
	 * @param state the initial state
	 * @param taskNetwork the task network to achieve
	 * @return the plan found
	 * @throws PlanNotFound if no plan could be found
	 */
	P findPlan(State state, N taskNetwork) throws PlanNotFound;
		
}
