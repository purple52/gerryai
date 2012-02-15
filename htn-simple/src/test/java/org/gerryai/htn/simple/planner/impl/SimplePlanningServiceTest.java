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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.domain.impl.SimpleMethod;
import org.gerryai.htn.simple.domain.impl.SimpleOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.planner.PlannerFactory;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlanningServiceTest {
 
	/**
	 * Test that the service returns whatever plan the planner finds.
	 * @throws PlanNotFound only if the test fails
	 */
	@Test
	public void testSolvePlanFound() throws PlanNotFound {
		
		@SuppressWarnings("unchecked")
		Plan<SimpleOperator, SubstitutableCondition> mockPlan = mock(Plan.class);
		Problem<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition>
				mockProblem = createMockProblem();
		@SuppressWarnings("unchecked")
		Planner<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition>
				mockPlanner = mock(Planner.class);
		when(mockPlanner.findPlan(mockProblem.getState(), mockProblem.getTaskNetwork())).thenReturn(mockPlan);
		@SuppressWarnings("unchecked")
		PlannerFactory<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask,
				SubstitutableTaskNetwork, ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>,
				SubstitutableCondition> mockPlannerFactory = mock(PlannerFactory.class);
		
		when(mockPlannerFactory.create(any(Domain.class))).thenReturn(mockPlanner);
		
		// Create the service to be tested
		SimplePlanningService plannerService = new SimplePlanningService(mockPlannerFactory);
		
		// Try and solve the problem
		Plan<SimpleOperator, SubstitutableCondition> plan = plannerService.solve(mockProblem);
		
		// Verify that the correct plan was returned
		verify(mockPlanner).findPlan(mockProblem.getState(), mockProblem.getTaskNetwork());
		assertEquals(mockPlan, plan);
	}

	/**
	 * Test that the service throws a PlanNotFound exception if no plan is found.
	 * @throws PlanNotFound if the test passes
	 */
	@Test(expected=PlanNotFound.class)
	public void testSolvePlanNotFound() throws PlanNotFound {
		
		Problem<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, ValidatableConstraint<SubstitutableTerm,
		SubstitutableTask, SubstitutableCondition>, SubstitutableCondition> mockProblem = createMockProblem();
		@SuppressWarnings("unchecked")
		Planner<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition>
				mockPlanner = mock(Planner.class);
		when(mockPlanner.findPlan(mockProblem.getState(), mockProblem.getTaskNetwork())).thenThrow(new PlanNotFound());
		@SuppressWarnings("unchecked")
		PlannerFactory<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition>
				mockPlannerFactory = mock(PlannerFactory.class);
		
		when(mockPlannerFactory.create(any(Domain.class))).thenReturn(mockPlanner);
		
		// Create the service to be tested
		SimplePlanningService plannerService = new SimplePlanningService(mockPlannerFactory);
		
		// Try and solve the problem, which should throw an exception
		plannerService.solve(mockProblem);
	}
	
	/**
	 * Create a simple mocked Problem.
	 * @return a mock problem
	 */
	private Problem<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
			ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>,SubstitutableCondition> createMockProblem() {
		
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		State mockState = mock(State.class);
		@SuppressWarnings("unchecked")
		Domain<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition>
				mockDomain = mock(Domain.class);
		@SuppressWarnings("unchecked")
		Problem<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SubstitutableCondition>
				mockProblem = mock(Problem.class);
		when(mockProblem.getTaskNetwork()).thenReturn(mockTaskNetwork);
		when(mockProblem.getState()).thenReturn(mockState);
		when(mockProblem.getDomain()).thenReturn(mockDomain);
		
		return mockProblem;
	}
}
