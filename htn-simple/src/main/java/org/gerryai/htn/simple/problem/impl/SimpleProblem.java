package org.gerryai.htn.simple.problem.impl;

import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.problem.ImmutableProblem;
import org.gerryai.htn.simple.problem.ImmutableProblemBuilder;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;

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

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleProblem implements ImmutableProblem {

	/**
	 * The initial state for this problem.
	 */
	private ImmutableState state;
	
	/**
	 * The task network to achieve.
	 */
	private ImmutableTaskNetwork taskNetwork;
	
	/**
	 * The domain to operate in.
	 */
	private ImmutableDomain domain;
	
	/**
	 * Constructor called only by builder class.
	 * @param builder the builder to build from
	 */
	protected SimpleProblem(Builder builder) {
	    this.state = builder.state;
	    this.taskNetwork = builder.taskNetwork;
	    this.domain = builder.domain;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableState getState() {
		return state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableTaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableDomain getDomain() {
		return domain;
	}

	/**
	 * Builder class for SimpleProblem.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
	public static class Builder implements ImmutableProblemBuilder {
	    
	    /**
	     * The initial state for this problem.
	     */
	    private ImmutableState state;
	    
	    /**
	     * The task network to achieve.
	     */
	    private ImmutableTaskNetwork taskNetwork;
	    
	    /**
	     * The domain to operate in.
	     */
	    private ImmutableDomain domain;
	    
	    /**
	     * Set the initial state.
	     * @param state the state
	     * @return an updated builder object
	     */
	    public final Builder setState(ImmutableState state) {
	        this.state = state;
	        return this;
	    }
	    
	    /**
	     * Set the task network to be solved.
	     * @param taskNetwork the task network
         * @return an updated builder object
  	     */
	    public final Builder setTaskNetwork(ImmutableTaskNetwork taskNetwork) {
	        this.taskNetwork = taskNetwork;
	        return this;
	    }
	    
    	/**
    	 * Set the domain to be used by the problem.
    	 * @param domain the domain
    	 * @return an updated builder object
    	 */
    	public final Builder setDomain(ImmutableDomain domain) {
    		this.domain = domain;
    		return this;
    	}
    	
        /**
         * Build the finished problem.
         * @return the problem
         */
        public final ImmutableProblem build() {
            return new SimpleProblem(this);
        }
	}
}
