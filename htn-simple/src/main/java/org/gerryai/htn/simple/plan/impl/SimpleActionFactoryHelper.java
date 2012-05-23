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
import org.gerryai.htn.plan.Bindings;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.plan.ActionFactoryHelper;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleActionFactoryHelper implements ActionFactoryHelper<ImmutableOperator,
        ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>, ImmutableVariable<?>> {

	/**
	 * Service for the domain that we are working in.
	 */
	private DomainHelper<ImmutableOperator, SubstitutableMethod, ImmutableTerm<?>, ImmutableTask,
			ImmutableTaskNetwork, ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> domainHelper;
	
	/**
	 * Constructor requiring a domain helper.
	 * @param domainHelper helper to use
	 */
	public SimpleActionFactoryHelper(DomainHelper<ImmutableOperator, SubstitutableMethod,
	        ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> domainHelper) {
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
	public final Bindings getBindings(ImmutableTask task, ImmutableOperator operator) throws TaskNotActionable {

		Bindings bindings = new Bindings();
		Map<Variable, Constant> bindingsMap = new HashMap<Variable, Constant>();
		bindings.setMap(bindingsMap);
		
		List<ImmutableTerm<?>> taskArguments = task.getArguments();
		List<ImmutableVariable<?>> operatorArguments = operator.getArguments();
		
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
			bindingsMap.put(variable, constant);
		}
		
		return bindings;
	}
}
