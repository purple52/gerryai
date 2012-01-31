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
import org.gerryai.htn.simple.domain.MethodBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleMethodBuilder implements MethodBuilder<Task, TaskNetwork> {

	private String name;
	
	private Task task;
	
	private TaskNetwork taskNetwork;
	
	/**
	 * {@inheritDoc}
	 */
	public MethodBuilder<Task, TaskNetwork> setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public MethodBuilder<Task, TaskNetwork> setTask(Task task) {
		this.task = task;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public MethodBuilder<Task, TaskNetwork> setTaskNetwork(TaskNetwork taskNetwork) {
		this.taskNetwork = taskNetwork;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public Method build() {
		return new SimpleMethod(this);
	}
	
	protected String getName() {
		return name;
	}
	
	protected Task getTask() {
		return task;
	}
	
	protected TaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

}
