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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.decomposition.UnifierNotFound;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableDomainHelper;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.plan.ImmutableAction;
import org.gerryai.htn.simple.plan.ImmutableActionFactory;
import org.gerryai.htn.simple.plan.ImmutablePlan;
import org.gerryai.htn.simple.plan.ImmutablePlanBuilder;
import org.gerryai.htn.simple.plan.ImmutablePlanBuilderFactory;
import org.gerryai.htn.simple.planner.DecompositionNotFound;
import org.gerryai.htn.simple.planner.sort.SortService;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.problem.ImmutableStateService;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerHelperTest {

	/**
	 * Mock plan builder factory.
	 */
    private ImmutablePlanBuilderFactory mockPlanBuilderFactory;
    
    /**
     * Mock action factory.
     */
    private ImmutableActionFactory mockActionFactory;
    
    /**
     * Mock decomposition service.
     */
    private DecompositionService<ImmutableMethod,
            ImmutableTask, ImmutableTaskNetwork,
            ImmutableConstraint<?>> mockDecompositionService;
    
    /**
     * Mock unification service.
     */
    private UnificationService<ImmutableMethod, Term,
            ImmutableTask, ImmutableTaskNetwork,
            ImmutableConstraint<?>, ImmutableCondition> mockUnificationService;
    
    /**
     * Mock sort service.
     */
    private SortService<ImmutableTask,
            ImmutableConstraint<?>> mockSortService;
    
    /**
     * Mock state service.
     */
    private ImmutableStateService mockStateService;
    
    /**
     * Mock domain helper.
     */
    private ImmutableDomainHelper mockDomainHelper;
    
    /**
     * Map of plan builders to actions for mocking plan builders.
     */
    private Map<ImmutablePlanBuilder, List<ImmutableAction>> builderActionMap;
    
    /**
     * Map of plan builders to target actions for mocking plan builders.
     */
    private Map<ImmutablePlanBuilder, List<ImmutableAction>> builderTargetActionMap;
    
    /**
     * Map of plan builders to plans for mocking plan builders.
     */
    private Map<ImmutablePlanBuilder, ImmutablePlan> builderReturnMap;
    
    /**
     * Set up factories.
     */
    @SuppressWarnings("unchecked")
    @Before
    public final void setup() {
        mockPlanBuilderFactory = mock(ImmutablePlanBuilderFactory.class);
        mockActionFactory = mock(ImmutableActionFactory.class);
        mockDecompositionService = mock(DecompositionService.class);
        mockUnificationService = mock(UnificationService.class);
        mockSortService = mock(SortService.class);
        mockStateService = mock(ImmutableStateService.class);
        mockDomainHelper = mock(ImmutableDomainHelper.class);
        builderActionMap = new HashMap<ImmutablePlanBuilder, List<ImmutableAction>>();
        builderTargetActionMap = new HashMap<ImmutablePlanBuilder, List<ImmutableAction>>();
        builderReturnMap = new HashMap<ImmutablePlanBuilder, ImmutablePlan>();
    }

    /**
     * Test finding a non-primitive task when none exists.
     * @throws NonPrimitiveTaskNotFound if the test passes
     */
	@Test(expected = NonPrimitiveTaskNotFound.class)
	public final void testGetNonPrimitiveTask() throws NonPrimitiveTaskNotFound {
		
	    SimplePlannerHelper plannerHelper = createHelper();
		
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		Set<ImmutableTask> tasks = new HashSet<ImmutableTask>();
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Trying to find a non-primitive task should throw an exception since there isn't one
		plannerHelper.getNonPrimitiveTask(mockTaskNetwork);
		
	}
	
	/**
	 * Test finding a plan for an empty task network.
	 * @throws PlanNotFound only if test fails
	 * @throws TaskNotActionable only if test fails
	 */
	@Test
	public final void testFindPlanForPrimitiveEmptyTaskNetwork() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(0);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
		
		SimplePlannerHelper plannerHelper = createHelper();
		
		// Try to find a plan
		ImmutablePlan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertTrue(plan.getActions().isEmpty());
	}
	
	/**
	 * Test finding a plan for a network containing a single primitive task.
	 * @throws PlanNotFound only of test fails
	 * @throws TaskNotActionable only if test fails
	 */
	@Test
	public final void testFindPlanForPrimitiveOneTask() throws PlanNotFound, TaskNotActionable {

		List<ImmutableAction> mockActions = setupPlanBuilderFactory(1);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
        SimplePlannerHelper plannerHelper = createHelper();
        
		// Try to find a plan
		ImmutablePlan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertEquals(1, plan.getActions().size());
		assertTrue(plan.getActions().contains(mockActions.get(0)));
	}
	
	/**
	 * Test finding a plan for a task network containing two primitive tasks.
	 * @throws PlanNotFound only if test fails
	 * @throws TaskNotActionable only if test fails
	 */
	@Test
	public final void testFindPlanForPrimitiveTwoTasks() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(2);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
		SimplePlannerHelper plannerHelper = createHelper();
		
		// Try to find a plan
		ImmutablePlan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertEquals(2, plan.getActions().size());
		assertTrue(plan.getActions().contains(mockActions.get(0)));
		assertTrue(plan.getActions().contains(mockActions.get(1)));
	}

	/**
	 * Test finding a plan for a task network where the preconditions of the first task are not met.
	 * @throws PlanNotFound if the test passes
	 * @throws TaskNotActionable only if the test fails
	 */
    @Test(expected = PlanNotFound.class)
    public final void testFindPlanForPrimitiveTwoTasksFirstFailsPreconditions() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(2);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
        ImmutableCondition mockGroundCondition = linkCondition(mockActions.get(0));
        
        when(mockStateService.ask(mockState, mockGroundCondition)).thenReturn(false);

        SimplePlannerHelper plannerHelper = createHelper();
        
        // Try to find a plan
        plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);
    }

    /**
     * Test finding a plan for two primitive tasks where the second task's preconditions are not met.
     * @throws PlanNotFound if the test passes
     * @throws TaskNotActionable only if the test fails
     */
    @Test(expected = PlanNotFound.class)
    public final void testFindPlanForPrimitiveTwoTasksSecondFailsPreconditions()
    		throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(2);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
        ImmutableCondition mockGroundCondition = linkCondition(mockActions.get(1));
        
        when(mockStateService.ask(mockState, mockGroundCondition)).thenReturn(false);

        SimplePlannerHelper plannerHelper = createHelper();
        
        // Try to find a plan
        plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);
    }
    
    /**
     * Test finding a plan for an unactionable primitive task.
     * @throws PlanNotFound if the test passes
     * @throws TaskNotActionable only if the test fails
     */
	@Test(expected = PlanNotFound.class)
	public final void testFindPlanForPrimitiveTaskNotActionable() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(1);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
		when(mockActionFactory.create(mockTaskNetwork.getTasks().iterator().next())).thenThrow(new TaskNotActionable());
		
		SimplePlannerHelper plannerHelper = createHelper();
        
		// Try to find a plan which should throw an exception because the task account be converted
		plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
	}
	
	/**
	 * Test that a task and method that cannot be unified cannot be decomposed.
	 * @throws DecompositionNotFound if the test passes
	 * @throws UnifierNotFound only if the test fails
	 * @throws InvalidConstraint only if the test fails
	 */
	@Test(expected = DecompositionNotFound.class)
	public final void testDecomposeNoUnifier() throws DecompositionNotFound, UnifierNotFound, InvalidConstraint {
		
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutableTask mockTask = mock(ImmutableTask.class);
		ImmutableMethod mockMethod = mock(ImmutableMethod.class);
		
		when(mockUnificationService.findUnifier(mockTask, mockMethod)).thenThrow(new UnifierNotFound());

        SimplePlannerHelper plannerHelper = createHelper();
        
		plannerHelper.decompose(mockTaskNetwork, mockTask, mockMethod);
	}
	
	/**
	 * Test that a unifiable task and method can be decomposed.
	 * @throws DecompositionNotFound only if the test fails
	 * @throws UnifierNotFound only if the test fails
	 * @throws InvalidConstraint only if the test fails
	 */
	@Test
	public final void testDecompose() throws DecompositionNotFound, UnifierNotFound, InvalidConstraint {
		
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutableTaskNetwork mockDecomposedTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutableTask mockTask = mock(ImmutableTask.class);
		ImmutableMethod mockMethod = mock(ImmutableMethod.class);
		@SuppressWarnings("unchecked")
		Map<Term, Term> mockSubstitution = mock(Map.class);
		
        when(mockUnificationService.findUnifier(mockTask, mockMethod))
        		.thenReturn(mockSubstitution);
        when(mockDecompositionService.decompose(mockSubstitution, mockTaskNetwork, mockTask, mockMethod))
        		.thenReturn(mockDecomposedTaskNetwork);
         
        SimplePlannerHelper plannerHelper = createHelper();
        
		assertEquals(mockDecomposedTaskNetwork,
		        plannerHelper.decompose(mockTaskNetwork, mockTask, mockMethod));
	}
	
	/**
	 * Create a planner helper.
	 * @return the helper
	 */
	private SimplePlannerHelper createHelper() {
        return new SimplePlannerHelper(mockActionFactory,
                mockPlanBuilderFactory, mockDecompositionService,
                mockUnificationService, mockSortService, mockStateService, mockDomainHelper);
    }
	
	/**
	 * Create a list of a specified number of mock actions.
	 * @param howMany number of actions to create
	 * @return the list of mock actions
	 */
	private List<ImmutableAction> createMockActions(int howMany) {
	    List<ImmutableAction> mockActions = new ArrayList<ImmutableAction>();
        
	    for (int i = 0; i < howMany; i++) {
	        ImmutableAction mockAction = mock(ImmutableAction.class);
	        mockActions.add(mockAction);
	        ImmutableOperator mockOperator = mock(ImmutableOperator.class);
	        when(mockAction.getOperator()).thenReturn(mockOperator);
	    }
	    return mockActions;
	}

	/**
	 * Create a plan builder that will return the plan provided when all the specified actions have been added.
	 * @param mockActions list of actions to check for
	 * @param mockPlan plan to return when actions have added
	 * @return the plan builder
	 */
	private ImmutablePlanBuilder createPlanBuilder(List<ImmutableAction> mockActions, ImmutablePlan mockPlan) {
	    ImmutablePlanBuilder mockPlanBuilder = mock(ImmutablePlanBuilder.class);
	    
	    if (mockActions.isEmpty()) {
	        verify(mockPlanBuilder, never()).addAction(any(ImmutableAction.class));
	        when(mockPlanBuilder.build()).thenReturn(mockPlan);
	    } else {
    	    builderActionMap.put(mockPlanBuilder, new ArrayList<ImmutableAction>());
    	    builderTargetActionMap.put(mockPlanBuilder, mockActions);
    	    builderReturnMap.put(mockPlanBuilder, mockPlan);
            when(mockPlanBuilder.addAction(any(ImmutableAction.class))).thenAnswer(new Answer<ImmutablePlanBuilder>() {
                public ImmutablePlanBuilder answer(InvocationOnMock invocation) {
                    Object[] args = invocation.getArguments();
                    ImmutablePlanBuilder mock = (ImmutablePlanBuilder) invocation.getMock();
                    builderActionMap.get(mock).add((ImmutableAction) args[0]);
                    if (builderTargetActionMap.get(mock).equals(builderActionMap.get(mock))) {
                        when(mock.build()).thenReturn(builderReturnMap.get(mock));
                    }
                    return mock;
                }
            });
	    }
	    
	    return mockPlanBuilder;
	}
	
	/**
	 * Set up the mock plan builder factory.
	 * @param howManyActions number of actions to create.
	 * @return the mock actions
	 */
	private List<ImmutableAction> setupPlanBuilderFactory(int howManyActions) {
	    List<ImmutableAction> mockActions = createMockActions(howManyActions);
        ImmutablePlan mockPlan = mock(ImmutablePlan.class);
        when(mockPlan.getActions()).thenReturn(mockActions);
        ImmutablePlanBuilder mockPlanBuilder = createPlanBuilder(mockActions, mockPlan);
        when(mockPlanBuilderFactory.createBuilder()).thenReturn(mockPlanBuilder);
        
        return mockActions;
	}
	
	/**
	 * Set up a mock task network and prime the action factory and sort service.
	 * @param mockActions the mock actions to map to
	 * @return the mock task network
	 * @throws PlanNotFound only if the test is broken
	 * @throws TaskNotActionable only if the test is broken
	 */
	private ImmutableTaskNetwork setupTaskNetwork(List<ImmutableAction> mockActions)
			throws PlanNotFound, TaskNotActionable {
	    
	    ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
	    Set<ImmutableTask> tasks = new HashSet<ImmutableTask>(mockActions.size());
        List<ImmutableTask> sortedTasks = new ArrayList<ImmutableTask>(mockActions.size());	    
	    for (ImmutableAction mockAction : mockActions) {
	        ImmutableTask mockTask = mock(ImmutableTask.class);
            tasks.add(mockTask);
        
            // Fixed order we're expecting the tasks back in - note in practice, this is
            // non-deterministic for tasks with equal precedence, but we need to fix the
            // sorted order to test.
            sortedTasks.add(mockTask);
            
            when(mockActionFactory.create(mockTask)).thenReturn(mockAction);
	    }
	    
        Set<ImmutableConstraint<?>> mockConstraints = new HashSet<ImmutableConstraint<?>>();
        when(mockTaskNetwork.getTasks()).thenReturn(tasks);
        when(mockTaskNetwork.getConstraints()).thenReturn(mockConstraints);
        when(mockSortService.sortByConstaints(tasks, mockConstraints)).thenReturn(sortedTasks);
        
        return mockTaskNetwork;
	}
	
	/**
	 * Create a mock condition that will be returned by the domain helper when working on the given action.
	 * @param mockAction the action being worked on
	 * @return the mock condition
	 */
	private ImmutableCondition linkCondition(ImmutableAction mockAction) {
	    
        Map<Variable, Constant> mockBindings = new HashMap<Variable, Constant>();
        when(mockAction.getBindings()).thenReturn(mockBindings);
        
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        Set<ImmutableCondition> mockConditions = new HashSet<ImmutableCondition>(1);
        mockConditions.add(mockCondition);
        when(mockAction.getOperator().getPreconditions()).thenReturn(mockConditions);
        
        ImmutableCondition mockGroundCondition = mock(ImmutableCondition.class);
        when(mockDomainHelper.getGroundedCondition(mockCondition, mockBindings)).thenReturn(mockGroundCondition);
        
        return mockGroundCondition;
	}
}
