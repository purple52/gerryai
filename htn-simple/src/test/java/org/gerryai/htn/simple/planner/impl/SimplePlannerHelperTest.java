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
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.htn.simple.logic.impl.SimpleUnifier;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.PlanFactory;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerHelperTest {

	@Test(expected=NonPrimitiveTaskNotFound.class)
	public void testGetNonPrimitiveTask() throws NonPrimitiveTaskNotFound {
		
		@SuppressWarnings("unchecked")
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				mockActionFactory = mock(ActionFactory.class);
		@SuppressWarnings("unchecked")
		PlanFactory<SimpleOperator, SimpleCondition> mockPlanFactory
				= mock(PlanFactory.class);
		@SuppressWarnings("unchecked")
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> mockDecompositionService
				= mock(DecompositionService.class);
		@SuppressWarnings("unchecked")
		UnificationService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition,
				SimpleVariable> mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Trying to find a non-primitive task should throw an exception since there isn't one
		plannerHelper.getNonPrimitiveTask(mockTaskNetwork);
		
	}
	
	@Test
	public void testFindPlanForPrimitiveEmptyTaskNetwork() throws PlanNotFound {

		List<Action<SimpleOperator, SimpleCondition>> actions
				= new ArrayList<Action<SimpleOperator, SimpleCondition>>();
		@SuppressWarnings("unchecked")
		Plan<SimpleOperator, SimpleCondition> mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		@SuppressWarnings("unchecked")
		PlanFactory<SimpleOperator, SimpleCondition> mockPlanFactory
				= mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		@SuppressWarnings("unchecked")
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				mockActionFactory = mock(ActionFactory.class);
		@SuppressWarnings("unchecked")
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockDecompositionService = mock(DecompositionService.class);
		@SuppressWarnings("unchecked")
		UnificationService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition,
				SimpleVariable> mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		// Try to find a plan
		Plan<SimpleOperator, SimpleCondition> plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertTrue(plan.getActions().isEmpty());
	}
	
	@Test
	public void testFindPlanForPrimitiveOneTask() throws PlanNotFound, TaskNotActionable {

		List<Action<SimpleOperator, SimpleCondition>> actions
				= new ArrayList<Action<SimpleOperator, SimpleCondition>>();
		@SuppressWarnings("unchecked")
		Plan<SimpleOperator, SimpleCondition> mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		@SuppressWarnings("unchecked")
		PlanFactory<SimpleOperator, SimpleCondition> mockPlanFactory
				= mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		
		@SuppressWarnings("unchecked")
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				mockActionFactory = mock(ActionFactory.class);
		@SuppressWarnings("unchecked")
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockDecompositionService = mock(DecompositionService.class);
		@SuppressWarnings("unchecked")
		UnificationService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition,
				SimpleVariable> mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		tasks.add(mockTaskA);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		@SuppressWarnings("unchecked")
		Action<SimpleOperator, SimpleCondition> mockActionA = mock(Action.class);
		when(mockActionFactory.create(mockTaskA)).thenReturn(mockActionA);
		
		// Try to find a plan
		Plan<SimpleOperator, SimpleCondition> plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertEquals(1, plan.getActions().size());
		assertTrue(plan.getActions().contains(mockActionA));
	}
	
	@Test
	public void testFindPlanForPrimitiveTwoTasks() throws PlanNotFound, TaskNotActionable {

		List<Action<SimpleOperator, SimpleCondition>> actions
				= new ArrayList<Action<SimpleOperator, SimpleCondition>>();
		@SuppressWarnings("unchecked")
		Plan<SimpleOperator, SimpleCondition> mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		@SuppressWarnings("unchecked")
		PlanFactory<SimpleOperator, SimpleCondition> mockPlanFactory
				= mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		
		@SuppressWarnings("unchecked")
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				mockActionFactory = mock(ActionFactory.class);
		@SuppressWarnings("unchecked")
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockDecompositionService = mock(DecompositionService.class);
		@SuppressWarnings("unchecked")
		UnificationService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition,
				SimpleVariable> mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		tasks.add(mockTaskA);
		tasks.add(mockTaskB);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		@SuppressWarnings("unchecked")
		Action<SimpleOperator, SimpleCondition> mockActionA = mock(Action.class);
		@SuppressWarnings("unchecked")
		Action<SimpleOperator, SimpleCondition> mockActionB = mock(Action.class);
		when(mockActionFactory.create(mockTaskA)).thenReturn(mockActionA);
		when(mockActionFactory.create(mockTaskB)).thenReturn(mockActionB);
		
		// Try to find a plan
		Plan<SimpleOperator, SimpleCondition> plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertEquals(2, plan.getActions().size());
		assertTrue(plan.getActions().contains(mockActionA));
		assertTrue(plan.getActions().contains(mockActionB));
	}
	
	@Test(expected=PlanNotFound.class)
	public void testFindPlanForPrimitiveTaskNotActionable() throws PlanNotFound, TaskNotActionable {

		List<Action<SimpleOperator, SimpleCondition>> actions
				= new ArrayList<Action<SimpleOperator, SimpleCondition>>();
		@SuppressWarnings("unchecked")
		Plan<SimpleOperator, SimpleCondition> mockPlan = mock(Plan.class);
		when(mockPlan.getActions()).thenReturn(actions);
		@SuppressWarnings("unchecked")
		PlanFactory<SimpleOperator, SimpleCondition> mockPlanFactory
				= mock(PlanFactory.class);
		when(mockPlanFactory.create()).thenReturn(mockPlan);
		
		@SuppressWarnings("unchecked")
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				mockActionFactory = mock(ActionFactory.class);
		@SuppressWarnings("unchecked")
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockDecompositionService = mock(DecompositionService.class);
		@SuppressWarnings("unchecked")
		UnificationService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition,
				SimpleVariable> mockUnificationService = mock(UnificationService.class);

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		SubstitutableTask mockTaskA = mock(SubstitutableTask.class);
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		Set<SubstitutableTask> tasks = new HashSet<SubstitutableTask>();
		tasks.add(mockTaskA);
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		State mockState = mock(State.class);
		
		when(mockActionFactory.create(mockTaskA)).thenThrow(new TaskNotActionable());
		
		// Try to find a plan which should throw an exception because the task account be converted
		plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
	}
	
	/**
	 * Test that a task and method that cannot be unified cannot be decomposed
	 * @throws DecompositionNotFound
	 * @throws UnifierNotFound
	 */
	@Test(expected=DecompositionNotFound.class)
	public void testDecomposeNoUnifier() throws DecompositionNotFound, UnifierNotFound {
		
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		SubstitutableTask mockTask = mock(SubstitutableTask.class);
		SimpleMethod mockMethod = mock(SimpleMethod.class);
		
		@SuppressWarnings("unchecked")
		PlanFactory<SimpleOperator, SimpleCondition> mockPlanFactory
				= mock(PlanFactory.class);
		@SuppressWarnings("unchecked")
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				mockActionFactory = mock(ActionFactory.class);
		@SuppressWarnings("unchecked")
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockDecompositionService = mock(DecompositionService.class);
		@SuppressWarnings("unchecked")
		UnificationService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition,
				SimpleVariable> mockUnificationService = mock(UnificationService.class);
		when(mockUnificationService.findUnifier(mockTask, mockMethod)).thenThrow(new UnifierNotFound());
		
		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		plannerHelper.decompose(mockTaskNetwork, mockTask, mockMethod);
	}
	
	/**
	 * Test that a unifiable task and method can be decomposed.
	 * @throws DecompositionNotFound
	 * @throws UnifierNotFound
	 */
	@Test
	public void testDecompose() throws DecompositionNotFound, UnifierNotFound {
		
		SubstitutableTaskNetwork mockTaskNetwork = mock(SubstitutableTaskNetwork.class);
		SubstitutableTaskNetwork mockDecomposedTaskNetwork = mock(SubstitutableTaskNetwork.class);
		SubstitutableTask mockTask = mock(SubstitutableTask.class);
		SimpleMethod mockMethod = mock(SimpleMethod.class);
		SimpleUnifier mockUnifier = mock(SimpleUnifier.class);
		
		@SuppressWarnings("unchecked")
		PlanFactory<SimpleOperator, SimpleCondition> mockPlanFactory
				= mock(PlanFactory.class);
		@SuppressWarnings("unchecked")
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				mockActionFactory = mock(ActionFactory.class);
		@SuppressWarnings("unchecked")
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				mockDecompositionService = mock(DecompositionService.class);
		@SuppressWarnings("unchecked")
		UnificationService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition,
				SimpleVariable> mockUnificationService = mock(UnificationService.class);
		when(mockUnificationService.findUnifier(mockTask, mockMethod)).thenReturn(mockUnifier);
		when(mockDecompositionService.decompose(mockUnifier, mockTaskNetwork, mockTask, mockMethod)).thenReturn(mockDecomposedTaskNetwork);
		
		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(mockActionFactory,
				mockPlanFactory, mockDecompositionService, mockUnificationService);
		
		assertEquals(mockDecomposedTaskNetwork,plannerHelper.decompose(mockTaskNetwork, mockTask, mockMethod));
	}
}
