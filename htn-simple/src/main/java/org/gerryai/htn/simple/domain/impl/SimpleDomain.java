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
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;

/**
 * Simple representation of a domain.
 * Wraps a set of operators and a set of methods to define the domain.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleDomain implements Domain {

	/**
	 * Operators available in this domain.
	 */
	private Set<Operator> operators;
	
	/**
	 * Methods available in this domain.
	 */
	private Set<Method> methods;
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<Operator> getOperators() {
		return operators;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Method> getMethods() {
		return methods;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setMethods(Set<Method> methods) {
		this.methods = methods;
	}

}
