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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.gerryai.htn.simple.logic.LogicFactory;
import org.gerryai.htn.simple.tasknetwork.TaskBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

import com.google.common.base.Objects;

/**
 * Basic implementation of the Task interface.
 */
public class SimpleTask implements Task {
	
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
	private List<Term> arguments;
	
    /**
     * Logic factory used to apply substitutions to arguments.
     */
    private LogicFactory logicFactory;
    
	/**
	 * Constructor for a simple task.
	 * @param builder the builder to build the task
	 */
	protected SimpleTask(TaskBuilder builder) {
		this.name = builder.getName();
		this.arguments = builder.getArguments();
		this.isPrimitive = builder.isPrimitive();
		this.logicFactory = builder.getLogicFactory();
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
	public final List<Term> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	/**
     * {@inheritDoc}
     */
	public final Task applyToCopy(Map<Term, Term> substitution) {
        return new Builder(logicFactory)
	        .copy(this)
	        .apply(substitution)
	        .build();
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
	
	/**
	 * Builder for SimpleTask.
	 */
	public static class Builder implements TaskBuilder {

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
	    private List<Term> arguments;
	    
	    /**
	     * Logic factory used to apply substitutions to arguments.
	     */
	    private LogicFactory logicFactory;

	    /**
	     * Constructor.
	     * @param logicFactory the logic factory this builder will use
	     */
	    protected Builder(LogicFactory logicFactory) {
	        this.logicFactory = logicFactory;
	        arguments = new ArrayList<Term>();
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public final Builder setName(String name) {
	        this.name = name;
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder setIsPrimitive(boolean isPrimitive) {
	        this.isPrimitive = isPrimitive;
	        return this;
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public final Builder addArgument(Term term) {
	        arguments.add(term);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder addArguments(List<Term> terms) {
	        arguments.addAll(terms);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder copy(Task task) {
	        name = task.getName();
	        arguments = new ArrayList<Term>(task.getArguments());
	        isPrimitive = task.isPrimitive();
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder apply(Map<Term, Term> substitution) {
	        arguments = logicFactory.apply(arguments, substitution);
	        return this;
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public final Task build() {
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
	    public final List<Term> getArguments() {       
	        return arguments;
	    }

	    /**
         * {@inheritDoc}
         */
	    public final LogicFactory getLogicFactory() {
	        return logicFactory;
	    }
	}
	
	@Override
	public final String toString() {
	    return new StringBuilder()
	            .append(name)
	            .append(arguments.toString())
	            .toString();
	}
}
