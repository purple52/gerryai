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

import java.util.List;
import java.util.Map;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.decomposition.UnifierNotFound;
import org.gerryai.htn.simple.domain.ImmutableDomainHelper;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.PlanBuilder;
import org.gerryai.htn.simple.plan.PlanBuilderFactory;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.ImmutablePlannerHelper;
import org.gerryai.htn.simple.planner.sort.SortService;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.problem.ImmutableStateService;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Planner helper.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlannerHelper implements ImmutablePlannerHelper {
    
    /**
     * Domain helper.
     */
    private ImmutableDomainHelper domainHelper;
    
	/**
	 * Factory for creating actions.
	 */
	private ActionFactory actionFactory;
	
	/**
	 * Factory for creating plans.
	 */
	private PlanBuilderFactory planBuilderFactory;
	
	/**
	 * Service for decomposing tasks.
	 */
	private DecompositionService decompositionService;
	
	/**
	 * Service for finding unifiers.
	 */
	private UnificationService unificationService;

	/**
	 * Service for sorting tasks by precedence constraints.
	 */
	private SortService sortService;
	
	/**
	 * Service for handling state checks and updates.
	 */
	private ImmutableStateService stateService;
	
	/**
	 * Constructor providing all the dependencies required to function.
	 * @param actionFactory the action factory
	 * @param planBuilderFactory the plan factory
	 * @param decompositionservice the decomposition service
	 * @param unificationService the unification service
	 * @param sortService the sorting service
	 * @param stateService the state service
	 * @param domainHelper the helper for manipulating domain objects
	 */
	public SimplePlannerHelper(
	        ActionFactory actionFactory,
	        PlanBuilderFactory planBuilderFactory,
			DecompositionService decompositionservice,
			UnificationService unificationService,
			SortService sortService,
			ImmutableStateService stateService,
			ImmutableDomainHelper domainHelper) {
		this.actionFactory = actionFactory;
		this.planBuilderFactory = planBuilderFactory;
		this.decompositionService = decompositionservice;
		this.unificationService = unificationService;
		this.sortService = sortService;
		this.stateService = stateService;
		this.domainHelper = domainHelper;
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
	public final Plan findPlanForPrimitive(ImmutableState state,
	        TaskNetwork taskNetwork) throws PlanNotFound {
		// TODO: Confirm implementation
		// TODO: Enforce constraints from task network completely
	    List<Task> sortedTasks = sortService.sortByConstaints(
	            taskNetwork.getTasks(), taskNetwork.getPrecedenceConstraints());

		PlanBuilder planBuilder = planBuilderFactory.createBuilder();
		
		for (Task task : sortedTasks) {
			Action action;
			try {
				action = actionFactory.create(task);
			} catch (TaskNotActionable e) {
				throw new PlanNotFound("Could not turn task into action", e);
			}
			for (Condition condition : action.getOperator().getPreconditions()) {
			    Condition groundCondition = domainHelper.getGroundedCondition(condition, action.getBindings());
			    if (!stateService.ask(state, groundCondition)) {
			        throw new PlanNotFound("Preconditions of operator not satisfied");
			    } else {
			        for (Effect effect : action.getOperator().getEffects()) {
			            Effect groundEffect = domainHelper.getGroundedEffect(effect, action.getBindings());
			            state = stateService.tell(state, groundEffect);
			        }
			    }
			}
			planBuilder = planBuilder.addAction(action);
		}
		
		return planBuilder.build();
	}

	/**
	 * {@inheritDoc}.
	 *
	public final TaskNetwork applySubstitution(Map<Term, Term> substitution,
			ImmutableTaskNetwork taskNetwork, Task task, ImmutableMethod method) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public final Task getNonPrimitiveTask(TaskNetwork taskNetwork) throws NonPrimitiveTaskNotFound {
		for (Task task : taskNetwork.getTasks()) {
			if (!task.isPrimitive()) {
				return task;
			}
		}
		throw new NonPrimitiveTaskNotFound("Could not find a non-primitive task");
	}

	@Override
	public final TaskNetwork decompose(TaskNetwork taskNetwork,
			Task task, Method method) throws DecompositionNotFound, InvalidConstraint {
		Map<Term, Term> substitution;
		try {
		    substitution = unificationService.findUnifier(task, method);
		} catch (UnifierNotFound e) {
			throw new DecompositionNotFound("Could not find unifier", e);
		}
		return decompositionService.decompose(substitution, taskNetwork, task, method);
	}

}
