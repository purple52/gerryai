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
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.impl.SimpleTerm;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleMethod implements Method<SimpleTerm, SimpleTask, SimpleTaskNetwork,
		ValidatableConstraint<SimpleTerm, SimpleTask, SubstitutableCondition>> {

	/**
	 * Name of this method.
	 */
	private String name;
	
	/**
	 * Task that this method decomposes.
	 */
	private SimpleTask task;
	
	/**
	 * Sub tasks that this method decomposes its task into.
	 */
	private SimpleTaskNetwork taskNetwork;
	
	/**
	 * Constructor using a builder.
	 * @param builder the builder to use
	 */
	protected SimpleMethod(SimpleMethodBuilder builder) {
		name = builder.getName();
		task = builder.getTask();
		taskNetwork = builder.getTaskNetwork();
	}
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
	public final SimpleTask getTask() {
		return task;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTask(SimpleTask task) {
		this.task = task;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTaskNetwork(SimpleTaskNetwork taskNetwork) {
		this.taskNetwork = taskNetwork;
	}

}
