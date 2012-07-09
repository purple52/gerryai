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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.domain.ImmutableDomainHelper;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.plan.ImmutableAction;
import org.gerryai.htn.simple.plan.ImmutablePlan;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.ImmutablePlannerHelper;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerTest {

	/**
	 * Test that an empty problem results in an empty plan.
	 * @throws PlanNotFound only if the test is broken
	 * @throws NonPrimitiveTaskNotFound only if the test is broken
	 */
	@Test
	public final void testEmptyProblem() throws PlanNotFound, NonPrimitiveTaskNotFound {
		
		ImmutableState mockState = mock(ImmutableState.class);
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutablePlan mockPlan = mock(ImmutablePlan.class);
		List<ImmutableAction> actions = new ArrayList<ImmutableAction>();
		when(mockPlan.getActions()).thenReturn(actions);
		
		// Create a mock planner helper that will throw an exception if no primitive tasks were found
		// And no plan if an empty network is searched
		ImmutablePlannerHelper mockPlannerHelper = mock(ImmutablePlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenThrow(new NonPrimitiveTaskNotFound());
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork)).thenReturn(mockPlan);
		ImmutableDomainHelper mockDomainHelper = mock(ImmutableDomainHelper.class);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		ImmutablePlan plan = planner.findPlan(mockState, mockTaskNetwork);
		
		assertTrue(plan.getActions().isEmpty());
	}
	
	/**
	 * Test that a problem with a simple primitive task that cannot be achieved throws an exception.
	 * @throws NonPrimitiveTaskNotFound only if the test is broken
	 * @throws PlanNotFound if the test passes
	 */
	@Test(expected = PlanNotFound.class)
	public final void testOneUnactionablePrimitiveTaskNo() throws NonPrimitiveTaskNotFound, PlanNotFound {
		
	    ImmutableState mockState = mock(ImmutableState.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Create a mock planner helper that will throw an exception since no non-primitive tasks were found
		// and an exception when trying to action the primitive task
		ImmutablePlannerHelper mockPlannerHelper = mock(ImmutablePlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenThrow(new NonPrimitiveTaskNotFound());
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork)).thenThrow(new PlanNotFound());
		ImmutableDomainHelper mockDomainHelper = mock(ImmutableDomainHelper.class);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		planner.findPlan(mockState, mockTaskNetwork);
	}
	
	/**
	 * Test a problem with a non-primitive task that cannot be decomposed because no methods exist.
	 * @throws NonPrimitiveTaskNotFound only if the test is broken
	 * @throws PlanNotFound if the test passes
	 */
	@Test(expected = PlanNotFound.class)
	public final void testOneUndecomposableNonPrimitiveTaskNoMethods() throws NonPrimitiveTaskNotFound, PlanNotFound {
		
	    ImmutableState mockState = mock(ImmutableState.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Create a mock planner helper that will throw an exception since no non-primitive tasks were found
		// and an exception when trying to action the primitive task
		ImmutablePlannerHelper mockPlannerHelper = mock(ImmutablePlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenReturn(mockTaskA);
		ImmutableDomainHelper mockDomainHelper = mock(ImmutableDomainHelper.class);
		Set<ImmutableMethod> methods = new HashSet<ImmutableMethod>();
		when(mockDomainHelper.getMethodsByTask(mockTaskA)).thenReturn(methods);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		planner.findPlan(mockState, mockTaskNetwork);
	}

	/**
	 * Test a problem with a non-primitive task that cannot be decomposed because methods failed.
	 * @throws NonPrimitiveTaskNotFound only if the test is broken
	 * @throws PlanNotFound if the test passes
	 * @throws DecompositionNotFound only if the test fails
	 * @throws InvalidConstraint only if the test fails
	 */
	@Test(expected = PlanNotFound.class)
	public final void testOneUndecomposableNonPrimitiveTaskMethodsFailed()
			throws NonPrimitiveTaskNotFound, PlanNotFound, DecompositionNotFound, InvalidConstraint  {
		
	    ImmutableState mockState = mock(ImmutableState.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);

		// Create a domain helper that has two potentially matching methods
		ImmutableDomainHelper mockDomainHelper = mock(ImmutableDomainHelper.class);
		ImmutableMethod mockMethodA = mock(ImmutableMethod.class);
		ImmutableMethod mockMethodB = mock(ImmutableMethod.class);
		Set<ImmutableMethod> methods = new HashSet<ImmutableMethod>();
		methods.add(mockMethodA);
		methods.add(mockMethodB);
		when(mockDomainHelper.getMethodsByTask(mockTaskA)).thenReturn(methods);
		
		// Create a mock planner helper that will throw an exception since no non-primitive tasks were found
		// and an exception when trying to action the primitive task
		ImmutablePlannerHelper mockPlannerHelper = mock(ImmutablePlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenReturn(mockTaskA);
		when(mockPlannerHelper.decompose(mockTaskNetwork, mockTaskA, mockMethodA))
				.thenThrow(new DecompositionNotFound());
		when(mockPlannerHelper.decompose(mockTaskNetwork, mockTaskA, mockMethodB))
				.thenThrow(new DecompositionNotFound());
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		planner.findPlan(mockState, mockTaskNetwork);
	}
	
	/**
	 * Test that a problem with a simple primitive task to solve results in plan with a single action.
	 * @throws NonPrimitiveTaskNotFound only if the test is broken
	 * @throws PlanNotFound only if the test is broken
	 */
	@Test
	public final void testOnePrimitiveTask() throws NonPrimitiveTaskNotFound, PlanNotFound {
		
	    ImmutableState mockState = mock(ImmutableState.class);
		
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		ImmutablePlan mockPlan = mock(ImmutablePlan.class);
		List<ImmutableAction> actions = new ArrayList<ImmutableAction>();
		ImmutableAction mockActionA = mock(ImmutableAction.class);
		actions.add(mockActionA);
		when(mockPlan.getActions()).thenReturn(actions);
		
		// Create a mock planner helper that will throw an exception if no primitive tasks were found
		// and a plan that returns one action in response to our network of one primitive task
		ImmutablePlannerHelper mockPlannerHelper = mock(ImmutablePlannerHelper.class);
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenThrow(new NonPrimitiveTaskNotFound());
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork)).thenReturn(mockPlan);
		ImmutableDomainHelper mockDomainHelper = mock(ImmutableDomainHelper.class);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		ImmutablePlan plan = planner.findPlan(mockState, mockTaskNetwork);
		
		assertEquals(1, plan.getActions().size());
		assertEquals(mockActionA, plan.getActions().get(0));
	}
	
	/**
	 * Test that a simple non-primitive task gets decomposed.
	 * @throws NonPrimitiveTaskNotFound only if the test is broken
	 * @throws PlanNotFound only if the test is broken
	 * @throws DecompositionNotFound only if the test is broken
	 * @throws InvalidConstraint only if the test is broken
	 */
	@Test
	public final void testOneNonPrimitiveTask()
			throws NonPrimitiveTaskNotFound, PlanNotFound, DecompositionNotFound, InvalidConstraint {
		
	    ImmutableState mockState = mock(ImmutableState.class);
		
		// Initial task network containing task A
		Task mockTaskA = mock(Task.class);
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(mockTaskA);
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);

		// The network that the initial network will decompose into, containing task B
		Task mockTaskB = mock(Task.class);
		Set<Task> decomposedTasks = new HashSet<Task>();
		decomposedTasks.add(mockTaskB);
		ImmutableTaskNetwork mockDecomposedTaskNetwork = mock(ImmutableTaskNetwork.class);
		when(mockDecomposedTaskNetwork.getTasks()).thenReturn(decomposedTasks);
		
		// The target plan containing action B
		ImmutablePlan mockPlan = mock(ImmutablePlan.class);
		List<ImmutableAction> actions = new ArrayList<ImmutableAction>();
		ImmutableAction mockActionB = mock(ImmutableAction.class);
		actions.add(mockActionB);
		when(mockPlan.getActions()).thenReturn(actions);
		
		// Domain helper should return method A for decomposing task A
		ImmutableMethod mockMethodA = mock(ImmutableMethod.class);
		Set<ImmutableMethod> methods = new HashSet<ImmutableMethod>();
		methods.add(mockMethodA);
		ImmutableDomainHelper mockDomainHelper = mock(ImmutableDomainHelper.class);
		when(mockDomainHelper.getMethodsByTask(mockTaskA)).thenReturn(methods);
		
		// Create a mock planner helper
		ImmutablePlannerHelper mockPlannerHelper = mock(ImmutablePlannerHelper.class);
		// Task A is primitive
		when(mockPlannerHelper.getNonPrimitiveTask(mockTaskNetwork)).thenReturn(mockTaskA);
		// Method A will decompose task A into task B
		when(mockPlannerHelper.decompose(mockTaskNetwork, mockTaskA, mockMethodA))
				.thenReturn(mockDecomposedTaskNetwork);	
		// There are no non-primitive tasks in the decomposed network
		when(mockPlannerHelper.getNonPrimitiveTask(mockDecomposedTaskNetwork))
				.thenThrow(new NonPrimitiveTaskNotFound());
		// Plan for the decomposed network is the mock plan containing action B
		when(mockPlannerHelper.findPlanForPrimitive(mockState, mockDecomposedTaskNetwork)).thenReturn(mockPlan);
		
		// Create the planner to be tested
		SimplePlanner planner = new SimplePlanner(mockDomainHelper, mockPlannerHelper);
		
		// Try and find a plan
		ImmutablePlan plan = planner.findPlan(mockState, mockTaskNetwork);
		
		assertEquals(1, plan.getActions().size());
		assertEquals(mockActionB, plan.getActions().get(0));
	}
}
