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
import org.gerryai.htn.simple.domain.impl.SimpleMethod;
import org.gerryai.htn.simple.domain.impl.SimpleOperator;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.htn.simple.logic.impl.SimpleTerm;
import org.gerryai.htn.simple.logic.impl.SimpleUnifier;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.PlanFactory;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.PlannerHelper;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.unification.Substitution;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerHelper implements PlannerHelper<SimpleOperator, SimpleMethod,
		SimpleTerm, SimpleTask, SimpleTaskNetwork,
		ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition> {

	/**
	 * Factory for creating actions.
	 */
	private ActionFactory<SimpleOperator, SimpleTerm, SimpleTask, SimpleCondition> actionFactory;
	
	/**
	 * Factory for creating plans.
	 */
	private PlanFactory<SimpleOperator, SimpleCondition> planFactory;
	
	/**
	 * Service for decomposing tasks.
	 */
	private DecompositionService<SimpleMethod, SimpleTerm, SimpleTask, SimpleTaskNetwork,
			ValidatableConstraint<SimpleTerm, SimpleTask>> decompositionService;
	
	/**
	 * Service for finding unifiers.
	 */
	private UnificationService<SimpleMethod, SimpleTerm, SimpleTask,
			SimpleTaskNetwork, ValidatableConstraint<SimpleTerm, SimpleTask>,
			SimpleCondition, SimpleVariable>  unificationService;
	
	/**
	 * Constructor providing all the dependencies required to function.
	 * @param actionFactory the action factory
	 * @param planFactory the plan factory
	 * @param decompositionservice the decomposition service
	 * @param unificationService the unification service
	 */
	public SimplePlannerHelper(
			ActionFactory<SimpleOperator, SimpleTerm, SimpleTask, SimpleCondition> actionFactory,
			PlanFactory<SimpleOperator, SimpleCondition> planFactory,
			DecompositionService<SimpleMethod, SimpleTerm, SimpleTask, SimpleTaskNetwork,
				ValidatableConstraint<SimpleTerm, SimpleTask>> decompositionservice,
			UnificationService<SimpleMethod, SimpleTerm, SimpleTask, SimpleTaskNetwork,
				ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition, SimpleVariable>  unificationService) {
		this.actionFactory = actionFactory;
		this.planFactory = planFactory;
		this.decompositionService = decompositionservice;
		this.unificationService = unificationService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean isUnsolvable(SimpleTaskNetwork taskNetwork) {
		//TODO: Implement
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Plan<SimpleOperator, SimpleCondition>
		findPlanForPrimitive(State state, SimpleTaskNetwork taskNetwork) throws PlanNotFound {
		// TODO: Confirm implementation
		// TODO: Enforce constraints
		
		Plan<SimpleOperator, SimpleCondition> plan = planFactory.create();
		
		for (SimpleTask task : taskNetwork.getTasks()) {
			Action<SimpleOperator, SimpleCondition> action;
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
	public final SimpleTaskNetwork applySubstitution(SimpleUnifier substitution,
			SimpleTaskNetwork taskNetwork, Task<SimpleTerm> task, SimpleMethod method) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTask getNonPrimitiveTask(SimpleTaskNetwork taskNetwork) throws NonPrimitiveTaskNotFound {
		for (SimpleTask task : taskNetwork.getTasks()) {
			if (!task.isPrimitive()) {
				return task;
			}
		}
		throw new NonPrimitiveTaskNotFound("Could not find a non-primitive task");
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetwork decompose(SimpleTaskNetwork taskNetwork,
			SimpleTask task, SimpleMethod method) throws DecompositionNotFound {
		Substitution<SimpleTerm, SimpleVariable> unifier;
		try {
			unifier = unificationService.findUnifier(task, method);
		} catch (UnifierNotFound e) {
			throw new DecompositionNotFound("Could not find unifier", e);
		}
		return decompositionService.decompose(unifier, taskNetwork, task, method);
	}

}
