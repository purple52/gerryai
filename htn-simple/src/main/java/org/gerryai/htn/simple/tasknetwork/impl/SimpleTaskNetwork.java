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
package org.gerryai.htn.simple.tasknetwork.impl;

import java.util.Set;

import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskNetwork implements TaskNetwork<SubstitutableTerm, SubstitutableTask,
		ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> {

	/**
	 * Set of tasks to be solved in this network.
	 */
	private Set<SubstitutableTask> tasks;
	
	/**
	 * Set of constraints to be met.
	 */
	private Set<ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> constraints;
	
	/**
	 * Constructor for a simple task.
	 * @param builder the builder to build the task
	 */
	protected SimpleTaskNetwork(TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> builder) {
		this.setTasks(builder.getTasks());
		this.setConstraints(builder.getConstraints());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<SubstitutableTask> getTasks() {
		return tasks;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTasks(Set<SubstitutableTask> tasks) {
		this.tasks = tasks;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> getConstraints() {
		return constraints;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setConstraints(Set<ValidatableConstraint<SubstitutableTerm,
			SubstitutableTask, SubstitutableCondition>> constraints) {
		this.constraints = constraints;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isPrimitive() {
		
		for (Task<SubstitutableTerm> task : getTasks()) {
			if (!task.isPrimitive()) {
				// If any of our tasks are non-primitive, the whole network is non-primitive
				return false;
			}
		}
		
		// None of our tasks were non-primitive, so the whole network is primitive
		return true;
	}

}
