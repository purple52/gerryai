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
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Interface that a planner must implement.
 * @param <E> type of effect this planner works with
 * @param <ST> type of state this planner works with
 * @param <P> type of plan this planner creates
 * @param <A> type of action this planner works with
 * @param <O> type of operator this planner works with
 * @param <M> type of method this planner works with
 * @param <N> type of task network this planner works with
 * @param <C> type of constraint this planner works with
 * @param <I> the class of condition the planner will handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Planner<
        E extends Effect,
        ST extends State,
        P extends Plan<E, A, O, I>,
        A extends Action<E, O, I>,
		O extends Operator<E, I>,
		M extends Method<N, C>,
		N extends TaskNetwork<C>,
		C extends Constraint,
		I extends Condition> {

	/**
	 * Finds a plan that achieves the given task network.
	 * @param state the initial state
	 * @param taskNetwork the task network to achieve
	 * @return the plan found
	 * @throws PlanNotFound if no plan could be found
	 */
	P findPlan(ST state, N taskNetwork) throws PlanNotFound;
		
}
