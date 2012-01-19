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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Builder for simple task networks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkBuilder implements TaskNetworkBuilder {

	/**
	 * Set of tasks we are building up.
	 */
	private Set<Task> tasks;
	
	/**
	 * Set of constraints we are building up.
	 */
	private Set<Constraint> constraints;
	
	/**
	 * Default constructor.
	 */
	public SimpleTaskNetworkBuilder() {
		tasks = new HashSet<Task>();
		constraints = new HashSet<Constraint>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addTask(Task task) {
		tasks.add(task);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addTasks(Set<Task> tasks) {
		tasks.addAll(tasks);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addConstraint(Constraint constraint) {
		constraints.add(constraint);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final TaskNetwork build() {
		SimpleTaskNetwork taskNetwork = new SimpleTaskNetwork();
		taskNetwork.setTasks(tasks);
		taskNetwork.setConstraints(constraints);
		
		return taskNetwork;
	}

}
