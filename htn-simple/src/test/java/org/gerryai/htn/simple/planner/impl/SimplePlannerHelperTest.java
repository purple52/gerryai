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

    private ImmutablePlanBuilderFactory mockPlanBuilderFactory;
    private ImmutableActionFactory mockActionFactory;
    private DecompositionService<ImmutableMethod,
            ImmutableTask, ImmutableTaskNetwork,
            ImmutableConstraint<?>> mockDecompositionService;
    private UnificationService<ImmutableMethod, Term,
            ImmutableTask, ImmutableTaskNetwork,
            ImmutableConstraint<?>, ImmutableCondition> mockUnificationService;
    private SortService<ImmutableTask,
            ImmutableConstraint<?>> mockSortService;
    private ImmutableStateService mockStateService;
    private ImmutableDomainHelper mockDomainHelper;
    
    private Map<ImmutablePlanBuilder, List<ImmutableAction>> builderActionMap;
    private Map<ImmutablePlanBuilder, List<ImmutableAction>> builderTargetActionMap;
    private Map<ImmutablePlanBuilder, ImmutablePlan> builderReturnMap;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
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

	@Test(expected=NonPrimitiveTaskNotFound.class)
	public void testGetNonPrimitiveTask() throws NonPrimitiveTaskNotFound {
		
	    SimplePlannerHelper plannerHelper = createHelper();
		
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		Set<ImmutableTask> tasks = new HashSet<ImmutableTask>();
		when(mockTaskNetwork.getTasks()).thenReturn(tasks);
		
		// Trying to find a non-primitive task should throw an exception since there isn't one
		plannerHelper.getNonPrimitiveTask(mockTaskNetwork);
		
	}
	
	@Test
	public void testFindPlanForPrimitiveEmptyTaskNetwork() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(0);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
		
		SimplePlannerHelper plannerHelper = createHelper();
		
		// Try to find a plan
		ImmutablePlan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertTrue(plan.getActions().isEmpty());
	}
	
	@Test
	public void testFindPlanForPrimitiveOneTask() throws PlanNotFound, TaskNotActionable {

		List<ImmutableAction> mockActions = setupPlanBuilderFactory(1);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
        SimplePlannerHelper plannerHelper = createHelper();
        
		// Try to find a plan
		ImmutablePlan plan = plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
		
		assertEquals(1, plan.getActions().size());
		assertTrue(plan.getActions().contains(mockActions.get(0)));
	}
	
	@Test
	public void testFindPlanForPrimitiveTwoTasks() throws PlanNotFound, TaskNotActionable {

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

    @Test(expected=PlanNotFound.class)
    public void testFindPlanForPrimitiveTwoTasksFirstFailsPreconditions() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(2);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
        ImmutableCondition mockGroundCondition = linkCondition(mockActions.get(0));
        
        when(mockStateService.ask(mockState, mockGroundCondition)).thenReturn(false);

        SimplePlannerHelper plannerHelper = createHelper();
        
        // Try to find a plan
        plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);
    }

    @Test(expected=PlanNotFound.class)
    public void testFindPlanForPrimitiveTwoTasksSecondFailsPreconditions() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(2);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
        ImmutableCondition mockGroundCondition = linkCondition(mockActions.get(1));
        
        when(mockStateService.ask(mockState, mockGroundCondition)).thenReturn(false);

        SimplePlannerHelper plannerHelper = createHelper();
        
        // Try to find a plan
        plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);
    }
    
	@Test(expected=PlanNotFound.class)
	public void testFindPlanForPrimitiveTaskNotActionable() throws PlanNotFound, TaskNotActionable {

        List<ImmutableAction> mockActions = setupPlanBuilderFactory(1);
        ImmutableTaskNetwork mockTaskNetwork = setupTaskNetwork(mockActions);
        ImmutableState mockState = mock(ImmutableState.class);
        
		when(mockActionFactory.create(mockTaskNetwork.getTasks().iterator().next())).thenThrow(new TaskNotActionable());
		
		SimplePlannerHelper plannerHelper = createHelper();
        
		// Try to find a plan which should throw an exception because the task account be converted
		plannerHelper.findPlanForPrimitive(mockState, mockTaskNetwork);		
	}
	
	/**
	 * Test that a task and method that cannot be unified cannot be decomposed
	 * @throws DecompositionNotFound
	 * @throws UnifierNotFound
	 */
	@Test(expected=DecompositionNotFound.class)
	public void testDecomposeNoUnifier() throws DecompositionNotFound, UnifierNotFound, InvalidConstraint {
		
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutableTask mockTask = mock(ImmutableTask.class);
		ImmutableMethod mockMethod = mock(ImmutableMethod.class);
		
		when(mockUnificationService.findUnifier(mockTask, mockMethod)).thenThrow(new UnifierNotFound());

        SimplePlannerHelper plannerHelper = createHelper();
        
		plannerHelper.decompose(mockTaskNetwork, mockTask, mockMethod);
	}
	
	/**
	 * Test that a unifiable task and method can be decomposed.
	 * @throws DecompositionNotFound
	 * @throws UnifierNotFound
	 */
	@Test
	public void testDecompose() throws DecompositionNotFound, UnifierNotFound, InvalidConstraint {
		
		ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutableTaskNetwork mockDecomposedTaskNetwork = mock(ImmutableTaskNetwork.class);
		ImmutableTask mockTask = mock(ImmutableTask.class);
		ImmutableMethod mockMethod = mock(ImmutableMethod.class);
		@SuppressWarnings("unchecked")
		Map<Term, Term> mockSubstitution = mock(Map.class);
		
        when(mockUnificationService.findUnifier(mockTask, mockMethod)).thenReturn(mockSubstitution);
        when(mockDecompositionService.decompose(mockSubstitution, mockTaskNetwork, mockTask, mockMethod)).thenReturn(mockDecomposedTaskNetwork);
         
        SimplePlannerHelper plannerHelper = createHelper();
        
		assertEquals(mockDecomposedTaskNetwork,
		        plannerHelper.decompose(mockTaskNetwork, mockTask, mockMethod));
	}
	
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
        
	    for(int i = 0; i < howMany; i++) {
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
	 * Set up a mock task network and prime the action factory and sort service
	 * @param mockActions the mock actions to map to
	 * @return the mock task network
	 * @throws PlanNotFound only if the test is broken
	 * @throws TaskNotActionable only if the test is broken
	 */
	private ImmutableTaskNetwork setupTaskNetwork(List<ImmutableAction> mockActions) throws PlanNotFound, TaskNotActionable {
	    
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
