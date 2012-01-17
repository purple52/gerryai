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

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.tasknetwork.Task;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainHelper implements DomainHelper {

	/**
	 * Domain this helper is working on.
	 */
	private Domain domain;
	
	/**
	 * Constructor taking a domain to work on.
	 * @param domain the domain to work on
	 */
	public SimpleDomainHelper(Domain domain) {
		this.domain = domain;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Domain getDomain() {
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Operator getOperatorByName(String name) throws OperatorNotFound {
		// TODO Ensure that two operators cannot have the same name and arguments match
		for (Operator operator : domain.getOperators()) {
			if (operator.getName().equals(name)) {
				return operator;
			}
		}
		throw new OperatorNotFound();
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Method> getMethodsByTask(Task task) {
		// TODO Check task arguments match
		Set<Method> methods = new HashSet<Method>();
		for (Method method : domain.getMethods()) {
			if (method.getTask().getName().equals(task.getName())) {
				methods.add(method);
			}
		}
		return methods;
	}

}
