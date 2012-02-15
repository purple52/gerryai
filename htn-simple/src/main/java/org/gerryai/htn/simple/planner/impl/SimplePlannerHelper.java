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

import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.decomposition.UnifierNotFound;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleUnifier;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.PlanFactory;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.PlannerHelper;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.unification.Substitution;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerHelper implements PlannerHelper<SubstitutableOperator, SubstitutableMethod,
		SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
		ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition> {

	/**
	 * Factory for creating actions.
	 */
	private ActionFactory<SubstitutableOperator, SubstitutableTerm, SubstitutableTask,
			SubstitutableCondition> actionFactory;
	
	/**
	 * Factory for creating plans.
	 */
	private PlanFactory<SubstitutableOperator, SubstitutableCondition> planFactory;
	
	/**
	 * Service for decomposing tasks.
	 */
	private DecompositionService<SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
			ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> decompositionService;
	
	/**
	 * Service for finding unifiers.
	 */
	private UnificationService<SubstitutableMethod, SubstitutableTerm, SubstitutableTask,
			SubstitutableTaskNetwork, ValidatableConstraint<SubstitutableTerm,
			SubstitutableTask, SubstitutableCondition>,
			SubstitutableCondition, SimpleVariable>  unificationService;
	
	/**
	 * Constructor providing all the dependencies required to function.
	 * @param actionFactory the action factory
	 * @param planFactory the plan factory
	 * @param decompositionservice the decomposition service
	 * @param unificationService the unification service
	 */
	public SimplePlannerHelper(
			ActionFactory<SubstitutableOperator, SubstitutableTerm, SubstitutableTask,
					SubstitutableCondition> actionFactory,
			PlanFactory<SubstitutableOperator, SubstitutableCondition> planFactory,
			DecompositionService<SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
			ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> decompositionservice,
			UnificationService<SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>,
				SubstitutableCondition, SimpleVariable>  unificationService) {
		this.actionFactory = actionFactory;
		this.planFactory = planFactory;
		this.decompositionService = decompositionservice;
		this.unificationService = unificationService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean isUnsolvable(SubstitutableTaskNetwork taskNetwork) {
		//TODO: Implement
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Plan<SubstitutableOperator, SubstitutableCondition>
		findPlanForPrimitive(State state, SubstitutableTaskNetwork taskNetwork) throws PlanNotFound {
		// TODO: Confirm implementation
		// TODO: Enforce constraints
		
		Plan<SubstitutableOperator, SubstitutableCondition> plan = planFactory.create();
		
		for (SubstitutableTask task : taskNetwork.getTasks()) {
			Action<SubstitutableOperator, SubstitutableCondition> action;
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
	public final SubstitutableTaskNetwork applySubstitution(SimpleUnifier substitution,
			SubstitutableTaskNetwork taskNetwork, Task<SubstitutableTerm> task, SubstitutableMethod method) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTask getNonPrimitiveTask(SubstitutableTaskNetwork
			taskNetwork) throws NonPrimitiveTaskNotFound {
		for (SubstitutableTask task : taskNetwork.getTasks()) {
			if (!task.isPrimitive()) {
				return task;
			}
		}
		throw new NonPrimitiveTaskNotFound("Could not find a non-primitive task");
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTaskNetwork decompose(SubstitutableTaskNetwork taskNetwork,
			SubstitutableTask task, SubstitutableMethod method) throws DecompositionNotFound {
		Substitution<SubstitutableTerm, SimpleVariable> unifier;
		try {
			unifier = unificationService.findUnifier(task, method);
		} catch (UnifierNotFound e) {
			throw new DecompositionNotFound("Could not find unifier", e);
		}
		return decompositionService.decompose(unifier, taskNetwork, task, method);
	}

}
