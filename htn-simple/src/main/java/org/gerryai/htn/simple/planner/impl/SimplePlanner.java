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

import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.PlannerHelper;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;

/**
 * Implementation of a planner.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlanner implements
		Planner<ImmutableOperator, ImmutableMethod, ImmutableTerm<?>,
		ImmutableTask, ImmutableTaskNetwork,
		ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> {
	
	/**
	 * Manager the domain being worked in.
	 */
	private DomainHelper<ImmutableDomain, ImmutableOperator, ImmutableMethod, ImmutableTerm<?>,
			ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> domainHelper;
	
	/**
	 * Helper for off-loading some of the logic.
	 */
	private PlannerHelper<ImmutableOperator, ImmutableMethod, ImmutableTerm<?>,
			ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> plannerHelper;
	
	/**
	 * Constructor taking the domain manager and planner helper to use.
	 * @param domainHelper the domain manager
	 * @param plannerHelper the planner helper
	 */
	public SimplePlanner(
			DomainHelper<ImmutableDomain, ImmutableOperator, ImmutableMethod, ImmutableTerm<?>,
					ImmutableTask, ImmutableTaskNetwork,
					ImmutableConstraint<?>,
					ImmutableCondition<?>, ImmutableVariable<?>> domainHelper,
			PlannerHelper<ImmutableOperator, ImmutableMethod, ImmutableTerm<?>,
					ImmutableTask, ImmutableTaskNetwork,
					ImmutableConstraint<?>,
					ImmutableCondition<?>, ImmutableVariable<?>> plannerHelper) {
		this.domainHelper = domainHelper;
		this.plannerHelper = plannerHelper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Plan<ImmutableOperator, ImmutableCondition<?>, ImmutableVariable<?>>
			findPlan(State state, ImmutableTaskNetwork taskNetwork) throws PlanNotFound {
		
		if (plannerHelper.isUnsolvable(taskNetwork)) {
			// 1. No solution
			// TODO: Handle empty case
			throw new PlanNotFound();
		} else {

			try {
				// Try and find a non-primitive task to deal with
				ImmutableTask task = plannerHelper.getNonPrimitiveTask(taskNetwork);

				// 3. Task network is non-primitive
				// TODO: Confirm implementation
				// TODO: Handle state changes (and correct backtracking?)			
				for (ImmutableMethod method : domainHelper.getMethodsByTask(task)) {
					try {
						ImmutableTaskNetwork decomposedNetwork = plannerHelper.decompose(taskNetwork, task, method);
						// Try recursing to further process the decomposed network
						return findPlan(state, decomposedNetwork);
					} catch (InvalidConstraint e) {
					    //TODO: Is this necessary and the correct way to handle this?
					    continue;
					} catch (DecompositionNotFound e) {
						// This method was no good, so continue and try the next one
						continue;
					} catch (PlanNotFound e) {
						// This method was no good, so continue and try the next one
						continue;
					}
				}
			} catch (NonPrimitiveTaskNotFound e) {
				// 2. Task network is primitive
				return plannerHelper.findPlanForPrimitive(state, taskNetwork);
			}
			
			throw new PlanNotFound("No methods found to decompose this task");
		}
	}

}
