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
import org.gerryai.htn.simple.domain.ImmutableDomainHelper;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.plan.ImmutableActionFactoryHelper;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleActionFactoryHelper implements ImmutableActionFactoryHelper {

	/**
	 * Service for the domain that we are working in.
	 */
	private ImmutableDomainHelper domainHelper;
	
	/**
	 * Constructor requiring a domain helper.
	 * @param domainHelper helper to use
	 */
	public SimpleActionFactoryHelper(ImmutableDomainHelper domainHelper) {
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
	public final Map<Variable, Constant>
	        getBindings(ImmutableTask task, ImmutableOperator operator) 
	                throws TaskNotActionable {

	    Map<Variable, Constant> bindings =
	            new HashMap<Variable, Constant>();
		
		List<Term> taskArguments = task.getArguments();
		List<Variable> operatorArguments = operator.getArguments();
		
		if (taskArguments.size() != operatorArguments.size()) {
			throw new TaskNotActionable("Task and operator arguments size does not match");
		}
		
		for (int i = 0; i < taskArguments.size(); i++) {
		    Variable variable = operatorArguments.get(i);
		    Term taskArgument = taskArguments.get(i);
		    Constant constant;
			try {
				constant = (Constant) taskArgument;
			} catch (ClassCastException e) {
				throw new TaskNotActionable("Task argument is not a constant", e);
			}
			bindings.put(variable, constant);
		}
		
		return bindings;
	}
}
