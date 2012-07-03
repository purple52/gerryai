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

import java.util.Map;

import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.plan.ImmutableAction;
import org.gerryai.htn.simple.plan.ImmutableActionFactory;
import org.gerryai.htn.simple.plan.ImmutableActionFactoryHelper;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Variable;

/**
 * Factory for creating actions.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleActionFactory implements ImmutableActionFactory {

	/**
	 * Helper object for doing the difficult bits.
	 */
	private ImmutableActionFactoryHelper actionFactoryHelper;
	
	/**
	 * Constructor taking all required dependencies.
	 * @param actionFactoryHelper the action factory
	 */
	public SimpleActionFactory(ImmutableActionFactoryHelper actionFactoryHelper) {
		this.actionFactoryHelper = actionFactoryHelper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableAction create(ImmutableTask task) throws TaskNotActionable {

		// Try and get the operator
		ImmutableOperator operator = actionFactoryHelper.getOperator(task);
		
		// Try and get the bindings
		Map<Variable, Constant> bindings = actionFactoryHelper.getBindings(task, operator);
		
		// Finally create this action
		return new SimpleAction.Builder()
		    .setOperator(operator)
		    .setBindings(bindings)
		    .build();
	}
	


}
