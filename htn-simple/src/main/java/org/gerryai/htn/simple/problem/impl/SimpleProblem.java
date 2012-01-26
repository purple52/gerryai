package org.gerryai.htn.simple.problem.impl;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.tasknetwork.TaskNetwork;

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
public class SimpleProblem implements Problem {

	/**
	 * The initial state for this problem.
	 */
	private State state;
	
	/**
	 * The task network to acheive.
	 */
	private TaskNetwork taskNetwork;
	
	/**
	 * The domain to operate in.
	 */
	private Domain domain;
	
	/**
	 * {@inheritDoc}
	 */
	public final State getState() {
		return state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setState(State state) {
		this.state = state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final TaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTaskNetwork(TaskNetwork taskNetwork) {
		this.taskNetwork = taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Domain getDomain() {
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDomain(Domain domain) {
		this.domain = domain;
	}

}
