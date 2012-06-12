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
package org.gerryai.htn.simple.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableConstant;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.plan.ImmutableActionFactoryHelper;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleActionFactoryHelper implements ImmutableActionFactoryHelper {

	/**
	 * Service for the domain that we are working in.
	 */
	private DomainHelper<ImmutableDomain, ImmutableOperator, ImmutableMethod, ImmutableTerm<?>, ImmutableTask,
			ImmutableTaskNetwork, ImmutableConstraint<?>, ImmutableCondition, ImmutableVariable<?>> domainHelper;
	
	/**
	 * Constructor requiring a domain helper.
	 * @param domainHelper helper to use
	 */
	public SimpleActionFactoryHelper(DomainHelper<ImmutableDomain, ImmutableOperator, ImmutableMethod,
	        ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition, ImmutableVariable<?>> domainHelper) {
		this.domainHelper = domainHelper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableOperator getOperator(ImmutableTask task) throws TaskNotActionable {
		
		ImmutableOperator operator;
		
		try {
			operator = domainHelper.getOperatorByName(task.getName());
		} catch (OperatorNotFound e) {
			throw new TaskNotActionable("Could not create action for task", e);
		}
		
		return operator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Map<ImmutableVariable<?>, ImmutableConstant<?>>
	        getBindings(ImmutableTask task, ImmutableOperator operator) 
	                throws TaskNotActionable {

	    Map<ImmutableVariable<?>, ImmutableConstant<?>> bindings =
	            new HashMap<ImmutableVariable<?>, ImmutableConstant<?>>();
		
		List<ImmutableTerm<?>> taskArguments = task.getArguments();
		List<ImmutableVariable<?>> operatorArguments = operator.getArguments();
		
		if (taskArguments.size() != operatorArguments.size()) {
			throw new TaskNotActionable("Task and operator arguments size does not match");
		}
		
		for (int i = 0; i < taskArguments.size(); i++) {
		    ImmutableVariable<?> variable = operatorArguments.get(i);
			ImmutableTerm<?> taskArgument = taskArguments.get(i);
			ImmutableConstant<?> constant;
			try {
				constant = (ImmutableConstant<?>) taskArgument;
			} catch (ClassCastException e) {
				throw new TaskNotActionable("Task argument is not a constant", e);
			}
			bindings.put(variable, constant);
		}
		
		return bindings;
	}
}
