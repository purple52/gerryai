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

import org.gerryai.htn.simple.constraint.SubstitutableValidatableConstraint;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
import org.gerryai.htn.tasknetwork.Task;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskNetwork implements SubstitutableTaskNetwork {

	/**
	 * Set of tasks to be solved in this network.
	 */
	private Set<SubstitutableTask> tasks;
	
	/**
	 * Set of constraints to be met.
	 */
	private Set<SubstitutableValidatableConstraint> constraints;
	
	/**
	 * Constructor for a simple task.
	 * @param builder the builder to build the task
	 */
	protected SimpleTaskNetwork(TaskNetworkBuilder<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
			SubstitutableValidatableConstraint> builder) {
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
	public final Set<SubstitutableValidatableConstraint> getConstraints() {
		return constraints;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setConstraints(Set<SubstitutableValidatableConstraint> constraints) {
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
