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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableDomainHelper;
import org.gerryai.htn.simple.domain.ImmutableEffect;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableConstant;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainHelper implements ImmutableDomainHelper {

	/**
	 * Domain this helper is working on.
	 */
	private ImmutableDomain domain;
	
	/**
	 * Constructor taking a domain to work on.
	 * @param domain the domain to work on
	 */
	public SimpleDomainHelper(ImmutableDomain domain) {
		this.domain = domain;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableDomain getDomain() {
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableOperator getOperatorByName(String name) throws OperatorNotFound {
		// TODO Ensure that two operators cannot have the same name and arguments match
		for (ImmutableOperator operator : domain.getOperators()) {
			if (operator.getName().equals(name)) {
				return operator;
			}
		}
		throw new OperatorNotFound();
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableMethod> getMethodsByTask(ImmutableTask task) {
		// TODO Check task arguments match
		Set<ImmutableMethod> methods = new HashSet<ImmutableMethod>();
		for (ImmutableMethod method : domain.getMethods()) {
			if (method.getTask().getName().equals(task.getName())) {
				methods.add(method);
			}
		}
		return methods;
	}

    /**
     * {@inheritDoc}
     */
    public final ImmutableEffect getGroundedEffect(ImmutableEffect effect,
            Map<ImmutableVariable<?>, ImmutableConstant<?>> bindings) {
        
        Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution =
                new HashMap<ImmutableTerm<?>, ImmutableTerm<?>>(bindings);
        
        return effect.createCopyBuilder()
                .apply(substitution)
                .build();
    }
    
    /**
     * {@inheritDoc}
     */
    public final ImmutableCondition getGroundedCondition(ImmutableCondition condition,
            Map<ImmutableVariable<?>, ImmutableConstant<?>> bindings) {
        
        Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution =
                new HashMap<ImmutableTerm<?>, ImmutableTerm<?>>(bindings);
        
        return condition.createCopyBuilder()
                .apply(substitution)
                .build();
    }
}
