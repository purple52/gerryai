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

import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.ActionFactoryHelper;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.unification.Bindings;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleActionFactory implements ActionFactory {

	private ActionFactoryHelper actionFactoryHelper;
	
	/**
	 * {@inheritDoc}
	 */
	public Action create(Task task) throws TaskNotActionable {

		// Try and get the operator
		Operator operator = actionFactoryHelper.getOperator(task);
		
		// Try and get the bindings
		Bindings bindings = actionFactoryHelper.getBindings(task, operator);
		
		// Finally, create and add this action
		Action action = new SimpleAction();
		action.setOperator(operator);
		action.setBindings(bindings);
		return action;
	}
	


}
