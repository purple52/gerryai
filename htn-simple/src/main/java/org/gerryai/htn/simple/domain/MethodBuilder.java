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

import org.gerryai.htn.domain.Method;

/**
 * Interface for a class that can build methods.
 * @param <T> class of task the builder can handle
 * @param <N> class of network the builder can handle
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface MethodBuilder<T, N> {

	/**
	 * Set the name of the operator.
	 * @param name the name
	 * @return the updated builder
	 */
	MethodBuilder<T, N> setName(String name);
	
	/**
	 * Add an argument to the operator.
	 * @param argument the variable to add
	 * @return the updated builder
	 */
	MethodBuilder<T, N> setTask(T task);

	/**
	 * Add an argument to the operator.
	 * @param condition the condition to add
	 * @return the updated builder
	 */
	MethodBuilder<T, N> setTaskNetwork(N taskNetwork);

	/**
	 * Build the method.
	 * @return the method
	 */
	Method build();
}
