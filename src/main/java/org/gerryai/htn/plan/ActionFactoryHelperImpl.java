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
package org.gerryai.htn.plan;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.gerryai.logic.unifier.ConstantSubstitution;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class ActionFactoryHelperImpl implements ActionFactoryHelper {

	private Domain domain;
	
	public Operator getOperator(Task task) throws TaskNotActionable {
		
		Operator operator;
		
		try {
			operator = domain.getOperatorByName(task.getName());
		} catch (OperatorNotFound e) {
			throw new TaskNotActionable("Could not create action for task", e);
		}
		
		return operator;
	}
	
	public ConstantSubstitution getBindings(Task task) throws TaskNotActionable {

		ConstantSubstitution bindings = new ConstantSubstitution();
		Map<Variable, Constant> bindingsMap = new HashMap<Variable, Constant>();
		bindings.setMap(bindingsMap);
		List<Term> arguments = task.getArguments();
		Iterator<Term> argumentIterator = arguments.iterator();
		while (argumentIterator.hasNext()) {
			Term argument = argumentIterator.next();
			//TODO: Try and create the bindings
		}
		
		return bindings;
	}
}
