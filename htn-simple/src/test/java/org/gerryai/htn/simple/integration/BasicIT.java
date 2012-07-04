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

import static org.junit.Assert.*;

import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableEffect;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.LogicFactory;
import org.gerryai.htn.simple.plan.ImmutablePlan;
import org.gerryai.htn.simple.planner.ImmutablePlanningFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningFactory;
import org.gerryai.htn.simple.problem.ImmutableProblem;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Predicate;
import org.gerryai.logic.Variable;
import org.gerryai.logic.builder.SentenceBuilder;
import org.junit.Ignore;
import org.junit.Test;

/**
 * End-to-end integration test of simple implementation using the JSHOP basic example.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class BasicIT {

	//@Ignore
	@Test
	public void testKiwiBanjo() throws PlanNotFound, InvalidConstraint {
		
	    ImmutablePlanningFactory planningFactory = new SimplePlanningFactory();
		
	    ImmutableDomain domain = createDomain(planningFactory);
	    
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
		
		
		ImmutablePlan plan = planningFactory.getPlanningService().solve(problem);
		
		assertEquals(2,plan.getActions().size());
		assertEquals("drop", plan.getActions().get(0).getOperator().getName());
		assertEquals(plan.getActions().get(0).getBindings().size(),1);
		assertEquals(plan.getActions().get(0).getBindings()
				.get(plan.getActions().get(0).getOperator().getArguments().get(0)), constantKiwi);
		assertEquals(plan.getActions().get(1).getOperator().getName(),"pickup");
		assertEquals(plan.getActions().get(1).getBindings().size(),1);
		assertEquals(plan.getActions().get(1).getBindings()
				.get(plan.getActions().get(1).getOperator().getArguments().get(0)), constantBanjo);

	}
	
	//@Ignore
    @Test
    public void testBanjoKiwi() throws PlanNotFound, InvalidConstraint {
        
        ImmutablePlanningFactory planningFactory = new SimplePlanningFactory();
        
        ImmutableDomain domain = createDomain(planningFactory);
        
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
        ImmutableState state = planningFactory.getStateService().createStateBuilder()
                .build();
                
        // Create the problem by putting together the domain and network to be solved;
        ImmutableProblem problem = planningFactory.getProblemBuilderFactory().createProblemBuilder()
                .setState(state)
                .setDomain(domain)
                .setTaskNetwork(taskNetwork)
                .build();
        
        
        ImmutablePlan plan = planningFactory.getPlanningService().solve(problem);
        
        assertEquals(2,plan.getActions().size());
        assertEquals("drop", plan.getActions().get(0).getOperator().getName());
        assertEquals(plan.getActions().get(0).getBindings().size(),1);
        assertEquals(plan.getActions().get(0).getBindings()
                .get(plan.getActions().get(0).getOperator().getArguments().get(0)), constantBanjo);
        assertEquals(plan.getActions().get(1).getOperator().getName(),"pickup");
        assertEquals(plan.getActions().get(1).getBindings().size(),1);
        assertEquals(plan.getActions().get(1).getBindings()
                .get(plan.getActions().get(1).getOperator().getArguments().get(0)), constantKiwi);

    }
    
	ImmutableDomain createDomain(ImmutablePlanningFactory planningFactory) throws PlanNotFound, InvalidConstraint {
	    
	    LogicFactory logicFactory = planningFactory.getLogicFactory();
	    SentenceBuilder builder = logicFactory.sentenceBuilder();
        Variable variableA = planningFactory.getLogicFactory().createVariable("a");
        ImmutableEffect effectA = planningFactory.getDomainBuilderFactory().createEffectBuilder()
                .setSentence(builder.predicate("have")
                        .addTerm(variableA)
                        .build())
                .build();
        // TODO: Should be a NOT
        ImmutableEffect effectB = planningFactory.getDomainBuilderFactory().createEffectBuilder()
                .setSentence(builder.negate(
                        builder.predicate("have")
                            .addTerm(variableA)
                            .build()))
                .build();
        Predicate PredicateA = planningFactory.getLogicFactory().createPredicate("have", variableA);
        ImmutableCondition conditionA = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(PredicateA)
                .build();
        //TODO: Should be NOT!
        Predicate PredicateB = planningFactory.getLogicFactory().createPredicate("have", variableA);
        ImmutableCondition conditionB = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(PredicateB)
                .build();
        
        ImmutableOperator operatorA = planningFactory.getDomainBuilderFactory().createOperatorBuilder()
                .setName("pickup")
                .addArgument(variableA)
                .addEffect(effectA)
                .addPrecondition(conditionA)
                .build();
        ImmutableOperator operatorB = planningFactory.getDomainBuilderFactory().createOperatorBuilder()
                .setName("drop")
                .addArgument(variableA)
                .addEffect(effectB)
                .addPrecondition(conditionB)
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
        Predicate beforePredicateA = planningFactory.getLogicFactory().createPredicate("have", variableX);
        ImmutableCondition beforeConditionA = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(beforePredicateA)
                .build();
        ImmutableConstraint<?> beforeConstraintA = planningFactory.getConstraintFactory().createBeforeConstraint(methodASubTask1, beforeConditionA);
        ImmutableConstraint<?> precedenceConstraintA = planningFactory.getConstraintFactory().createPrecedenceConstraint(methodASubTask1, methodASubTask2);
        ImmutableTaskNetwork methodATaskNetwork = planningFactory.getTaskNetworkFactory().createTaskNetworkBuilder()
                .addTask(methodASubTask1)
                .addTask(methodASubTask2)
                .addConstraint(precedenceConstraintA)
                .addConstraint(beforeConstraintA)
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
        //TODO: SHould be NOT!
        Predicate beforePredicateB = planningFactory.getLogicFactory().createPredicate("have", variableX);
        ImmutableCondition beforeConditionB = planningFactory.getDomainBuilderFactory().createConditionBuilder()
                .setSentence(beforePredicateB)
                .build();
        ImmutableConstraint<?> beforeConstraintB = planningFactory.getConstraintFactory().createBeforeConstraint(methodBSubTask1, beforeConditionB);
        ImmutableConstraint<?> precedenceConstraintB = planningFactory.getConstraintFactory().createPrecedenceConstraint(methodBSubTask1, methodBSubTask2);
        ImmutableTaskNetwork methodBTaskNetwork = planningFactory.getTaskNetworkFactory().createTaskNetworkBuilder()
                .addTask(methodBSubTask1)
                .addTask(methodBSubTask2)
                .addConstraint(precedenceConstraintB)
                .addConstraint(beforeConstraintB)
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

}
