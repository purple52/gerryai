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
import org.gerryai.htn.simple.constraint.impl.SimpleConstraintFactory;
import org.gerryai.htn.simple.constraint.validation.impl.GenericConstraintValidatorFactory;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilderFactory;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.domain.impl.SimpleDomainBuilderFactory;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.logic.impl.SimpleConstant;
import org.gerryai.htn.simple.logic.impl.SimpleLogicFactory;
import org.gerryai.htn.simple.plan.ImmutablePlan;
import org.gerryai.htn.simple.planner.ImmutablePlannerFactory;
import org.gerryai.htn.simple.planner.ImmutablePlanningService;
import org.gerryai.htn.simple.planner.impl.SimplePlannerFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningService;
import org.gerryai.htn.simple.problem.ImmutableProblem;
import org.gerryai.htn.simple.problem.ImmutableProblemBuilderFactory;
import org.gerryai.htn.simple.problem.impl.SimpleProblemBuilderFactory;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetworkFactory;
import org.junit.Ignore;
import org.junit.Test;

/**
 * End-to-end integration test of simple implementation using the JSHOP basic example.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class BasicIT {

	@Ignore
	@Test
	public void test() throws PlanNotFound, InvalidConstraint {
		
	    ImmutableLogicFactory logicFactory = new SimpleLogicFactory();
		ImmutableDomainBuilderFactory domainBuilderFactory = new SimpleDomainBuilderFactory();
		ImmutableProblemBuilderFactory problemBuilderFactory = new SimpleProblemBuilderFactory();
        
		GenericConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>> constraintValidatorFactory
				= new GenericConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>>();
		SimpleTaskNetworkFactory taskNetworkBuilderFactory
				= new SimpleTaskNetworkFactory(constraintValidatorFactory, logicFactory);
		
		ImmutablePlannerFactory plannerFactory = new SimplePlannerFactory();
		ImmutablePlanningService planningService = new SimplePlanningService(plannerFactory);
		SimpleConstraintFactory constraintFactory = new SimpleConstraintFactory();
		
		ImmutableVariable<?> variableA = logicFactory.createVariable("a");
		ImmutableOperator operatorA = domainBuilderFactory.createOperatorBuilder()
				.setName("pickup")
				.addArgument(variableA)
				.build();
		ImmutableOperator operatorB = domainBuilderFactory.createOperatorBuilder()
				.setName("drop")
				.addArgument(variableA)
				.build();
		
		ImmutableVariable<?> variableX = logicFactory.createVariable("x");
		ImmutableVariable<?> variableY = logicFactory.createVariable("y");
        
		ImmutableTask methodATask = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("swap")
				.addArgument(variableX)
				.addArgument(variableY)
				.setIsPrimitive(false)
				.build();
		ImmutableTask methodASubTask1  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("drop")
				.addArgument(variableX)
				.setIsPrimitive(true)
				.build();
		ImmutableTask methodASubTask2  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("pickup")
				.addArgument(variableY)
				.setIsPrimitive(true)
				.build();
		ImmutableCondition<?> beforeConditionA = logicFactory.createCondition("have", variableX);
		ImmutableConstraint<?> beforeConstraintA = constraintFactory.createBeforeConstraint(methodASubTask1, beforeConditionA);
		ImmutableConstraint<?> precedenceConstraintA = constraintFactory.createPrecedenceConstraint(methodASubTask1, methodASubTask2);
        ImmutableTaskNetwork methodATaskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
                .addTask(methodASubTask1)
                .addTask(methodASubTask2)
                .addConstraint(precedenceConstraintA)
                .addConstraint(beforeConstraintA)
                .build();
        
        ImmutableTask methodBTask  = taskNetworkBuilderFactory.createTaskBuilder()
                .setName("swap")
                .addArgument(variableX)
                .addArgument(variableY)
                .setIsPrimitive(false)
                .build();
        ImmutableTask methodBSubTask1  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("drop")
				.addArgument(variableY)
				.setIsPrimitive(true)
				.build();
		ImmutableTask methodBSubTask2  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("pickup")
				.addArgument(variableX)
				.setIsPrimitive(true)
				.build();
		ImmutableCondition<?> beforeConditionB = logicFactory.createCondition("have", variableY);
		ImmutableConstraint<?> beforeConstraintB = constraintFactory.createBeforeConstraint(methodBSubTask1, beforeConditionB);
		ImmutableConstraint<?> precedenceConstraintB = constraintFactory.createPrecedenceConstraint(methodBSubTask1, methodBSubTask2);
		ImmutableTaskNetwork methodBTaskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
				.addTask(methodBSubTask1)
				.addTask(methodBSubTask2)
				.addConstraint(precedenceConstraintB)
				.addConstraint(beforeConstraintB)
                .build();
		
		ImmutableMethod methodA = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodATask)
				.setTaskNetwork(methodATaskNetwork)
				.build();
		ImmutableMethod methodB = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodBTask)
				.setTaskNetwork(methodBTaskNetwork)
				.build();
		
	    ImmutableDomain domain =  domainBuilderFactory.createDomainBuilder()
	            .addOperator(operatorA)
	            .addOperator(operatorB)
	            .addMethod(methodA)
	            .addMethod(methodB)
	            .build();
		
		// Build the task network to be solved
		SimpleConstant constantKiwi = new SimpleConstant("kiwi");
		SimpleConstant constantBanjo = new SimpleConstant("banjo");
		ImmutableTask task = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("swap")
				.addArgument(constantKiwi)
				.addArgument(constantBanjo)
				.setIsPrimitive(false)
				.build();
		ImmutableTaskNetwork taskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
				.addTask(task)
				.build();
		
		// Create the problem by putting together the domain and network to be solved;
		ImmutableProblem problem = problemBuilderFactory.createProblemBuilder()
		        .setDomain(domain)
		        .setTaskNetwork(taskNetwork)
		        .build();
		
		
		ImmutablePlan plan = planningService.solve(problem);
		
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

}
