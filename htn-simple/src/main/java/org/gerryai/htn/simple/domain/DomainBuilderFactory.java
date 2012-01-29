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

/**
 * Interface for a factory that creates domain and operator builders.
 * @param <O> the class of operator the builder will handle
 * @param <M> the class of method the builder will handle
 * @param <C> the class of constraint the builder will handle
 * @param <E> the class of effect the builder will handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface DomainBuilderFactory<O, M, C, E> {

	/**
	 * Create a domain builder of the required type.
	 * @return the domain builder
	 */
	DomainBuilder<O, M> createDomainBuilder();
	
	/**
	 * Create an operator builder of the required type.
	 * @return the operator builder
	 */
	OperatorBuilder<C, E> createOperatorBuilder();
}
