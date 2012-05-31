package org.gerryai.htn.simple.problem;

import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.domain.ImmutableDomain;
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
 * Interface for a build erthan can build an immutable problem.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableProblemBuilder {

    /**
     * Set the initial state.
     * @param state the state
     * @return an updated builder object
     */
    ImmutableProblemBuilder setState(State state);
    
    /**
     * Set the task network to be solved.
     * @param taskNetwork the task network
     * @return an updated builder object
     */
    ImmutableProblemBuilder setTaskNetwork(ImmutableTaskNetwork taskNetwork);
    
	/**
	 * Set the domain to be used by the problem.
	 * @param domain the domain
	 * @return an updated builder object
	 */
	ImmutableProblemBuilder setDomain(ImmutableDomain domain);

    /**
     * Build the finished problem.
     * @return the problem
     */
    ImmutableProblem build();

}
