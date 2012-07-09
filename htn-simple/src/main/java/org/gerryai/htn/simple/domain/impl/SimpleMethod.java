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

import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableMethodBuilder;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.tasknetwork.Task;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleMethod implements ImmutableMethod {

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
	private ImmutableTaskNetwork taskNetwork;
	
	/**
	 * Constructor using a builder.
	 * @param builder the builder to use
	 */
	protected SimpleMethod(ImmutableMethodBuilder builder) {
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
	public final Task getTask() {
		return task;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableTaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

	/**
	 * Builder class for SimpleMethod objects.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
	public static class Builder implements ImmutableMethodBuilder {

	    /**
	     * Name of the method being built.
	     */
	    private String name;

	    /**
	     * Task that the method being built will decompose.
	     */
	    private Task task;

	    /**
	     * Task network that the method being built decomposes into.
	     */
	    private ImmutableTaskNetwork taskNetwork;

	    /**
         * Default constructor.
         */
        protected Builder() {
            // Does nothing except not being public
        }
        
	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableMethodBuilder setName(String name) {
	        this.name = name;
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableMethodBuilder setTask(Task task) {
	        this.task = task;
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableMethodBuilder setTaskNetwork(
	            ImmutableTaskNetwork taskNetwork) {
	        this.taskNetwork = taskNetwork;
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableMethod build() {
	        return new SimpleMethod(this);
	    }

	    /**
	     * Get the name of the method being built.
	     * @return the name
	     */
	    public final String getName() {
	        return name;
	    }

	    /**
	     * Get the task for the method being built.
	     * @return the task
	     */
	    public final Task getTask() {
	        return task;
	    }

	    /**
	     * Get the task network for the method being built.
	     * @return the task network
	     */
	    public final ImmutableTaskNetwork getTaskNetwork() {
	        return taskNetwork;
	    }
	}
}
