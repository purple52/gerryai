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
package org.gerryai.htn.simple.plan;

import java.util.Map;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * @param <O> type of operator this action uses
 * @param <I> type of condition the action uses
 * @param <T> type of logical term this helper has to deal with
 * @param <K> type of task this helper works with
 * @param <V> type of variable this helper works with
 * @param <C> type of constant thishelper works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ActionFactoryHelper<
        O extends Operator<T, I, V>,
        T extends Term,
        K extends Task<T>,
        I extends Condition<T>,
        V extends Variable,
        C extends Constant> {
	
	/**
	 * Get the operator for the given task.
	 * @param task the task
	 * @return the operator
	 * @throws TaskNotActionable if an operator does not exist for this task
	 */
	O getOperator(K task) throws TaskNotActionable;
	
	/**
	 * Get the bindings for grounding this task.
	 * @param task the task
	 * @param operator the operator
	 * @return a set of bindings that ground this operator to implement this task
	 * @throws TaskNotActionable if a set of bindings cannot be generated
	 */
	Map<V, C> getBindings(K task, O operator) throws TaskNotActionable;

}
