package org.gerryai.htn.simple.problem.impl;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
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
public class SimpleProblem implements Problem<SubstitutableOperator, SubstitutableMethod,
		ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
		ImmutableConstraint<?>, ImmutableCondition<?>> {

	/**
	 * The initial state for this problem.
	 */
	private State state;
	
	/**
	 * The task network to achieve.
	 */
	private ImmutableTaskNetwork taskNetwork;
	
	/**
	 * The domain to operate in.
	 */
	private Domain<SubstitutableOperator, SubstitutableMethod, ImmutableTerm<?>,
			ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition<?>> domain;
	
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
	public final ImmutableTaskNetwork getTaskNetwork() {
		return taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTaskNetwork(ImmutableTaskNetwork taskNetwork) {
		this.taskNetwork = taskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Domain<SubstitutableOperator, SubstitutableMethod, ImmutableTerm<?>,
			ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition<?>> getDomain() {
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDomain(Domain<SubstitutableOperator, SubstitutableMethod, ImmutableTerm<?>,
			ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition<?>> domain) {
		this.domain = domain;
	}

}
