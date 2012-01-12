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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.tasknetwork.Domain;
import org.gerryai.htn.tasknetwork.Plan;
import org.gerryai.htn.tasknetwork.PlanImpl;
import org.gerryai.htn.tasknetwork.Problem;
import org.gerryai.htn.tasknetwork.State;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.htn.tasknetwork.TaskNetworkImpl;

/**
 * Implementation of a planner.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class PlannerImpl implements Planner {
	
	private PlannerHelper plannerHelper;

	/**
	 * {@inheritDoc}
	 */
	public final Plan solve(Problem problem) throws PlanNotFound {	
		return findPlan(problem.getState(), problem.getTaskNetwork(), problem.getDomain());
	}
	
	private Plan findPlan(State state, TaskNetwork taskNetwork, Domain domain) throws PlanNotFound {
		
		if (plannerHelper.isUnsolvable(taskNetwork)) {
			// 1. No solution
			throw new PlanNotFound();
		} else if (plannerHelper.isPrimitive(taskNetwork)) {
			// 2. U is primitive
			return plannerHelper.findPlanForPrimitive(state, taskNetwork, domain);
		} else {
			// 3. U is non-primitive
			return plannerHelper.findPlanForNonPrimitive(state, taskNetwork, domain);
		}
	}
	
	/**
	 * Looks for plans in the network provided.
	 * @param problem the initial problem being solved
	 * @param taskNetwork the current task network being searched
	 * @param plan the plan currently being evaluated
	 * @return the plans found
	 */
	private List<Plan> findPlans(Problem problem, TaskNetwork taskNetwork, Plan plan) {
		
		List<Plan> plans = new ArrayList<Plan>();
		
		Set<Task> tasks = taskNetwork.getTasks();
		
		if (tasks.size() == 0) {
			if (tasks.equals(problem.getTaskNetwork().getTasks())) {
				// We've just solved the original network, so return our plan.
				plans.add(plan);
			}
		} else {
			Iterator<Task> taskIterator = tasks.iterator();
			while (taskIterator.hasNext()) {
				Set<Task> nextTaskSet = new HashSet<Task>();
				nextTaskSet.add(taskIterator.next());
				TaskNetwork nextTaskNetwork = new TaskNetworkImpl();
				nextTaskNetwork.setConstraints(taskNetwork.getConstraints());
				nextTaskNetwork.setTasks(nextTaskSet);
				plans.addAll(findPlans(problem, nextTaskNetwork, plan));
			}
		}
		
		return plans;
	}

}
