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

import java.util.Map;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * Interface for a builder that creates immutable task networks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableTaskNetworkBuilder {

	/**
	 * Add a task.
	 * @param task the task
	 * @return the updated builder
	 */
	ImmutableTaskNetworkBuilder addTask(ImmutableTask task);

	/**
	 * Add a set of tasks.
	 * @param tasks the tasks
	 * @return the updated builder
	 */
	ImmutableTaskNetworkBuilder addTasks(Set<ImmutableTask> tasks);
	
	/**
	 * Add a constraint.
	 * Checks if the constraint is valid for this network:
	 * 1. Makes sure the tasks referenced in the constraint all exist in the network
	 * 2. Makes sure that there are no cyclical precedence constraints
	 * @param constraint the constraint
	 * @return the updated builder
	 * @throws InvalidConstraint if the constraint could not be added
	 */
	ImmutableTaskNetworkBuilder addConstraint(ImmutableConstraint<?> constraint) throws InvalidConstraint;
	
	/**
	 * Add a set of constraints.
	 * Checks if the constraints are valid for this network as per setConstraint.
	 * @param constraints the constraints
	 * @return the updated builder
	 * @throws InvalidConstraint if any of the constraints could not be added
	 */
	ImmutableTaskNetworkBuilder addConstraints(Set<ImmutableConstraint<?>> constraints) throws InvalidConstraint;
	
	/**
	 * Adds all the tasks and constraints from the given network, replacing any existing.
	 * @param taskNetwork the network to copy from
	 * @return the updated builder
	 * @throws InvalidConstraint if any of the network's constraints could not be added
	 */
	ImmutableTaskNetworkBuilder copy(ImmutableTaskNetwork taskNetwork)  throws InvalidConstraint;
	
    /**
     * Apply the provided substituter to the arguments provided so far.
     * @param substitution the substitution to apply
     * @return the updated builder
     */
	ImmutableTaskNetworkBuilder apply(Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution);
	
	/**
	 * Replace the given task with the network of tasks and constraints provided.
	 * @param oldTask the task to replace
	 * @param taskNetwork the network to replace with
	 * @return the updated builder
	 */
	ImmutableTaskNetworkBuilder replace(ImmutableTask oldTask, ImmutableTaskNetwork taskNetwork);
	
	/**
	 * Get the set of tasks for the task network to be built.
	 * @return the tasks
	 */
	Set<ImmutableTask> getTasks();
	
	/**
	 * Get the set of constraints for the task network to be built.
	 * @return the constraints
	 */
	Set<ImmutableConstraint<?>> getConstraints();
	
	/**
	 * Build the task network.
	 * @return the task network
	 */
	ImmutableTaskNetwork build();
}
