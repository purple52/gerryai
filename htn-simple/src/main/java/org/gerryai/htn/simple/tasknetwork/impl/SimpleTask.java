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

import java.util.Collections;
import java.util.List;

import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.TaskBuilder;

import com.google.common.base.Objects;

/**
 * Basic implementation of the Task interface.
 */
public class SimpleTask implements SubstitutableTask {
	
	/**
	 * Name for this task.
	 */
	private String name;
	
	/**
	 * Whether this task is primitive.
	 */
	private boolean isPrimitive;
	
	/**
	 * Arguments for this task.
	 */
	private List<SubstitutableTerm> arguments;

	/**
	 * Constructor for a simple task.
	 * @param builder the builder to build the task
	 */
	protected SimpleTask(TaskBuilder<SubstitutableTerm, SubstitutableTask> builder) {
		this.name = builder.getName();
		this.arguments = builder.getArguments();
		this.isPrimitive = builder.isPrimitive();
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
	public final boolean isPrimitive() {
		return isPrimitive;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<SubstitutableTerm> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void apply(Substituter<SubstitutableTerm> substituter) {
		substituter.visit(arguments);
	}
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(name, arguments);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimpleTask) {
	        final SimpleTask other = (SimpleTask) obj;
	        return Objects.equal(name, other.name)
	            && Objects.equal(arguments, other.arguments);
	    } else {
	        return false;
	    }
	}

}
