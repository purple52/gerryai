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
package org.gerryai.htn.simple.integration;

import static org.junit.Assert.assertEquals;

import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableEffect;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.plan.ImmutablePlan;
import org.gerryai.htn.simple.planner.ImmutablePlanningFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningFactory;
import org.gerryai.htn.simple.problem.ImmutableProblem;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Variable;
import org.gerryai.logic.builder.SentenceBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * End-to-end integration test of simple implementation using the JSHOP basic example.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class BasicIT {

	/**
	 * Planning factory used by the test.
	 */
    private ImmutablePlanningFactory planningFactory;
    
    /**
     * Initialise the planning factory.
     */
    @Before
    public final void init() {
        planningFactory = new SimplePlanningFactory();
    }
    
	/**
	 * Test basic domain with basic problem.
	 * @throws PlanNotFound only if the test fails
	 * @throws InvalidConstraint only if the test fails
	 */
	@Test
	public final void testKiwiBanjo() throws PlanNotFound, InvalidConstraint {

	    ImmutableDomain domain = createDomain();
	    
		// Build the task network to be solved
		Constant constantKiwi = planningFactory.getLogicFactory().createConstant("kiwi");
		Constant constantBanjo = planningFactory.getLogicFactory().createConstant("banjo");
		ImmutableTask task = planningFactory.getTaskNetworkFactory().createTaskBuilder()
				.setName("swap")
				.addArgument(constantKiwi)
				.addArgument(constantBanjo)
				.setIsPrimitive(false)
				.build();
		ImmutableTaskNetwork taskNetwork = planningFactory.getTaskNetworkFactory().createTaskNetworkBuilder()
				.addTask(task)
				.build();
		
		// Create the initial state
		ImmutableEffect effect = planningFactory.getDomainBuilderFactory().createEffectBuilder()
		        .setSentence(planningFactory.getLogicFactory().sentenceBuilder()
		                .predicate("have")
                        .addTerm(constantKiwi)
                        .build())
                .build();
		ImmutableState state = planningFactory.getStateService().createStateBuilder()
		        .tell(effect)
		        .build();
		        
		// Create the problem by putting together the domain and network to be solved;
		ImmutableProblem problem = planningFactory.getProblemBuilderFactory().createProblemBuilder()
		        .setState(state)
		        .setDomain(domain)
		        .setTaskNetwork(taskNetwork)
		        .build();
		
		// Solve the problem
		ImmutablePlan plan = planningFactory.getPlanningService().solve(problem);
		
		assertEquals(2, plan.getActions().size());
		assertEquals("drop", plan.getActions().get(0).getOperator().getName());
		assertEquals(plan.getActions().get(0).getBindings().size(), 1);
		assertEquals(plan.getActions().get(0).getBindings()
				.get(plan.getActions().get(0).getOperator().getArguments().get(0)), constantKiwi);
		assertEquals(plan.getActions().get(1).getOperator().getName(), "pickup");
		assertEquals(plan.getActions().get(1).getBindings().size(), 1);
		assertEquals(plan.getActions().get(1).getBindings()
				.get(plan.getActions().get(1).getOperator().getArguments().get(0)), constantBanjo);

	}
	
	/**
	 * Test basic domain with reverse problem.
	 * @throws PlanNotFound only if the test fails
	 * @throws InvalidConstraint only if the test fails
	 */
    @Test
    public final void testBanjoKiwi() throws PlanNotFound, InvalidConstraint {
        
        ImmutablePlanningFactory planningFactory = new SimplePlanningFactory();
        
        ImmutableDomain domain = createDomain();
        
        // Build the task network to be solved
        Constant constantKiwi = planningFactory.getLogicFactory().createConstant("kiwi");
        Constant constantBanjo = planningFactory.getLogicFactory().createConstant("banjo");
        ImmutableTask task = planningFactory.getTaskNetworkFactory().createTaskBuilder()
                .setName("swap")
                .addArgument(constantBanjo)
                .addArgument(constantKiwi)
                .setIsPrimitive(false)
                .build();
        ImmutableTaskNetwork taskNetwork = planningFactory.getTaskNetworkFactory().createTaskNetworkBuilder()
                .addTask(task)
                .build();
        
        // Create the initial state
        ImmutableEffect effect = planningFactory.getDomainBuilderFactory().createEffectBuilder()
                .setSentence(planningFactory.getLogicFactory().sentenceBuilder()
                        .predicate("have")
                        .addTerm(constantBanjo)
                        .build())
                .build();
        ImmutableState state = planningFactory.getStateService().createStateBuilder()
                .tell(effect)
                .build();
                
        // Create the problem by putting together the domain and network to be solved;
        ImmutableProblem problem = planningFactory.getProblemBuilderFactory().createProblemBuilder()
                .setState(state)
                .setDomain(domain)
                .setTaskNetwork(taskNetwork)
                .build();
        
        // Solve the problem
        ImmutablePlan plan = planningFactory.getPlanningService().solve(problem);
        
        assertEquals(2, plan.getActions().size());
        assertEquals("drop", plan.getActions().get(0).getOperator().getName());
        assertEquals(plan.getActions().get(0).getBindings().size(), 1);
        assertEquals(plan.getActions().get(0).getBindings()
                .get(plan.getActions().get(0).getOperator().getArguments().get(0)), constantBanjo);
        assertEquals(plan.getActions().get(1).getOperator().getName(), "pickup");
        assertEquals(plan.getActions().get(1).getBindings().size(), 1);
        assertEquals(plan.getActions().get(1).getBindings()
                .get(plan.getActions().get(1).getOperator().getArguments().get(0)), constantKiwi);

    }
    
    /**
     * Create the basic domain.
     * @return the domain
     * @throws PlanNotFound only if test fails
     * @throws InvalidConstraint only if test fails
     */
	private ImmutableDomain createDomain() throws PlanNotFound, InvalidConstraint {
	    
	    
        Variable variableA = planningFactory.getLogicFactory().createVariable("a");
        ImmutableEffect effectHaveA = planningFactory.getDomainBuilderFactory().createEffectBuilder()
                .setSentence(getSentenceBuilder().predicate("have")
                        .addTerm(variableA)
                        .build())
                .build();
        ImmutableEffect effectNotHaveA = planningFactory.getDomainBuilderFactory().createEffectBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableA)
                            .build()))
                .build();
        ImmutableCondition conditionHaveA = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(getSentenceBuilder().predicate("have")
                        .addTerm(variableA)
                        .build())
                .build();
        ImmutableCondition conditionNotHaveA = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableA)
                            .build()))
                .build();
        
        ImmutableOperator operatorA = planningFactory.getDomainBuilderFactory().createOperatorBuilder()
                .setName("pickup")
                .addArgument(variableA)
                .addEffect(effectHaveA)
                .addPrecondition(conditionNotHaveA)
                .build();
        ImmutableOperator operatorB = planningFactory.getDomainBuilderFactory().createOperatorBuilder()
                .setName("drop")
                .addArgument(variableA)
                .addEffect(effectNotHaveA)
                .addPrecondition(conditionHaveA)
                .build();
        
        Variable variableX = planningFactory.getLogicFactory().createVariable("x");
        Variable variableY = planningFactory.getLogicFactory().createVariable("y");
        
        ImmutableTask methodATask = planningFactory.getTaskNetworkFactory().createTaskBuilder()
                .setName("swap")
                .addArgument(variableX)
                .addArgument(variableY)
                .setIsPrimitive(false)
                .build();
        ImmutableTask methodASubTask1  = planningFactory.getTaskNetworkFactory().createTaskBuilder()
                .setName("drop")
                .addArgument(variableX)
                .setIsPrimitive(true)
                .build();
        ImmutableTask methodASubTask2  = planningFactory.getTaskNetworkFactory().createTaskBuilder()
                .setName("pickup")
                .addArgument(variableY)
                .setIsPrimitive(true)
                .build();
        ImmutableCondition beforeConditionHaveX = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().predicate("have")
                        .addTerm(variableX)
                        .build())
                .build();
        ImmutableCondition beforeConditionNotHaveY = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableY)
                            .build()))
                .build();
        ImmutableConstraint<?> beforeConstraintHaveX = planningFactory.getConstraintFactory()
                .createBeforeConstraint(methodASubTask1, beforeConditionHaveX);
        ImmutableConstraint<?> beforeConstraintNotHaveY = planningFactory.getConstraintFactory()
                .createBeforeConstraint(methodASubTask1, beforeConditionNotHaveY);
        ImmutableConstraint<?> precedenceConstraintA = planningFactory.getConstraintFactory()
                .createPrecedenceConstraint(methodASubTask1, methodASubTask2);
        ImmutableTaskNetwork methodATaskNetwork = planningFactory.getTaskNetworkFactory().createTaskNetworkBuilder()
                .addTask(methodASubTask1)
                .addTask(methodASubTask2)
                .addConstraint(precedenceConstraintA)
                .addConstraint(beforeConstraintHaveX)
                .addConstraint(beforeConstraintNotHaveY)
                .build();
        
        ImmutableTask methodBTask  = planningFactory.getTaskNetworkFactory().createTaskBuilder()
                .setName("swap")
                .addArgument(variableX)
                .addArgument(variableY)
                .setIsPrimitive(false)
                .build();
        ImmutableTask methodBSubTask1  = planningFactory.getTaskNetworkFactory().createTaskBuilder()
                .setName("drop")
                .addArgument(variableY)
                .setIsPrimitive(true)
                .build();
        ImmutableTask methodBSubTask2  = planningFactory.getTaskNetworkFactory().createTaskBuilder()
                .setName("pickup")
                .addArgument(variableX)
                .setIsPrimitive(true)
                .build();
        ImmutableCondition beforeConditionNotHaveX = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableX)
                            .build()))
                .build();
        ImmutableCondition beforeConditionHaveY = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().predicate("have")
                        .addTerm(variableY)
                        .build())
                .build();
        ImmutableConstraint<?> beforeConstraintNotHaveX = planningFactory.getConstraintFactory()
                .createBeforeConstraint(methodBSubTask1, beforeConditionNotHaveX);
        ImmutableConstraint<?> beforeConstraintHaveY = planningFactory.getConstraintFactory()
                .createBeforeConstraint(methodBSubTask1, beforeConditionHaveY);
        ImmutableConstraint<?> precedenceConstraintB = planningFactory.getConstraintFactory()
                .createPrecedenceConstraint(methodBSubTask1, methodBSubTask2);
        ImmutableTaskNetwork methodBTaskNetwork = planningFactory.getTaskNetworkFactory().createTaskNetworkBuilder()
                .addTask(methodBSubTask1)
                .addTask(methodBSubTask2)
                .addConstraint(precedenceConstraintB)
                .addConstraint(beforeConstraintNotHaveX)
                .addConstraint(beforeConstraintHaveY)
                .build();
        
        ImmutableMethod methodA = planningFactory.getDomainBuilderFactory().createMethodBuilder()
                .setName("swap")
                .setTask(methodATask)
                .setTaskNetwork(methodATaskNetwork)
                .build();
        ImmutableMethod methodB = planningFactory.getDomainBuilderFactory().createMethodBuilder()
                .setName("swap")
                .setTask(methodBTask)
                .setTaskNetwork(methodBTaskNetwork)
                .build();
        
        return planningFactory.getDomainBuilderFactory().createDomainBuilder()
                .addOperator(operatorA)
                .addOperator(operatorB)
                .addMethod(methodA)
                .addMethod(methodB)
                .build();
	}
	
	/**
	 * Utility method to get a sentence builder.
	 * @return a sentence builder
	 */
	private SentenceBuilder getSentenceBuilder() {
	    return planningFactory.getLogicFactory().sentenceBuilder();
	}
}
