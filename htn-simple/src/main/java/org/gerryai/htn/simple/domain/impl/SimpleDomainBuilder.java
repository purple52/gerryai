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

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.domain.DomainBuilder;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainBuilder implements DomainBuilder<Operator, Method> {

	/**
	 * Set of operators the domain uses.
	 */
	private Set<Operator> operators;
	
	/**
	 * Set of methods that the domain uses.
	 */
	private Set<Method> methods;
	
	/**
	 * Default constructor.
	 */
	protected SimpleDomainBuilder() {
		operators = new HashSet<Operator>();
		methods = new HashSet<Method>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addOperator(Operator operator) {
		operators.add(operator);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleDomainBuilder addMethod(Method method) {
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
	protected final Set<Operator> getOperators() {
		return operators;
	}

	/**
	 * @return the effects
	 */
	protected final Set<Method> getMethods() {
		return methods;
	}

}
