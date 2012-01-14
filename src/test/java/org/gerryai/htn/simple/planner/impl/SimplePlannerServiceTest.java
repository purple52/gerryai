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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerServiceTest {

	@Mock
	private Planner mockPlanner;
 
	@InjectMocks
	private SimplePlannerService plannerService;
	
    @Before
    public void initMocks() {
    	mockPlanner = mock(Planner.class);
    	plannerService = new SimplePlannerService();
    	MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void testSolvePlanFound() throws PlanNotFound {
		
		Plan mockPlan = mock(Plan.class);
		Problem mockProblem = createMockProblem();
		when(mockPlanner.findPlan(mockProblem.getState(), mockProblem.getTaskNetwork(), mockProblem.getDomain())).thenReturn(mockPlan);

		Plan plan = plannerService.solve(mockProblem);
		verify(mockPlanner).findPlan(mockProblem.getState(), mockProblem.getTaskNetwork(), mockProblem.getDomain());
		assertEquals(mockPlan, plan);
	}

	
	@Test(expected=PlanNotFound.class)
	public void testSolvePlanNotFound() throws PlanNotFound {
		
		Problem mockProblem = createMockProblem();
		when(mockPlanner.findPlan(mockProblem.getState(), mockProblem.getTaskNetwork(), mockProblem.getDomain())).thenThrow(new PlanNotFound());

		plannerService.solve(mockProblem);
	}
	
	/**
	 * Create a simple mocked Problem
	 * @return a mock problem
	 */
	private Problem createMockProblem() {
		
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		State mockState = mock(State.class);
		Domain mockDomain = mock(Domain.class);
		
		Problem mockProblem = mock(Problem.class);
		when(mockProblem.getTaskNetwork()).thenReturn(mockTaskNetwork);
		when(mockProblem.getState()).thenReturn(mockState);
		when(mockProblem.getDomain()).thenReturn(mockDomain);
		
		return mockProblem;
	}
}
