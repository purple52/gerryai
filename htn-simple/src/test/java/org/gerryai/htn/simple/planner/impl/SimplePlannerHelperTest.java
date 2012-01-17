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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.decomposition.DecompositionService;
import org.gerryai.htn.decomposition.UnificationService;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.PlanFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerHelperTest {

	@Test(expected=PrimitiveTaskNotFound.class)
	public void testGetNonPrimitiveTask() throws PrimitiveTaskNotFound {
		
		ActionFactory mockActionFactory = mock(ActionFactory.class);
		PlanFactory mockPlanFactory = mock(PlanFactory.class);
		DecompositionService mockDecompositionService = mock(DecompositionService.class);
		UnificationService mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		Set<Task> tasks = new HashSet<Task>();
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Trying to find a non-primitive task should throw an exception since there isn't one
		plannerHelper.getNonPrimitiveTask(mockTaskNetwork);
		
	}
	
	@Test
	public void testFindPlanForPrimitiveEmptyTaskNetwork() throws PlanNotFound {

		List<Action> actions = new ArrayList<Action>();
		Plan mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		PlanFactory mockPlanFactory = mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		
		ActionFactory mockActionFactory = mock(ActionFactory.class);
		DecompositionService mockDecompositionService = mock(DecompositionService.class);
		UnificationService mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		Set<Task> tasks = new HashSet<Task>();
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		// Try to find a plan
		Plan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertTrue(plan.getActions().isEmpty());
	}
	
	@Test
	public void testFindPlanForPrimitiveOneTask() throws PlanNotFound, TaskNotActionable {

		List<Action> actions = new ArrayList<Action>();
		Plan mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		PlanFactory mockPlanFactory = mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		
		ActionFactory mockActionFactory = mock(ActionFactory.class);
		DecompositionService mockDecompositionService = mock(DecompositionService.class);
		UnificationService mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		Task mockTaskA = mock(Task.class);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		Action mockActionA = mock(Action.class);
		when(mockActionFactory.create(mockTaskA)).thenReturn(mockActionA);
		
		// Try to find a plan
		Plan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertEquals(1, plan.getActions().size());
		assertTrue(plan.getActions().contains(mockActionA));
	}
	
	@Test
	public void testFindPlanForPrimitiveTwoTasks() throws PlanNotFound, TaskNotActionable {

		List<Action> actions = new ArrayList<Action>();
		Plan mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		PlanFactory mockPlanFactory = mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		
		ActionFactory mockActionFactory = mock(ActionFactory.class);
		DecompositionService mockDecompositionService = mock(DecompositionService.class);
		UnificationService mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		tasks.add(mockTaskB);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		Action mockActionA = mock(Action.class);
		Action mockActionB = mock(Action.class);
		when(mockActionFactory.create(mockTaskA)).thenReturn(mockActionA);
		when(mockActionFactory.create(mockTaskB)).thenReturn(mockActionB);
		
		// Try to find a plan
		Plan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertEquals(2, plan.getActions().size());
		assertTrue(plan.getActions().contains(mockActionA));
		assertTrue(plan.getActions().contains(mockActionB));
	}
	
	@Test(expected=PlanNotFound.class)
	public void testFindPlanForPrimitiveTaskNotActionable() throws PlanNotFound, TaskNotActionable {

		List<Action> actions = new ArrayList<Action>();
		Plan mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		PlanFactory mockPlanFactory = mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		
		ActionFactory mockActionFactory = mock(ActionFactory.class);
		DecompositionService mockDecompositionService = mock(DecompositionService.class);
		UnificationService mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		Task mockTaskA = mock(Task.class);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		when(mockActionFactory.create(mockTaskA)).thenThrow(new TaskNotActionable());
		
		// Try to find a plan which should throw an exception because the task account be converted
		plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
	}
}
