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

import org.gerryai.htn.decomposition.DecompositionService;
import org.gerryai.htn.decomposition.UnificationService;
import org.gerryai.htn.decomposition.UnifierNotFound;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.PlanFactory;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.PlannerHelper;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.unification.Unifier;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerHelper implements PlannerHelper {

	/**
	 * Factory for creating actions.
	 */
	private ActionFactory actionFactory;
	
	/**
	 * Factory for creating plans.
	 */
	private PlanFactory planFactory;
	
	/**
	 * Service for decomposing tasks.
	 */
	private DecompositionService decompositionService;
	
	/**
	 * Service for finding unifiers.
	 */
	private UnificationService unificationService;
	
	/**
	 * Constructor providing all the dependencies required to function.
	 * @param actionFactory the action factory
	 * @param planFactory the plan factory
	 * @param decompositionservice the decomposition service
	 * @param unificationService the unification service
	 */
	public SimplePlannerHelper(ActionFactory actionFactory, PlanFactory planFactory,
			DecompositionService decompositionservice, UnificationService unificationService) {
		this.actionFactory = actionFactory;
		this.planFactory = planFactory;
		this.decompositionService = decompositionservice;
		this.unificationService = unificationService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean isUnsolvable(TaskNetwork taskNetwork) {
		//TODO: Implement
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Plan findPlanForPrimitive(State state, TaskNetwork taskNetwork) throws PlanNotFound {
		// TODO: Confirm implementation
		// TODO: Enforce constraints
		
		Plan plan = planFactory.create();
		
		Set<Task> tasks = taskNetwork.getTasks();
		Iterator<Task> taskIterator = tasks.iterator();
		while (taskIterator.hasNext()) {
			Task task = taskIterator.next();
			Action action;
			try {
				action = actionFactory.create(task);
			} catch (TaskNotActionable e) {
				throw new PlanNotFound("Could not turn task into action", e);
			}
			plan.getActions().add(action);
		}
		
		return plan;
	}

	/**
	 * {@inheritDoc}
	 */
	public final TaskNetwork applySubstitution(Unifier substitution,
			TaskNetwork taskNetwork, Task task, Method method) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Task getNonPrimitiveTask(TaskNetwork taskNetwork) throws PrimitiveTaskNotFound {
		for (Task task : taskNetwork.getTasks()) {
			if (!task.isPrimitive()) {
				return task;
			}
		}
		throw new PrimitiveTaskNotFound("Could not find a non-primitive task");
	}

	/**
	 * {@inheritDoc}
	 */
	public final TaskNetwork decompose(TaskNetwork taskNetwork, Task task, Method method) throws DecompositionNotFound {
		Unifier unifier;
		try {
			unifier = unificationService.findUnifier(task, method);
		} catch (UnifierNotFound e) {
			throw new DecompositionNotFound("Could not find unifier", e);
		}
		return decompositionService.decompose(unifier, taskNetwork, task, method);
	}

}
