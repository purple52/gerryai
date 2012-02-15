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
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.simple.constraint.validation.impl.GenericConstraintValidatorFactory;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.domain.impl.SimpleDomainBuilderFactory;
import org.gerryai.htn.simple.domain.impl.SimpleDomainHelper;
import org.gerryai.htn.simple.domain.impl.SimpleMethod;
import org.gerryai.htn.simple.domain.impl.SimpleOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.htn.simple.logic.impl.SimpleConstant;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.planner.impl.SimplePlannerFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningService;
import org.gerryai.htn.simple.problem.impl.SimpleProblem;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetworkBuilderFactory;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class BasicIT {

	@Ignore
	@Test
	public void test() throws PlanNotFound {
		
		SimpleDomainBuilderFactory domainBuilderFactory
				= new SimpleDomainBuilderFactory();
		GenericConstraintValidatorFactory<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> constraintValidatorFactory
				= new GenericConstraintValidatorFactory<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>();
		SimpleTaskNetworkBuilderFactory taskNetworkBuilderFactory
				= new SimpleTaskNetworkBuilderFactory(constraintValidatorFactory);
		
		SimplePlannerFactory plannerFactory = new SimplePlannerFactory();
		SimplePlanningService planningService = new SimplePlanningService(plannerFactory);

		SimpleVariable variableA = new SimpleVariable("a");
		SubstitutableOperator operatorA = domainBuilderFactory.createOperatorBuilder()
				.setName("pickup")
				.addArgument(variableA)
				.build();
		SubstitutableOperator operatorB = domainBuilderFactory.createOperatorBuilder()
				.setName("drop")
				.addArgument(variableA)
				.build();
		
		Domain domain = domainBuilderFactory.createDomainBuilder()
				.addOperator(operatorA)
				.addOperator(operatorB)
				.build();
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(domain);
		
		SimpleVariable variableX = new SimpleVariable("x");
		SimpleVariable variableY = new SimpleVariable("y");
		SubstitutableTask methodATask  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("swap")
				.addArgument(variableX)
				.addArgument(variableY)
				.setIsPrimitive(false)
				.build();
		SubstitutableTask methodBTask  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("swap")
				.addArgument(variableX)
				.addArgument(variableY)
				.setIsPrimitive(false)
				.build();
		SubstitutableTask methodASubTask1  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("drop")
				.addArgument(variableX)
				.setIsPrimitive(true)
				.build();
		SubstitutableTask methodASubTask2  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("pickup")
				.addArgument(variableY)
				.setIsPrimitive(true)
				.build();
		SubstitutableTask methodBSubTask1  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("drop")
				.addArgument(variableY)
				.setIsPrimitive(true)
				.build();
		SubstitutableTask methodBSubTask2  = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("pickup")
				.addArgument(variableX)
				.setIsPrimitive(true)
				.build();
		SubstitutableTaskNetwork methodATaskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
				.addTask(methodASubTask1)
				.addTask(methodASubTask2)
				.build();
		SubstitutableTaskNetwork methodBTaskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
				.addTask(methodBSubTask1)
				.addTask(methodBSubTask2)
				.build();
		
		SimpleMethod methodA = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodATask)
				.setTaskNetwork(methodATaskNetwork)
				.build();
		SimpleMethod methodB = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodBTask)
				.setTaskNetwork(methodBTaskNetwork)
				.build();
		domain.getMethods().add(methodA);
		domain.getMethods().add(methodB);
		
		
		SimpleConstant constantKiwi = new SimpleConstant("kiwi");
		SimpleConstant constantBanjo = new SimpleConstant("banjo");
		SubstitutableTask task = taskNetworkBuilderFactory.createTaskBuilder()
				.setName("swap")
				.addArgument(constantKiwi)
				.addArgument(constantBanjo)
				.setIsPrimitive(false)
				.build();
		
		SubstitutableTaskNetwork taskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
				.addTask(task)
				.build();
		
		Problem problem = new SimpleProblem();
		problem.setDomain(domain);
		problem.setTaskNetwork(taskNetwork);
		
		
		Plan<SubstitutableOperator, SubstitutableCondition> plan = planningService.solve(problem);
		
		assertEquals(plan.getActions().size(),2);
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
