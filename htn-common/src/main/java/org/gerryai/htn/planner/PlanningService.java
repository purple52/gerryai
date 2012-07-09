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
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Interface for a service that can solve problems.
 * @param <E> type of effect this service works with
 * @param <St> type of state this service works with
 * @param <Pl> type of plan this service generates
 * @param <Pr> type of problem this service handles
 * @param <A> type of action this service works with
 * @param <D> type of domain this service works with
 * @param <O> type of operator this service works with
 * @param <M> type of method this service works with
 * @param <N> type of task network this service works with
 * @param <C> type of constraint this service works with
 * @param <I> the class of condition this service will handle
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface PlanningService<
        E extends Effect,
        St extends State,
        Pl extends Plan<E, A, O, I>,
        Pr extends Problem<E, St, D, O, M, N, C, I>,
        A extends Action<E, O, I>,
        D extends Domain<E, O, M, N, C, I>,
		O extends Operator<E, I>,
		M extends Method<N, C>,
		N extends TaskNetwork<C>,
		C extends Constraint,
		I extends Condition> {
	
	/**
	 * Find a plan that solve the given problem.
	 * @param problem the problem to be solved
	 * @return a solution
	 * @throws PlanNotFound if no plan exists for the given problem
	 */
	Pl solve(Pr problem) throws PlanNotFound;
	
}
