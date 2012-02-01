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
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.problem.Problem;
import org.gerryai.htn.simple.domain.impl.SimpleDomainBuilderFactory;
import org.gerryai.htn.simple.domain.impl.SimpleDomainHelper;
import org.gerryai.htn.simple.logic.impl.SimpleConstant;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.planner.impl.SimplePlannerFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningService;
import org.gerryai.htn.simple.problem.impl.SimpleProblem;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskBuilder;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetworkBuilderFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Variable;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class BasicIT {

	@Test
	public void test() throws PlanNotFound {
		
		SimpleDomainBuilderFactory domainBuilderFactory = new SimpleDomainBuilderFactory();
		SimpleTaskNetworkBuilderFactory taskNetworkBuilderFactory = new SimpleTaskNetworkBuilderFactory();
		
		SimplePlannerFactory plannerFactory = new SimplePlannerFactory();
		SimplePlanningService planningService = new SimplePlanningService(plannerFactory);
		
		SimpleVariable variableA = new SimpleVariable("a");
		Operator operatorA = domainBuilderFactory.createOperatorBuilder()
				.setName("pickup")
				.addArgument(variableA)
				.build();
		Operator operatorB = domainBuilderFactory.createOperatorBuilder()
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
		Task methodATask  = new SimpleTaskBuilder(domainHelper)
				.setName("swap")
				.addArgument(variableX)
				.addArgument(variableY)
				.build();
		Task methodBTask  = new SimpleTaskBuilder(domainHelper)
				.setName("swap")
				.addArgument(variableX)
				.addArgument(variableY)
				.build();
		Task methodASubTask1  = new SimpleTaskBuilder(domainHelper)
				.setName("drop")
				.addArgument(variableX)
				.build();
		Task methodASubTask2  = new SimpleTaskBuilder(domainHelper)
				.setName("pickup")
				.addArgument(variableY)
				.build();
		Task methodBSubTask1  = new SimpleTaskBuilder(domainHelper)
				.setName("drop")
				.addArgument(variableY)
				.build();
		Task methodBSubTask2  = new SimpleTaskBuilder(domainHelper)
				.setName("pickup")
				.addArgument(variableX)
				.build();
		TaskNetwork methodATaskNetwork = taskNetworkBuilderFactory.create()
				.addTask(methodASubTask1)
				.addTask(methodASubTask2)
				.build();
		TaskNetwork methodBTaskNetwork = taskNetworkBuilderFactory.create()
				.addTask(methodBSubTask1)
				.addTask(methodBSubTask2)
				.build();
		
		Method methodA = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodATask)
				.setTaskNetwork(methodATaskNetwork)
				.build();
		Method methodB = domainBuilderFactory.createMethodBuilder()
				.setName("swap")
				.setTask(methodBTask)
				.setTaskNetwork(methodBTaskNetwork)
				.build();
		domain.getMethods().add(methodA);
		domain.getMethods().add(methodB);
		
		
		SimpleConstant constantKiwi = new SimpleConstant("kiwi");
		SimpleConstant constantBanjo = new SimpleConstant("banjo");
		Task task = new SimpleTaskBuilder(domainHelper)
				.setName("swap")
				.addArgument(constantKiwi)
				.addArgument(constantBanjo)
				.build();
		
		TaskNetwork taskNetwork = taskNetworkBuilderFactory.create()
				.addTask(task)
				.build();
		
		Problem problem = new SimpleProblem();
		problem.setDomain(domain);
		problem.setTaskNetwork(taskNetwork);
		
		/*
		Plan plan = planningService.solve(problem);
		
		assertEquals(plan.getActions().size(),2);
		assertEquals(plan.getActions().get(0).getOperator().getName(),"drop");
		assertEquals(plan.getActions().get(0).getBindings().getMap().size(),1);
		assertEquals(plan.getActions().get(0).getBindings().getMap()
				.get(plan.getActions().get(0).getOperator().getArguments().get(0)), constantKiwi);
		assertEquals(plan.getActions().get(1).getOperator().getName(),"pickup");
		assertEquals(plan.getActions().get(1).getBindings().getMap().size(),1);
		assertEquals(plan.getActions().get(1).getBindings().getMap()
				.get(plan.getActions().get(1).getOperator().getArguments().get(0)), constantBanjo);
		
		*/
	}

}
