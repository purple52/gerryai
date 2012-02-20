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
import org.gerryai.htn.simple.tasknetwork.TaskBuilder;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Generic builder for tasks.
 * @param <K> type of task this builder will create
 * @param <B> type of task builder extending this base class
 * @author David Edwards <david@more.fool.me.uk>
 */
public abstract class AbstractTaskBuilder<K extends Task<SubstitutableTerm>,
		B extends AbstractTaskBuilder<K, B>> implements TaskBuilder<SubstitutableTerm, K> {

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
	 * Base task to build from.
	 */
	private K baseTask;
	
	/**
	 * Substituter to apply during building.
	 */
	private Substituter<SubstitutableTerm> substituter;
	
	/**
	 * Default constructor.
	 */
	protected AbstractTaskBuilder() {
		arguments = new ArrayList<SubstitutableTerm>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final B setName(String name) {
		this.name = name;
		return me();
	}

	/**
	 * {@inheritDoc}
	 */
	public final B setIsPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
		return me();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final B addArgument(SubstitutableTerm term) {
		arguments.add(term);
		return me();
	}

	/**
	 * {@inheritDoc}
	 */
	public final B addArguments(List<SubstitutableTerm> terms) {
		arguments.addAll(terms);
		return me();
	}

	/**
     * {@inheritDoc}
     */
	public final B setBaseTask(K task) {
	    baseTask = task;
	    return me();
	}

	   /**
     * {@inheritDoc}
     */
	public final B setSubstituter(Substituter<SubstitutableTerm> substituter) {
	    this.substituter = substituter;
	    return me();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public abstract K build();

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		if (name == null && baseTask != null) {
            return baseTask.getName();		    
		} else {
		    return name;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean isPrimitive() {
	    if (isPrimitive == null && baseTask != null) {
	        return baseTask.isPrimitive();
	    } else {
	        return isPrimitive;
	    }
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<SubstitutableTerm> getArguments() {
	    
	    int arraySize = 0;
	    if (baseTask != null) {
	        arraySize = arraySize + baseTask.getArguments().size();
	    }
	    arraySize = arraySize + arguments.size();
	    List<SubstitutableTerm> outputArguments = new ArrayList<SubstitutableTerm>(arraySize);

	    if (baseTask != null) {
	        outputArguments.addAll(baseTask.getArguments());
	    }
	    outputArguments.addAll(arguments);

	    // Apply the substituter if there is one
	    if (substituter != null) {
	        substituter.visit(outputArguments);
	    }
	    
		return outputArguments;
	}

	/**
     * {@inheritDoc}
     */
    public final K getBaseTask() {
        return baseTask;
    }

    /**
     * {@inheritDoc}
     */
    public final Substituter<SubstitutableTerm> getSubstituter() {
        return substituter;
    }
    
	/**
	 * Return this.
	 * @return this
	 */
	protected abstract B me();

}
