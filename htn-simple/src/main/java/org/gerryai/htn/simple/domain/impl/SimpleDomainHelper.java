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
import org.gerryai.htn.simple.constraint.SubstitutableValidatableConstraint;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainHelper implements DomainHelper<SubstitutableOperator, SubstitutableMethod,
		SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
		SubstitutableValidatableConstraint, SubstitutableCondition> {

	/**
	 * Domain this helper is working on.
	 */
	private Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask,
	SubstitutableTaskNetwork, SubstitutableValidatableConstraint, SubstitutableCondition> domain;
	
	/**
	 * Constructor taking a domain to work on.
	 * @param domain the domain to work on
	 */
	public SimpleDomainHelper(Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm,
			SubstitutableTask, SubstitutableTaskNetwork,
			SubstitutableValidatableConstraint, SubstitutableCondition> domain) {
		this.domain = domain;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Domain<SubstitutableOperator, SubstitutableMethod, SubstitutableTerm, SubstitutableTask,
	SubstitutableTaskNetwork, SubstitutableValidatableConstraint, SubstitutableCondition> getDomain() {
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperator getOperatorByName(String name) throws OperatorNotFound {
		// TODO Ensure that two operators cannot have the same name and arguments match
		for (SubstitutableOperator operator : domain.getOperators()) {
			if (operator.getName().equals(name)) {
				return operator;
			}
		}
		throw new OperatorNotFound();
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<SubstitutableMethod> getMethodsByTask(SubstitutableTask task) {
		// TODO Check task arguments match
		Set<SubstitutableMethod> methods = new HashSet<SubstitutableMethod>();
		for (SubstitutableMethod method : domain.getMethods()) {
			if (method.getTask().getName().equals(task.getName())) {
				methods.add(method);
			}
		}
		return methods;
	}

}
