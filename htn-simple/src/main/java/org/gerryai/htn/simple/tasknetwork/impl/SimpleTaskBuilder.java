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

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.TaskBuilder;

/**
 * Simple builder for simple tasks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskBuilder implements TaskBuilder<SubstitutableTerm, SubstitutableTask> {

	/**
	 * Name of the task being built.
	 */
	private String name;
	
	/**
	 * Whether the task is primitive.
	 */
	private Boolean isPrimitive;
	
	/**
	 * List of arguments for the task.
	 */
	private List<SubstitutableTerm> arguments;
	
	/**
	 * Default constructor.
	 */
	protected SimpleTaskBuilder() {
		arguments = new ArrayList<SubstitutableTerm>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder setIsPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder addArgument(SubstitutableTerm term) {
		arguments.add(term);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder addArguments(List<SubstitutableTerm> terms) {
		arguments.addAll(terms);
		return this;
	}

	/**
     * {@inheritDoc}
     */
	public final SimpleTaskBuilder copy(SubstitutableTask task) {
	    name = task.getName();
	    arguments = new ArrayList<SubstitutableTerm>(task.getArguments());
	    isPrimitive = task.isPrimitive();
	    return this;
	}

	/**
     * {@inheritDoc}
     */
	public final SimpleTaskBuilder apply(Substituter<SubstitutableTerm> substituter) {
	    substituter.visit(arguments);
	    return this;
	}
	
    /**
     * {@inheritDoc}
     */
    public final SubstitutableTask build() {
        return new SimpleTask(this);
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
		return arguments;
	}
}
