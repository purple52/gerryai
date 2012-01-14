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
package org.gerryai.htn.domain;

import java.util.Set;

import org.gerryai.htn.tasknetwork.Task;

/**
 * Interface that a domain must implement.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Domain {

	/**
	 * Get the full set of available operators for this domain.
	 * @return the operators
	 */
	Set<Operator> getOperators();
	
	/**
	 * Set the available operators for this domain.
	 * @param operators the set of operators
	 */
	void setOperators(Set<Operator> operators);
	
	/**
	 * Get the set of methods available for this domain.
	 * @return the methods
	 */
	Set<Method> getMethods();
	
	/**
	 * Set the available methods for this domain.
	 * @param methods the set of methods
	 */
	void setMethods(Set<Method> methods);
	
	/**
	 * Get an operator by name.
	 * @param name the name of the operator to get
	 */
	Operator getOperatorByName(String name) throws OperatorNotFound;
	
	Set<Method> getMethodsByTask(Task task);
}
