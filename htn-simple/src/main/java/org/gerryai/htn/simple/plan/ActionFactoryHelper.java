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

import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Bindings;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.tasknetwork.Task;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface ActionFactoryHelper {
	
	/**
	 * Get the operator for the given task.
	 * @param task the task
	 * @return the operator
	 * @throws TaskNotActionable if an operator does not exist for this task
	 */
	Operator getOperator(Task task) throws TaskNotActionable;
	
	/**
	 * Get the bindings for grounding this task.
	 * @param task the task
	 * @param operator the operator
	 * @return a set of bindings that ground this operator to implement this task
	 * @throws TaskNotActionable if a set of bindings cannot be generated
	 */
	Bindings getBindings(Task task, Operator operator) throws TaskNotActionable;

}
