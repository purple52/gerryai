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

import org.gerryai.htn.tasknetwork.Plan;
import org.gerryai.htn.tasknetwork.PlanImpl;
import org.gerryai.htn.tasknetwork.Problem;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.htn.tasknetwork.TaskNetworkImpl;

/**
 * Implementation of a planner.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class PlannerImpl implements Planner {

	/**
	 * {@inheritDoc}
	 */
	public final List<Plan> solve(Problem problem) {
		
		Plan plan = new PlanImpl();
		return findPlans(problem, problem.getTaskNetwork(), plan);
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
