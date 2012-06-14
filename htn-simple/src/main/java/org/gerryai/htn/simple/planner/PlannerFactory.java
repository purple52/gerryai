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
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface for factories that create planners.
 * @param <L> type of planner this planner factory generates
 * @param <P> type of plan this planner factory uses
 * @param <A> type of action this planner factory uses
 * @param <D> type of domain this planner factory creates
 * @param <O> type of operator this planner factory uses
 * @param <M> type of method this planner factory uses
 * @param <T> type of logical term this planner factory uses
 * @param <K> type of task this planner factory uses
 * @param <N> type of task network this planner factory uses
 * @param <C> type of constraint this planner factory uses
 * @param <I> type of condition this factory uses
 * @param <V> type of variable this factory uses
 * @param <S> type of constant this factory uses
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface PlannerFactory<
        L extends Planner<P, A, O, M, T, K, N, C, I, V, S>,
        P extends Plan<T, A, O, I, V, S>,
        A extends Action<T, O, I, V, S>,
        D extends Domain<O, M, T, K, N, C, I, V>,
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
	 * Create a planner instance using the domain provided.
	 * @param domain the domain the planner will work in
	 * @return the planner
	 */
	L create(D domain);
	
}
