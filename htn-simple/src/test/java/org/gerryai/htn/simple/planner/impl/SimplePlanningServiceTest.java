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

import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.plan.ImmutablePlan;
import org.gerryai.htn.simple.planner.ImmutablePlanner;
import org.gerryai.htn.simple.planner.ImmutablePlannerFactory;
import org.gerryai.htn.simple.problem.ImmutableProblem;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
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
		
		ImmutablePlan mockPlan = mock(ImmutablePlan.class);
		ImmutableProblem mockProblem = createMockProblem();
		ImmutablePlanner mockPlanner = mock(ImmutablePlanner.class);
		when(mockPlanner.findPlan(mockProblem.getState(), mockProblem.getTaskNetwork())).thenReturn(mockPlan);
		ImmutablePlannerFactory mockPlannerFactory = mock(ImmutablePlannerFactory.class);
		
		when(mockPlannerFactory.create(any(ImmutableDomain.class))).thenReturn(mockPlanner);
		
		// Create the service to be tested
		SimplePlanningService plannerService = new SimplePlanningService(mockPlannerFactory);
		
		// Try and solve the problem
		ImmutablePlan plan = plannerService.solve(mockProblem);
		
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
		
		ImmutableProblem mockProblem = createMockProblem();
		ImmutablePlanner mockPlanner = mock(ImmutablePlanner.class);
		when(mockPlanner.findPlan(mockProblem.getState(), mockProblem.getTaskNetwork())).thenThrow(new PlanNotFound());
		ImmutablePlannerFactory mockPlannerFactory = mock(ImmutablePlannerFactory.class);
		
		when(mockPlannerFactory.create(any(ImmutableDomain.class))).thenReturn(mockPlanner);
		
		// Create the service to be tested
		SimplePlanningService plannerService = new SimplePlanningService(mockPlannerFactory);
		
		// Try and solve the problem, which should throw an exception
		plannerService.solve(mockProblem);
	}
	
	/**
	 * Create a simple mocked Problem.
	 * @return a mock problem
	 */
	private ImmutableProblem createMockProblem() {
		
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutableState mockState = mock(ImmutableState.class);
		ImmutableDomain	mockDomain = mock(ImmutableDomain.class);
		ImmutableProblem mockProblem = mock(ImmutableProblem.class);
		when(mockProblem.getTaskNetwork()).thenReturn(mockTaskNetwork);
		when(mockProblem.getState()).thenReturn(mockState);
		when(mockProblem.getDomain()).thenReturn(mockDomain);
		
		return mockProblem;
	}
}
