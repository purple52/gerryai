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

import java.util.List;

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

import com.google.common.base.Objects;

/**
 * Basic implementation of the Task interface.
 */
public abstract class SimpleTask implements Task {
	
	/**
	 * Name for this task.
	 */
	private String name;
	
	/**
	 * Arguments for this task.
	 */
	private List<Term> arguments;

	/**
	 * Constructor for a simple task.
	 * @param builder the builder to build the task
	 */
	protected SimpleTask(SimpleTaskBuilder builder) {
		this.setName(builder.getName());
		this.setArguments(builder.getArguments());
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
	public final List<Term> getArguments() {
		return arguments;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void setArguments(List<Term> arguments) {
		this.arguments = arguments;
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
