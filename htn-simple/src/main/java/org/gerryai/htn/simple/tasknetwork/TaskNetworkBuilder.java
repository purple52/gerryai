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
import org.gerryai.logic.Term;

/**
 * Interface for a task network builder.
 * @param <T> type of term used by the task network
 * @param <K> class of tasks
 * @param <N> type of task network being built
 * @param <C> class of constraints
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskNetworkBuilder<
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>> {

	/**
	 * Add a task.
	 * @param task the task
	 * @return the updated builder
	 */
	TaskNetworkBuilder<T, K, N, C> addTask(K task);

	/**
	 * Add a set of tasks.
	 * @param tasks the tasks
	 * @return the updated builder
	 */
	TaskNetworkBuilder<T, K, N, C> addTasks(Set<K> tasks);
	
	/**
	 * Add a constraint.
	 * Checks if the constraint is valid for this network:
	 * 1. Makes sure the tasks referenced in the constraint all exist in the network
	 * 2. Makes sure that there are no cyclical precedence constraints
	 * @param constraint the constraint
	 * @return the updated builder
	 * @throws InvalidConstraint if the constraint could not be added
	 */
	TaskNetworkBuilder<T, K, N, C> addConstraint(C constraint) throws InvalidConstraint;
	
	/**
	 * Add a set of constraints.
	 * Checks if the constraints are valid for this network as per setConstraint.
	 * @param constraints the constraints
	 * @return the updated builder
	 * @throws InvalidConstraint if any of the constraints could not be added
	 */
	TaskNetworkBuilder<T, K, N, C> addConstraints(Set<C> constraints) throws InvalidConstraint;
	
	/**
	 * Adds all the tasks and constraints from the given network, replacing any existing.
	 * @param taskNetwork the network to copy from
	 * @return the updated builder
	 * @throws InvalidConstraint if any of the network's constraints could not be added
	 */
	TaskNetworkBuilder<T, K, N, C> copy(N taskNetwork)  throws InvalidConstraint;
	
	/**
	 * Get the set of tasks for the task network to be built.
	 * @return the tasks
	 */
	Set<K> getTasks();
	
	/**
	 * Get the set of constraints for the task network to be built.
	 * @return the constraints
	 */
	Set<C> getConstraints();
	
	/**
	 * Build the task network.
	 * @return the task network
	 */
	N build();
}
