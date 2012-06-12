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

import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.decomposition.UnifierNotFound;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleUnifier;
import org.gerryai.htn.simple.plan.ImmutableAction;
import org.gerryai.htn.simple.plan.ImmutableActionFactory;
import org.gerryai.htn.simple.plan.ImmutablePlan;
import org.gerryai.htn.simple.plan.ImmutablePlanBuilder;
import org.gerryai.htn.simple.plan.ImmutablePlanBuilderFactory;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.ImmutablePlannerHelper;
import org.gerryai.htn.simple.planner.sort.SortService;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Planner helper.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlannerHelper implements ImmutablePlannerHelper {
    
	/**
	 * Factory for creating actions.
	 */
	private ImmutableActionFactory actionFactory;
	
	/**
	 * Factory for creating plans.
	 */
	private ImmutablePlanBuilderFactory planBuilderFactory;
	
	/**
	 * Service for decomposing tasks.
	 */
	private DecompositionService<ImmutableMethod, ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
	        ImmutableConstraint<?>, ImmutableSubstitution> decompositionService;
	
	/**
	 * Service for finding unifiers.
	 */
	private UnificationService<ImmutableMethod, ImmutableTerm<?>, ImmutableTask,
			ImmutableTaskNetwork, ImmutableConstraint<?>,
			ImmutableCondition>  unificationService;

	/**
	 * Service for sorting tasks by precedence constraints.
	 */
	private SortService<ImmutableTerm<?>, ImmutableTask, ImmutableConstraint<?>> sortService;
	
	/**
	 * Constructor providing all the dependencies required to function.
	 * @param actionFactory the action factory
	 * @param planBuilderFactory the plan factory
	 * @param decompositionservice the decomposition service
	 * @param unificationService the unification service
	 * @param sortService the sorting service
	 */
	public SimplePlannerHelper(
	        ImmutableActionFactory actionFactory,
	        ImmutablePlanBuilderFactory planBuilderFactory,
			DecompositionService<ImmutableMethod, ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableSubstitution> decompositionservice,
			UnificationService<ImmutableMethod, ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition>  unificationService,
			SortService<ImmutableTerm<?>, ImmutableTask, ImmutableConstraint<?>> sortService) {
		this.actionFactory = actionFactory;
		this.planBuilderFactory = planBuilderFactory;
		this.decompositionService = decompositionservice;
		this.unificationService = unificationService;
		this.sortService = sortService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean isUnsolvable(ImmutableTaskNetwork taskNetwork) {
		//TODO: Implement
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutablePlan findPlanForPrimitive(State state, ImmutableTaskNetwork taskNetwork) throws PlanNotFound {
		// TODO: Confirm implementation
		// TODO: Enforce constraints completely
	    List<ImmutableTask> sortedTasks = sortService.sortByConstaints(
	            taskNetwork.getTasks(), taskNetwork.getConstraints());

		ImmutablePlanBuilder planBuilder = planBuilderFactory.createBuilder();
		
		for (ImmutableTask task : sortedTasks) {
			ImmutableAction action;
			try {
				action = actionFactory.create(task);
			} catch (TaskNotActionable e) {
				throw new PlanNotFound("Could not turn task into action", e);
			}
			planBuilder = planBuilder.addAction(action);
		}
		
		return planBuilder.build();
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableTaskNetwork applySubstitution(SimpleUnifier substitution,
			ImmutableTaskNetwork taskNetwork, Task<ImmutableTerm<?>> task, ImmutableMethod method) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableTask getNonPrimitiveTask(ImmutableTaskNetwork
			taskNetwork) throws NonPrimitiveTaskNotFound {
		for (ImmutableTask task : taskNetwork.getTasks()) {
			if (!task.isPrimitive()) {
				return task;
			}
		}
		throw new NonPrimitiveTaskNotFound("Could not find a non-primitive task");
	}

	/**
	 * {@inheritDoc} 
	 */
	public final ImmutableTaskNetwork decompose(ImmutableTaskNetwork taskNetwork,
			ImmutableTask task, ImmutableMethod method) throws DecompositionNotFound, InvalidConstraint {
		ImmutableSubstitution substitution;
		try {
		    substitution = unificationService.findUnifier(task, method);
		} catch (UnifierNotFound e) {
			throw new DecompositionNotFound("Could not find unifier", e);
		}
		return decompositionService.decompose(substitution, taskNetwork, task, method);
	}

}
