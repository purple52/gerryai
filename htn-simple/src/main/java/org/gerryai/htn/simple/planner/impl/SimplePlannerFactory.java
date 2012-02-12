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

import org.gerryai.htn.aima.AIMAConverter;
import org.gerryai.htn.aima.impl.AIMAConverterImpl;
import org.gerryai.htn.aima.unification.AIMAUnificationService;
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.constraint.validation.impl.GenericConstraintValidatorFactory;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.impl.SimpleDecompositionService;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.impl.SimpleDomainHelper;
import org.gerryai.htn.simple.domain.impl.SimpleMethod;
import org.gerryai.htn.simple.domain.impl.SimpleOperator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.ActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactory;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimplePlanFactory;
import org.gerryai.htn.simple.planner.PlannerFactory;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilderFactory;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetworkBuilderFactory;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerFactory implements
		PlannerFactory<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
		ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition> {

	/**
	 * {@inheritDoc}
	 */
	public final SimplePlanner create(
			Domain<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
			ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition> domain) {
		
		DomainHelper<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>, SimpleCondition> domainHelper
				= new SimpleDomainHelper(domain);
		
		ActionFactoryHelper<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				actionFactoryHelper = new SimpleActionFactoryHelper(domainHelper);
		ActionFactory<SimpleOperator, SubstitutableTerm, SubstitutableTask, SimpleCondition>
				actionFactory = new SimpleActionFactory(actionFactoryHelper);
		
		SimplePlanFactory planFactory = new SimplePlanFactory();
		
		aima.core.logic.fol.Unifier unifier = new aima.core.logic.fol.Unifier();
		AIMAConverter<SubstitutableTerm, SimpleVariable, SubstitutableTask> converter = new AIMAConverterImpl();
		ConstraintValidatorFactory<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> constraintValidatorFactory
				= new GenericConstraintValidatorFactory<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>();
		TaskNetworkBuilderFactory<SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>>
				taskNetworkBuilderFactory =
			new SimpleTaskNetworkBuilderFactory(constraintValidatorFactory);
		AIMAUnificationService<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask,
				SimpleTaskNetwork, ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>,
				SimpleCondition, SimpleVariable> unificationService =
			new AIMAUnificationService<SimpleOperator, SimpleMethod, SubstitutableTerm, SubstitutableTask,
				SimpleTaskNetwork, ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>,
				SimpleCondition, SimpleVariable>(unifier, converter, domainHelper, taskNetworkBuilderFactory);
		
		DecompositionService<SimpleMethod, SubstitutableTerm, SubstitutableTask, SimpleTaskNetwork,
				ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>> decompositionService =
				new SimpleDecompositionService(unificationService);
		
		SimplePlannerHelper plannerHelper =
				new SimplePlannerHelper(actionFactory, planFactory,	decompositionService, unificationService);
		
		SimplePlanner planner = new SimplePlanner(domainHelper, plannerHelper);
		
		return planner;	
	}

}
