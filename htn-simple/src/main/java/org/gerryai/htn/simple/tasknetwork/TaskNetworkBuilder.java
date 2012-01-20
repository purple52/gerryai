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
package org.gerryai.htn.simple.tasknetwork;

import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Interface for a task network builder.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskNetworkBuilder {

	/**
	 * Add a task.
	 * @param task the task
	 * @return the updated builder
	 */
	TaskNetworkBuilder addTask(Task task);

	/**
	 * Add a set of tasks.
	 * @param task the tasks
	 * @return the updated builder
	 */
	TaskNetworkBuilder addTasks(Set<Task> task);
	
	/**
	 * Add a constraint.
	 * @param constraint the constraint
	 * @return the updated builder
	 */
	TaskNetworkBuilder addConstraint(Constraint constraint);
	
	/**
	 * Add a set of constraints.
	 * @param constraints the constraints
	 * @return the updated builder
	 */
	TaskNetworkBuilder addConstraints(Set<Constraint> constraints);
	
	/**
	 * Build the task network.
	 * @return the task network
	 */
	TaskNetwork build();
}
