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

import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilder;
import org.gerryai.htn.simple.problem.ImmutableProblem;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Variable;
import org.junit.Test;

/**
 * End-to-end integration test of simple implementation using the JSHOP basic example.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class BasicIT extends BaseIT {
    
	/**
	 * Test basic domain with basic problem.
	 * @throws PlanNotFound only if the test fails
	 * @throws InvalidConstraint only if the test fails
	 */
	@Test
	public final void testKiwiBanjo() throws PlanNotFound, InvalidConstraint {
	    
		// Build the task network to be solved
		Constant constantKiwi = getPlanningFactory().getLogicFactory().createConstant("kiwi");
		Constant constantBanjo = getPlanningFactory().getLogicFactory().createConstant("banjo");
		Task task = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
				.setName("swap")
				.addArgument(constantKiwi)
				.addArgument(constantBanjo)
				.setIsPrimitive(false)
				.build();
		TaskNetwork taskNetwork = getPlanningFactory().getTaskNetworkFactory().createTaskNetworkBuilder()
				.addTask(task)
				.build();
		
		// Create the initial state
		Effect effect = getPlanningFactory().getDomainBuilderFactory().createEffectBuilder()
		        .setSentence(getPlanningFactory().getLogicFactory().sentenceBuilder()
		                .predicate("have")
                        .addTerm(constantKiwi)
                        .build())
                .build();
		ImmutableState state = getPlanningFactory().getStateService().createStateBuilder()
		        .tell(effect)
		        .build();
		        
		// Create the problem by putting together the domain and network to be solved;
		ImmutableProblem problem = getPlanningFactory().getProblemBuilderFactory().createProblemBuilder()
		        .setState(state)
		        .setDomain(getDomain())
		        .setTaskNetwork(taskNetwork)
		        .build();
		
		// Solve the problem
		Plan plan = getPlanningFactory().getPlanningService().solve(problem);
		
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
        
        // Build the task network to be solved
        Constant constantKiwi = getPlanningFactory().getLogicFactory().createConstant("kiwi");
        Constant constantBanjo = getPlanningFactory().getLogicFactory().createConstant("banjo");
        Task task = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
                .setName("swap")
                .addArgument(constantBanjo)
                .addArgument(constantKiwi)
                .setIsPrimitive(false)
                .build();
        TaskNetwork taskNetwork = getPlanningFactory().getTaskNetworkFactory().createTaskNetworkBuilder()
                .addTask(task)
                .build();
        
        // Create the initial state
        Effect effect = getPlanningFactory().getDomainBuilderFactory().createEffectBuilder()
                .setSentence(getPlanningFactory().getLogicFactory().sentenceBuilder()
                        .predicate("have")
                        .addTerm(constantBanjo)
                        .build())
                .build();
        ImmutableState state = getPlanningFactory().getStateService().createStateBuilder()
                .tell(effect)
                .build();
                
        // Create the problem by putting together the domain and network to be solved;
        ImmutableProblem problem = getPlanningFactory().getProblemBuilderFactory().createProblemBuilder()
                .setState(state)
                .setDomain(getDomain())
                .setTaskNetwork(taskNetwork)
                .build();
        
        // Solve the problem
        Plan plan = getPlanningFactory().getPlanningService().solve(problem);
        
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
    
    @Override
	final Domain createDomain() throws PlanNotFound, InvalidConstraint {
	    
        Variable variableX = getPlanningFactory().getLogicFactory().createVariable("x");
        Variable variableY = getPlanningFactory().getLogicFactory().createVariable("y");
        
        Task methodATask = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
                .setName("swap")
                .addArgument(variableX)
                .addArgument(variableY)
                .setIsPrimitive(false)
                .build();
        Task methodASubTask1  = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
                .setName("drop")
                .addArgument(variableX)
                .setIsPrimitive(true)
                .build();
        Task methodASubTask2  = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
                .setName("pickup")
                .addArgument(variableY)
                .setIsPrimitive(true)
                .build();
        Condition beforeConditionHaveX = getPlanningFactory()
        		.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().predicate("have")
                        .addTerm(variableX)
                        .build())
                .build();
        Condition beforeConditionNotHaveY = getPlanningFactory()
        		.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableY)
                            .build()))
                .build();
        BeforeConstraint beforeConstraintHaveX = getPlanningFactory().getConstraintFactory()
                .createBeforeConstraint(methodASubTask1, beforeConditionHaveX);
        BeforeConstraint beforeConstraintNotHaveY = getPlanningFactory().getConstraintFactory()
                .createBeforeConstraint(methodASubTask1, beforeConditionNotHaveY);
        PrecedenceConstraint precedenceConstraintA = getPlanningFactory().getConstraintFactory()
                .createPrecedenceConstraint(methodASubTask1, methodASubTask2);
        TaskNetwork methodATaskNetwork = getPlanningFactory().getTaskNetworkFactory()
        		.createTaskNetworkBuilder()
                .addTask(methodASubTask1)
                .addTask(methodASubTask2)
                .addPrecedenceConstraint(precedenceConstraintA)
                .addBeforeConstraint(beforeConstraintHaveX)
                .addBeforeConstraint(beforeConstraintNotHaveY)
                .build();
        
        Task methodBTask  = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
                .setName("swap")
                .addArgument(variableX)
                .addArgument(variableY)
                .setIsPrimitive(false)
                .build();
        Task methodBSubTask1  = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
                .setName("drop")
                .addArgument(variableY)
                .setIsPrimitive(true)
                .build();
        Task methodBSubTask2  = getPlanningFactory().getTaskNetworkFactory().createTaskBuilder()
                .setName("pickup")
                .addArgument(variableX)
                .setIsPrimitive(true)
                .build();
        Condition beforeConditionNotHaveX = getPlanningFactory()
        		.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableX)
                            .build()))
                .build();
        Condition beforeConditionHaveY = getPlanningFactory()
        		.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().predicate("have")
                        .addTerm(variableY)
                        .build())
                .build();
        BeforeConstraint beforeConstraintNotHaveX = getPlanningFactory().getConstraintFactory()
                .createBeforeConstraint(methodBSubTask1, beforeConditionNotHaveX);
        BeforeConstraint beforeConstraintHaveY = getPlanningFactory().getConstraintFactory()
                .createBeforeConstraint(methodBSubTask1, beforeConditionHaveY);
        PrecedenceConstraint precedenceConstraintB = getPlanningFactory().getConstraintFactory()
                .createPrecedenceConstraint(methodBSubTask1, methodBSubTask2);
        TaskNetwork methodBTaskNetwork = getPlanningFactory()
        		.getTaskNetworkFactory().createTaskNetworkBuilder()
                .addTask(methodBSubTask1)
                .addTask(methodBSubTask2)
                .addPrecedenceConstraint(precedenceConstraintB)
                .addBeforeConstraint(beforeConstraintNotHaveX)
                .addBeforeConstraint(beforeConstraintHaveY)
                .build();
        
        Method methodA = getPlanningFactory().getDomainBuilderFactory().createMethodBuilder()
                .setName("swap")
                .setTask(methodATask)
                .setTaskNetwork(methodATaskNetwork)
                .build();
        Method methodB = getPlanningFactory().getDomainBuilderFactory().createMethodBuilder()
                .setName("swap")
                .setTask(methodBTask)
                .setTaskNetwork(methodBTaskNetwork)
                .build();
        
        return addOperators(getPlanningFactory().getDomainBuilderFactory().createDomainBuilder())
                .addMethod(methodA)
                .addMethod(methodB)
                .build();
	}
	
	/**
	 * Add the operators to the domain being built.
	 * @param builder the domain so far
	 * @return a builder with the operators added
	 */
	private ImmutableDomainBuilder addOperators(ImmutableDomainBuilder builder) {
		
        Variable variableA = getPlanningFactory().getLogicFactory().createVariable("a");
        Effect effectHaveA = getPlanningFactory().getDomainBuilderFactory().createEffectBuilder()
                .setSentence(getSentenceBuilder().predicate("have")
                        .addTerm(variableA)
                        .build())
                .build();
        Effect effectNotHaveA = getPlanningFactory().getDomainBuilderFactory().createEffectBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableA)
                            .build()))
                .build();
        Condition conditionHaveA = getPlanningFactory().getDomainBuilderFactory().createConditionBuilder()
                .setSentence(getSentenceBuilder().predicate("have")
                        .addTerm(variableA)
                        .build())
                .build();
        Condition conditionNotHaveA = getPlanningFactory().getDomainBuilderFactory().createConditionBuilder()
                .setSentence(
                    getSentenceBuilder().negate(
                        getSentenceBuilder().predicate("have")
                            .addTerm(variableA)
                            .build()))
                .build();
        
        Operator operatorA = getPlanningFactory().getDomainBuilderFactory().createOperatorBuilder()
                .setName("pickup")
                .addArgument(variableA)
                .addEffect(effectHaveA)
                .addPrecondition(conditionNotHaveA)
                .build();
        Operator operatorB = getPlanningFactory().getDomainBuilderFactory().createOperatorBuilder()
                .setName("drop")
                .addArgument(variableA)
                .addEffect(effectNotHaveA)
                .addPrecondition(conditionHaveA)
                .build();
        
		return builder
			.addOperator(operatorA)
            .addOperator(operatorB);
	}
}
