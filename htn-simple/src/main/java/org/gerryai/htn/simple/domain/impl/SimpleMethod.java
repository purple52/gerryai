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
package org.gerryai.htn.simple.domain.impl;

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleMethod implements Method {

	/**
	 * Name of this method.
	 */
	private String name;
	
	/**
	 * Task that this method decomposes.
	 */
	private Task task;
	
	/**
	 * Sub tasks that this method decomposes its task into.
	 */
	private TaskNetwork taskNetwork;
	
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Task getTask() {
		return task;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTask(Task task) {
		this.task = task;
	}

	/**
	 * {@inheritDoc}
	 */
	public final TaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTaskNetwork(TaskNetwork taskNetwork) {
		this.taskNetwork = taskNetwork;
	}

}
