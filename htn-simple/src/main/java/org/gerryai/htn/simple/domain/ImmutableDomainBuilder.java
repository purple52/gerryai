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
package org.gerryai.htn.simple.domain;

import java.util.Set;

import org.gerryai.htn.domain.Operator;

/**
 * Builder interface for an immutable domain.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableDomainBuilder {
	
	/**
	 * Add an operator to the domain.
	 * @param operator the operator to add
	 * @return the updated builder
	 */
	ImmutableDomainBuilder addOperator(Operator operator);
	
	/**
	 * Add a method to the domain.
	 * @param method the method to add
	 * @return the updated builder
	 */
	ImmutableDomainBuilder addMethod(ImmutableMethod method);

	/**
	 * Get the set of operators added to this builder.
     * @return the operators
     */
    Set<Operator> getOperators();

    /**
     * Get the methods added to this builder.
     * @return the methods
     */
    Set<ImmutableMethod> getMethods();
	
	/**
	 * Build the domain.
	 * @return the domain
	 */
	ImmutableDomain build();
}
