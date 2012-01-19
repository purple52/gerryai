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

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.problem.State;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.PlannerHelper;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerTest {

	/**
	 * Test that an empty problem results in an empty plan.
	 * @throws PlanNotFound
	 * @throws NonPrimitiveTaskNotFound
	 */
	@Test
	public void testEmptyProblem() throws PlanNotFound, NonPrimitiveTaskNotFound {
		
		State mockState = mock(State.class);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		Plan mockPlan = mock(Plan.class);
		List<Action> actions = new ArrayList<Action>();
		when(mockPlan.getActions()).thenReturn(actions);
		
		// Create a mock planner helper that will throw an exception if no primitive tasks were found
		// And no plan if an empty network is searched
		PlannerHelper mockPlannerHelper = mock(PlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenThrow(new NonPrimitiveTaskNotFound());
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork)).thenReturn(mockPlan);
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		Plan plan = planner.findPlan(mockState, mockTaskNetwork);
		
		assertTrue(plan.getActions().isEmpty());
	}
	
	/**
	 * Test that a problem with a simple primitive task that cannot be achieved throws an exception.
	 * @throws NonPrimitiveTaskNotFound
	 * @throws PlanNotFound
	 */
	@Test(expected=PlanNotFound.class)
	public void testOneUnactionablePrimitiveTaskNo() throws NonPrimitiveTaskNotFound, PlanNotFound {
		
		State mockState = mock(State.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Create a mock planner helper that will throw an exception since no non-primitive tasks were found
		// and an exception when trying to action the primitive task
		PlannerHelper mockPlannerHelper = mock(PlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenThrow(new NonPrimitiveTaskNotFound());
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork)).thenThrow(new PlanNotFound());
		
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		planner.findPlan(mockState, mockTaskNetwork);
	}
	
	/**
	 * Test that a problem with a non-primitive task that cannot be decomposed because no methods exist throws an exception.
	 * @throws NonPrimitiveTaskNotFound
	 * @throws PlanNotFound
	 */
	@Test(expected=PlanNotFound.class)
	public void testOneUndecomposableNonPrimitiveTaskNoMethods() throws NonPrimitiveTaskNotFound, PlanNotFound {
		
		State mockState = mock(State.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Create a mock planner helper that will throw an exception since no non-primitive tasks were found
		// and an exception when trying to action the primitive task
		PlannerHelper mockPlannerHelper = mock(PlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenReturn(mockTaskA);
		
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		Set<Method> methods = new HashSet<Method>();
		when(mockDomainHelper.getMethodsByTask(mockTaskA)).thenReturn(methods);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		planner.findPlan(mockState, mockTaskNetwork);
	}

	/**
	 * Test that a problem with a non-primitive task that cannot be decomposed because methods failed throws an exception.
	 * @throws NonPrimitiveTaskNotFound
	 * @throws PlanNotFound
	 * @throws DecompositionNotFound 
	 */
	@Test(expected=PlanNotFound.class)
	public void testOneUndecomposableNonPrimitiveTaskMethodsFailed() throws NonPrimitiveTaskNotFound, PlanNotFound, DecompositionNotFound {
		
		State mockState = mock(State.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);

		// Create a domain helper that has two potentially matching methods
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		Method mockMethodA = mock(Method.class);
		Method mockMethodB = mock(Method.class);
		Set<Method> methods = new HashSet<Method>();
		methods.add(mockMethodA);
		methods.add(mockMethodB);
		when(mockDomainHelper.getMethodsByTask(mockTaskA)).thenReturn(methods);
		
		// Create a mock planner helper that will throw an exception since no non-primitive tasks were found
		// and an exception when trying to action the primitive task
		PlannerHelper mockPlannerHelper = mock(PlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenReturn(mockTaskA);
		when(mockPlannerHelper.decompose(mockTaskNetwork, mockTaskA, mockMethodA)).thenThrow(new DecompositionNotFound());
		when(mockPlannerHelper.decompose(mockTaskNetwork, mockTaskA, mockMethodB)).thenThrow(new DecompositionNotFound());
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		planner.findPlan(mockState, mockTaskNetwork);
	}
	
	/**
	 * Test that a problem with a simple primitive task to solve results in plan with a single action.
	 * @throws NonPrimitiveTaskNotFound
	 * @throws PlanNotFound
	 */
	@Test
	public void testOnePrimitiveTask() throws NonPrimitiveTaskNotFound, PlanNotFound {
		
		State mockState = mock(State.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		Plan mockPlan = mock(Plan.class);
		List<Action> actions = new ArrayList<Action>();
		Action mockActionA = mock(Action.class);
		actions.add(mockActionA);
		when(mockPlan.getActions()).thenReturn(actions);
		
		// Create a mock planner helper that will throw an exception if no primitive tasks were found
		// and a plan that returns one action in response to our network of one primitive task
		PlannerHelper mockPlannerHelper = mock(PlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenThrow(new NonPrimitiveTaskNotFound());
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork)).thenReturn(mockPlan);
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		Plan plan = planner.findPlan(mockState, mockTaskNetwork);
		
		assertEquals(1, plan.getActions().size());
		assertEquals(mockActionA, plan.getActions().get(0));
	}
	
	/**
	 * Test that a simple non-primitive task gets decomposed.
	 * @throws NonPrimitiveTaskNotFound
	 * @throws PlanNotFound
	 * @throws DecompositionNotFound
	 */
	@Test
	public void testOneNonPrimitiveTask() throws NonPrimitiveTaskNotFound, PlanNotFound, DecompositionNotFound {
		
		State mockState = mock(State.class);
		
		// Initial task network containing task A
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);

		// The network that the initial network will decompose into, containing task B
		Task mockTaskB = mock(Task.class);
		Set<Task> decomposedTasks = new HashSet<Task>();
		decomposedTasks.add(mockTaskB);
		TaskNetwork mockDecomposedTaskNetwork = mock(TaskNetwork.class);
		when(mockDecomposedTaskNetwork.getTasks()).thenReturn(decomposedTasks);
		
		// The target plan containing action B
		Plan mockPlan = mock(Plan.class);
		List<Action> actions = new ArrayList<Action>();
		Action mockActionB = mock(Action.class);
		actions.add(mockActionB);
		when(mockPlan.getActions()).thenReturn(actions);
		
		// Domain helper should return method A for decomposing task A
		Method mockMethodA = mock(Method.class);
		Set<Method> methods = new HashSet<Method>();
		methods.add(mockMethodA);
		DomainHelper mockDomainHelper = mock(DomainHelper.class);
		when(mockDomainHelper.getMethodsByTask(mockTaskA)).thenReturn(methods);
		
		// Create a mock planner helper
		PlannerHelper mockPlannerHelper = mock(PlannerHelper.class);
		// Task A is primitive
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenReturn(mockTaskA);
		// Method A will decompose task A into task B
		when(mockPlannerHelper.decompose(mockTaskNetwork, mockTaskA, mockMethodA)).thenReturn(mockDecomposedTaskNetwork);	
		// There are no non-primitive tasks in the decomposed network
		when(mockPlannerHelper.getNonPrimitiveTask(mockDecomposedTaskNetwork)).thenThrow(new NonPrimitiveTaskNotFound());
		// Plan for the decomposed network is the mock plan containing action B
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockDecomposedTaskNetwork)).thenReturn(mockPlan);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		Plan plan = planner.findPlan(mockState, mockTaskNetwork);
		
		assertEquals(1, plan.getActions().size());
		assertEquals(mockActionB, plan.getActions().get(0));
	}
}