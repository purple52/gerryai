package org.gerryai.htn.simple.problem.impl;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.domain.impl.SimpleMethod;
import org.gerryai.htn.simple.domain.impl.SimpleOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;

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
public class SimpleProblem implements Problem<SimpleOperator, SimpleMethod,
		SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
		ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition> {

	/**
	 * The initial state for this problem.
	 */
	private State state;
	
	/**
	 * The task network to acheive.
	 */
	private SubstitutableTaskNetwork taskNetwork;
	
	/**
	 * The domain to operate in.
	 */
	private Domain<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
	ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition> domain;
	
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
	public final SubstitutableTaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTaskNetwork(SubstitutableTaskNetwork taskNetwork) {
		this.taskNetwork = taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Domain<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
			ValidatableConstraint<SubstitutableTerm, SubstitutableTask,
			SubstitutableCondition>, SubstitutableCondition> getDomain() {
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDomain(Domain<SimpleOperator, SimpleMethod, SubstitutableTerm,
			SubstitutableTask, SubstitutableTaskNetwork,
			ValidatableConstraint<SubstitutableTerm, SubstitutableTask,
			SubstitutableCondition>, SubstitutableCondition> domain) {
		this.domain = domain;
	}

}
