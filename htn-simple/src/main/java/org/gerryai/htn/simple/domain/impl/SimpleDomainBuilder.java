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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.DomainBuilder;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainBuilder implements	DomainBuilder<SimpleDomain, ImmutableOperator,
		ImmutableMethod, ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
		ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> {

	/**
	 * Set of operators the domain uses.
	 */
	private Set<ImmutableOperator> operators;
	
	/**
	 * Set of methods that the domain uses.
	 */
	private Set<ImmutableMethod> methods;
	
	/**
	 * Default constructor.
	 */
	protected SimpleDomainBuilder() {
		operators = new HashSet<ImmutableOperator>();
		methods = new HashSet<ImmutableMethod>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addOperator(ImmutableOperator operator) {
		operators.add(operator);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addMethod(ImmutableMethod method) {
		methods.add(method);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomain build() {
		return new SimpleDomain(this);
	}

	/**
	 * @return the operators
	 */
	protected final Set<ImmutableOperator> getOperators() {
		return operators;
	}

	/**
	 * @return the effects
	 */
	protected final Set<ImmutableMethod> getMethods() {
		return methods;
	}

}
