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

import java.util.Set;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;

/**
 * Simple representation of a domain.
 * Wraps a set of operators and a set of methods to define the domain.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleDomain implements Domain<ImmutableOperator, SubstitutableMethod,
		ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
		ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> {

	/**
	 * Operators available in this domain.
	 */
	private Set<ImmutableOperator> operators;
	
	/**
	 * Methods available in this domain.
	 */
	private Set<SubstitutableMethod> methods;
	
	/**
	 * Constructor for a simple domain.
	 * @param builder the builder to build this domain from
	 */
	protected SimpleDomain(SimpleDomainBuilder builder) {
		operators = builder.getOperators();
		methods = builder.getMethods();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableOperator> getOperators() {
		return operators;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setOperators(Set<ImmutableOperator> operators) {
		this.operators = operators;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<SubstitutableMethod> getMethods() {
		return methods;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setMethods(Set<SubstitutableMethod> methods) {
		this.methods = methods;
	}

}
