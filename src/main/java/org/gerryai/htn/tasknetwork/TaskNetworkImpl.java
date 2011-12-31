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
package org.gerryai.htn.tasknetwork;

import java.util.Set;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class TaskNetworkImpl implements TaskNetwork {

	/**
	 * Set of tasks to be solved in this network.
	 */
	private Set<Task> tasks;
	
	/**
	 * Set of constraints to be met.
	 */
	private Set<Constraint> constraints;
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Constraint> getConstraints() {
		return constraints;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setConstraints(Set<Constraint> constraints) {
		this.constraints = constraints;
	}

}
