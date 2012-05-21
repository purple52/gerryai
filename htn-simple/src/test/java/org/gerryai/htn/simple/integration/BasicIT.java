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

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.impl.SimpleConstraintFactory;
import org.gerryai.htn.simple.constraint.impl.SimplePrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.impl.GenericConstraintValidatorFactory;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.domain.impl.SimpleDomainBuilderFactory;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleConstant;
import org.gerryai.htn.simple.logic.impl.SimpleLogicFactory;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.planner.impl.SimplePlannerFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningService;
import org.gerryai.htn.simple.problem.impl.SimpleProblem;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetworkFactory;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class BasicIT {

	//@Ignore
	@Test
	public void test() throws PlanNotFound, InvalidConstraint {
		
	    ImmutableLogicFactory logicFactory = new SimpleLogicFactory();
		SimpleDomainBuilderFactory domainBuilderFactory
				= new SimpleDomainBuilderFactory();
		GenericConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>> constraintValidatorFactory
				= new GenericConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>>();
		SimpleTaskNetworkFactory taskNetworkBuilderFactory
				= new SimpleTaskNetworkFactory(constraintValidatorFactory, logicFactory);
		
		SimplePlannerFactory plannerFactory = new SimplePlannerFactory();
		SimplePlanningService planningService = new SimplePlanningService(plannerFactory);
		SimpleConstraintFactory constraintFactory = new SimpleConstraintFactory();
		
		SimpleVariable variableA = new SimpleVariable("a");
		SubstitutableOperator operatorA = domainBuilderFactory.createOperatorBuilder()
				.setName("pickup")
				.addArgument(variableA)
				.build();
		SubstitutableOperator operatorB = domainBuilderFactory.createOperatorBuilder()
				.setName("drop")
				.addArgument(variableA)
				.build();
		
		Domain<SubstitutableOperator, SubstitutableMethod, ImmutableTerm<?>,
		    ImmutableTask, ImmutableTaskNetwork, ImmutableConstraint<?>, 
		    ImmutableCondition<?>> domain = domainBuilderFactory.createDomainBuilder()
				.addOperator(operatorA)
				.addOperator(operatorB)
				.build();
		//SimpleDomainHelper domainHelper = new SimpleDomainHelper(domain);
		
		SimpleVariable variableX = new SimpleVariable("x");
		SimpleVariable variableY = new SimpleVariable("y");
		ImmutableTask methodATask = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("swap")
				.addArgument(variableX)
				.addArgument(variableY)
				.setIsPrimitive(false)
				.build();
		ImmutableTask methodBTask  = taskNetworkBuilderFactory.createTaskBuilder()
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
		SimplePrecedenceConstraint precedenceConstraintA = constraintFactory.createPrecedenceConstraint(methodASubTask1, methodASubTask2);
        ImmutableTaskNetwork methodATaskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
                .addTask(methodASubTask1)
                .addTask(methodASubTask2)
                .addConstraint(precedenceConstraintA)
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
		SimplePrecedenceConstraint precedenceConstraintB = constraintFactory.createPrecedenceConstraint(methodBSubTask1, methodBSubTask2);

		ImmutableTaskNetwork methodBTaskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
				.addTask(methodBSubTask1)
				.addTask(methodBSubTask2)
				.addConstraint(precedenceConstraintB)
				.build();
		
		SubstitutableMethod methodA = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodATask)
				.setTaskNetwork(methodATaskNetwork)
				.build();
		SubstitutableMethod methodB = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodBTask)
				.setTaskNetwork(methodBTaskNetwork)
				.build();
		domain.getMethods().add(methodA);
		domain.getMethods().add(methodB);
		
		
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
		
		SimpleProblem problem = new SimpleProblem();
		problem.setDomain(domain);
		problem.setTaskNetwork(taskNetwork);
		
		
		Plan<SubstitutableOperator, ImmutableCondition<?>> plan = planningService.solve(problem);
		
		assertEquals(2,plan.getActions().size());
		assertEquals("drop", plan.getActions().get(0).getOperator().getName());
		assertEquals(plan.getActions().get(0).getBindings().getMap().size(),1);
		assertEquals(plan.getActions().get(0).getBindings().getMap()
				.get(plan.getActions().get(0).getOperator().getArguments().get(0)), constantKiwi);
		assertEquals(plan.getActions().get(1).getOperator().getName(),"pickup");
		assertEquals(plan.getActions().get(1).getBindings().getMap().size(),1);
		assertEquals(plan.getActions().get(1).getBindings().getMap()
				.get(plan.getActions().get(1).getOperator().getArguments().get(0)), constantBanjo);

	}

}
