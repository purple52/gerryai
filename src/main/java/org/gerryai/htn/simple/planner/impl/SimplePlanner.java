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
package org.gerryai.htn.simple.planner.impl;

import java.util.Iterator;
import java.util.Set;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.plan.PlanFactory;
import org.gerryai.htn.simple.planner.PlannerHelper;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Implementation of a planner.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlanner implements Planner {
	
	private PlannerHelper plannerHelper;

	private PlanFactory planFactory;
	
	public Plan findPlan(State state, TaskNetwork taskNetwork, Domain domain) throws PlanNotFound {
		
		if (plannerHelper.isUnsolvable(taskNetwork)) {
			// 1. No solution
			throw new PlanNotFound();
		} else if (taskNetwork.isPrimitive()) {
			// 2. Task network is primitive
			return plannerHelper.findPlanForPrimitive(state, taskNetwork, domain);
		} else {
			// 3. Task network is non-primitive
			// TODO: Implement
			
			Plan plan = planFactory.create();
			
			for (Task task : taskNetwork.getTasks()) {
				if (!task.isPrimitive() ) {

					Set<Method> methods = domain.getMethodsByTask(task);
					Iterator<Method> methodIterator = methods.iterator();
					while (methodIterator.hasNext()) {
						Method method = methodIterator.next();
						TaskNetwork decomposedNetwork = new SimpleTaskNetwork();
						return findPlan(state, decomposedNetwork, domain);

					}
				}
			}
			
			return plan;
		}
	}

}
