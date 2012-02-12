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

import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.domain.MethodBuilder;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleMethodBuilder implements MethodBuilder<SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> {

	/**
	 * Name of the method being built.
	 */
	private String name;
	
	/**
	 * Task that the method being built will decompose.
	 */
	private SubstitutableTask task;
	
	/**
	 * Task network that the method being built decomposes into.
	 */
	private SimpleTaskNetwork taskNetwork;
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleMethodBuilder setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleMethodBuilder setTask(SubstitutableTask task) {
		this.task = task;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleMethodBuilder setTaskNetwork(SimpleTaskNetwork taskNetwork) {
		this.taskNetwork = taskNetwork;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleMethod build() {
		return new SimpleMethod(this);
	}
	
	/**
	 * Get the name of the method being built.
	 * @return the name
	 */
	protected final String getName() {
		return name;
	}
	
	/**
	 * Get the task for the method being built.
	 * @return the task
	 */
	protected final SubstitutableTask getTask() {
		return task;
	}
	
	/**
	 * Get the task network for the method being built.
	 * @return the task network
	 */
	protected final SimpleTaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

}
