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
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface for factories that create planners.
 * @param <D> type of domain this planner factory creates
 * @param <O> type of operator this planner factory uses
 * @param <M> type of method this planner factory uses
 * @param <T> type of logical term this planner factory uses
 * @param <K> type of task this planner factory uses
 * @param <N> type of task network this planner factory uses
 * @param <C> type of constraint this planner factory uses
 * @param <I> type of condition this factory uses
 * @param <V> type of variable this factory uses
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface PlannerFactory<
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
	 * Create a planner instance using the domain provided.
	 * @param domain the domain the planner will work in
	 * @return the planner
	 */
	Planner<O, M, T, K, N, C, I, V> create(D domain);
	
}
