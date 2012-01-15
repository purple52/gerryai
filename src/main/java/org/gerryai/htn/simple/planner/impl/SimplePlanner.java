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

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.PlannerHelper;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Implementation of a planner.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlanner implements Planner {
	
	/**
	 * Helper for off-loading some of the logic.
	 */
	private PlannerHelper plannerHelper;
	
	/**
	 * {@inheritDoc}
	 */
	public final Plan findPlan(State state, TaskNetwork taskNetwork, Domain domain) throws PlanNotFound {
		
		if (plannerHelper.isUnsolvable(taskNetwork)) {
			// 1. No solution
			// TODO: Handle empty case
			throw new PlanNotFound();
		} else {

			
			Task task;
			
			try {
				// Try and find a non-primitive task to deal with
				task = plannerHelper.getNonPrimitiveTask(taskNetwork);
			} catch (PrimitiveTaskNotFound e) {
				// 2. Task network is primitive
				return plannerHelper.findPlanForPrimitive(state, taskNetwork, domain);
			}
			
			// 3. Task network is non-primitive
			// TODO: Implement
			// TODO: Handle state changes (and correct backtracking?)			
			for (Method method : domain.getMethodsByTask(task)) {
				try {
					TaskNetwork decomposedNetwork = plannerHelper.decompose(taskNetwork, task, method);
					// Try recursing to further process the decomposed network
					return findPlan(state, decomposedNetwork, domain);
				} catch (DecompositionNotFound e) {
					// This method was no good, so continue and try the next one
					continue;
				} catch (PlanNotFound e) {
					// This method was no good, so continue and try the next one
					continue;
				}
			}
			
			throw new PlanNotFound();
		}
	}

}
