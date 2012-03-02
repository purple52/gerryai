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

import org.gerryai.htn.simple.constraint.impl.SimpleConstraint;
import org.gerryai.htn.simple.domain.DomainBuilder;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainBuilder implements	DomainBuilder<SimpleDomain, SubstitutableOperator,
		SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
		SimpleConstraint<?>, SubstitutableCondition> {

	/**
	 * Set of operators the domain uses.
	 */
	private Set<SubstitutableOperator> operators;
	
	/**
	 * Set of methods that the domain uses.
	 */
	private Set<SubstitutableMethod> methods;
	
	/**
	 * Default constructor.
	 */
	protected SimpleDomainBuilder() {
		operators = new HashSet<SubstitutableOperator>();
		methods = new HashSet<SubstitutableMethod>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addOperator(SubstitutableOperator operator) {
		operators.add(operator);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addMethod(SubstitutableMethod method) {
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
	protected final Set<SubstitutableOperator> getOperators() {
		return operators;
	}

	/**
	 * @return the effects
	 */
	protected final Set<SubstitutableMethod> getMethods() {
		return methods;
	}

}
