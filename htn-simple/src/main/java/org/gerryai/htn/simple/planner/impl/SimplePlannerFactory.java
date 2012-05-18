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
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.constraint.validation.impl.GenericConstraintValidatorFactory;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.decomposition.impl.SimpleDecompositionService;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.domain.impl.SimpleDomainHelper;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.logic.impl.SimpleLogicFactory;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.ActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactory;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimplePlanFactory;
import org.gerryai.htn.simple.planner.PlannerFactory;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetworkFactory;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerFactory implements
		PlannerFactory<SubstitutableOperator, SubstitutableMethod, ImmutableTerm<?>,
		ImmutableTask, ImmutableTaskNetwork,
		ImmutableConstraint<?>, ImmutableCondition<?>> {

	/**
	 * {@inheritDoc}
	 */
	public final SimplePlanner create(
			Domain<SubstitutableOperator, SubstitutableMethod, ImmutableTerm<?>,
			ImmutableTask, ImmutableTaskNetwork,
			ImmutableConstraint<?>, ImmutableCondition<?>> domain) {
		
		DomainHelper<SubstitutableOperator, SubstitutableMethod, ImmutableTerm<?>,
				ImmutableTask, ImmutableTaskNetwork,
				ImmutableConstraint<?>, ImmutableCondition<?>> domainHelper
				= new SimpleDomainHelper(domain);
		
		ActionFactoryHelper<SubstitutableOperator, ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>>
				actionFactoryHelper = new SimpleActionFactoryHelper(domainHelper);
		ActionFactory<SubstitutableOperator, ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>>
				actionFactory = new SimpleActionFactory(actionFactoryHelper);
		
		SimplePlanFactory planFactory = new SimplePlanFactory();
		
		aima.core.logic.fol.Unifier unifier = new aima.core.logic.fol.Unifier();
		AIMAConverter<ImmutableTerm<?>, ImmutableVariable<?>, ImmutableTask> converter = new AIMAConverterImpl();
		ConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask,
		ImmutableCondition<?>> constraintValidatorFactory
				= new GenericConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>>();
		ImmutableLogicFactory logicFactory = new SimpleLogicFactory();
		SimpleTaskNetworkFactory taskNetworkBuilderFactory =
			new SimpleTaskNetworkFactory(constraintValidatorFactory, logicFactory);
		AIMAUnificationService<SubstitutableOperator, SubstitutableMethod, 
		        ImmutableCondition<?>, ImmutableVariable<?>> unificationService =
		        new AIMAUnificationService<SubstitutableOperator, SubstitutableMethod,
		        ImmutableCondition<?>, ImmutableVariable<?>>(unifier, converter,
		                domainHelper, taskNetworkBuilderFactory);
		
		DecompositionService<SubstitutableMethod, ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
		        ImmutableConstraint<?>, ImmutableSubstitution> decompositionService =
				new SimpleDecompositionService(constraintValidatorFactory);
		
		SimplePlannerHelper plannerHelper =
				new SimplePlannerHelper(actionFactory, planFactory,	decompositionService, unificationService);
		
		SimplePlanner planner = new SimplePlanner(domainHelper, plannerHelper);
		
		return planner;	
	}

}
