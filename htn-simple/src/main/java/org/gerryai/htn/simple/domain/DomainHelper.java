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

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Interface for a service that manages a domain.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface DomainHelper {
	
	/**
	 * Get the domain that this service is managing.
	 * @return the domain
	 */
	Domain getDomain();
	
	/**
	 * Get an operator by name.
	 * @param name the name of the operator to get
	 * @return the operator
	 * @throws OperatorNotFound if no such operator exists
	 */
	Operator getOperatorByName(String name) throws OperatorNotFound;
	
	/**
	 * Get a set of methods that match the given task.
	 * @param task the task being matched
	 * @return a set of matching methods
	 */
	Set<Method> getMethodsByTask(Task task);
}
