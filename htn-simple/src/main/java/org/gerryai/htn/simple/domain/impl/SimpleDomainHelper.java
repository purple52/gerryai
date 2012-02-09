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
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.htn.simple.logic.impl.SimpleTerm;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainHelper implements DomainHelper<SimpleOperator, SimpleMethod,
		SimpleTerm, SimpleTask, SimpleTaskNetwork,
		ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition> {

	/**
	 * Domain this helper is working on.
	 */
	private Domain<SimpleOperator, SimpleMethod, SimpleTerm, SimpleTask,
			SimpleTaskNetwork, ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition> domain;
	
	/**
	 * Constructor taking a domain to work on.
	 * @param domain the domain to work on
	 */
	public SimpleDomainHelper(Domain<SimpleOperator, SimpleMethod, SimpleTerm,
			SimpleTask, SimpleTaskNetwork, ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition> domain) {
		this.domain = domain;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Domain<SimpleOperator, SimpleMethod, SimpleTerm, SimpleTask,
			SimpleTaskNetwork, ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition> getDomain() {
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperator getOperatorByName(String name) throws OperatorNotFound {
		// TODO Ensure that two operators cannot have the same name and arguments match
		for (SimpleOperator operator : domain.getOperators()) {
			if (operator.getName().equals(name)) {
				return operator;
			}
		}
		throw new OperatorNotFound();
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<SimpleMethod> getMethodsByTask(SimpleTask task) {
		// TODO Check task arguments match
		Set<SimpleMethod> methods = new HashSet<SimpleMethod>();
		for (SimpleMethod method : domain.getMethods()) {
			if (method.getTask().getName().equals(task.getName())) {
				methods.add(method);
			}
		}
		return methods;
	}

}
