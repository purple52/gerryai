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
package org.gerryai.htn.planner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.ActionFactory;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.plan.PlanImpl;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class PlannerHelperImpl implements PlannerHelper {

	private ActionFactory actionFactory;
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isUnsolvable(TaskNetwork taskNetwork) {
		//TODO: Implement
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public Plan findPlanForPrimitive(State state, TaskNetwork taskNetwork, Domain domain) throws PlanNotFound {
		// TODO: Confirm implementation
		// TODO: Enforce constraints
		
		Plan plan = new PlanImpl();
		List<Action> actions = new ArrayList<Action>();
		plan.setActions(actions);
		
		Set<Task> tasks = taskNetwork.getTasks();
		Iterator<Task> taskIterator = tasks.iterator();
		while (taskIterator.hasNext()) {
			Task task = taskIterator.next();
			Action action;
			try {
				action = actionFactory.create(task);
			} catch (TaskNotActionable e) {
				// If we couldn't find an operator for this task, something must
				// have gone a bit wrong - but definitely no plan exists!
				throw new PlanNotFound();
			}
			actions.add(action);
		}
		
		return plan;
	}

	/**
	 * {@inheritDoc}
	 */
	public Plan findPlanForNonPrimitive(State state, TaskNetwork taskNetwork, Domain domain) throws PlanNotFound {
		// TODO: Implement
		throw new PlanNotFound();
	}

}
