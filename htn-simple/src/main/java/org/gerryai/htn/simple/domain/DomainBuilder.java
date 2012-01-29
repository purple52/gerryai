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

import org.gerryai.htn.domain.Domain;

/**
 * Interface for a domain builder.
 * @param <O> the type of operator this domain will use
 * @param <M> the type of method this domain will use
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface DomainBuilder<O, M> {
	
	/**
	 * Add an operator to the domain.
	 * @param operator the operator to add
	 * @return the updated builder
	 */
	DomainBuilder<O, M> addOperator(O operator);
	
	/**
	 * Add a method to the domain.
	 * @param method the method to add
	 * @return the updated builder
	 */
	DomainBuilder<O, M> addMethod(M method);

	/**
	 * Build the domain.
	 * @return the domain
	 */
	Domain build();
}
