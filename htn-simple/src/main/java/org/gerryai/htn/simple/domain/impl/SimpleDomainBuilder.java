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

import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.domain.DomainBuilder;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainBuilder implements	DomainBuilder<SimpleDomain, SimpleOperator,
		SimpleMethod, SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
		ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition> {

	/**
	 * Set of operators the domain uses.
	 */
	private Set<SimpleOperator> operators;
	
	/**
	 * Set of methods that the domain uses.
	 */
	private Set<SimpleMethod> methods;
	
	/**
	 * Default constructor.
	 */
	protected SimpleDomainBuilder() {
		operators = new HashSet<SimpleOperator>();
		methods = new HashSet<SimpleMethod>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addOperator(SimpleOperator operator) {
		operators.add(operator);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addMethod(SimpleMethod method) {
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
	protected final Set<SimpleOperator> getOperators() {
		return operators;
	}

	/**
	 * @return the effects
	 */
	protected final Set<SimpleMethod> getMethods() {
		return methods;
	}

}
