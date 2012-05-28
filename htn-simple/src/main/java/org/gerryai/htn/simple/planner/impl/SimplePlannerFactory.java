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
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.constraint.validation.impl.GenericConstraintValidatorFactory;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.decomposition.impl.SimpleDecompositionService;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.domain.impl.SimpleDomainHelper;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableConstant;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.plan.ImmutableAction;
import org.gerryai.htn.simple.plan.ImmutableActionFactory;
import org.gerryai.htn.simple.plan.ImmutableActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactory;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimplePlanFactory;
import org.gerryai.htn.simple.planner.PlannerFactory;
import org.gerryai.htn.simple.planner.sort.SortService;
import org.gerryai.htn.simple.planner.sort.impl.SimpleSortService;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerFactory implements
		PlannerFactory<ImmutableAction, ImmutableDomain, ImmutableOperator, ImmutableMethod, ImmutableTerm<?>,
		ImmutableTask, ImmutableTaskNetwork, ImmutableConstraint<?>,
		ImmutableCondition<?>, ImmutableVariable<?>, ImmutableConstant<?>> {

	/**
	 * {@inheritDoc}
	 */
	public final SimplePlanner create(ImmutableDomain domain) {
		
		DomainHelper<ImmutableDomain, ImmutableOperator, ImmutableMethod, ImmutableTerm<?>,
				ImmutableTask, ImmutableTaskNetwork,
				ImmutableConstraint<?>, ImmutableCondition<?>, ImmutableVariable<?>> domainHelper
				= new SimpleDomainHelper(domain);
		
		ImmutableActionFactoryHelper actionFactoryHelper = new SimpleActionFactoryHelper(domainHelper);
		ImmutableActionFactory actionFactory = new SimpleActionFactory(actionFactoryHelper);
		SimplePlanFactory planFactory = new SimplePlanFactory();
		
		aima.core.logic.fol.Unifier unifier = new aima.core.logic.fol.Unifier();
		AIMAConverter<ImmutableTerm<?>, ImmutableVariable<?>, ImmutableTask> converter = new AIMAConverterImpl();
		ConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask,
		ImmutableCondition<?>> constraintValidatorFactory
				= new GenericConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>>();
		AIMAUnificationService<ImmutableOperator, ImmutableMethod, 
		        ImmutableCondition<?>, ImmutableVariable<?>> unificationService =
		        new AIMAUnificationService<ImmutableOperator, ImmutableMethod,
		        ImmutableCondition<?>, ImmutableVariable<?>>(unifier, converter);
		
		DecompositionService<ImmutableMethod, ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
		        ImmutableConstraint<?>, ImmutableSubstitution> decompositionService =
				new SimpleDecompositionService(constraintValidatorFactory);
		
		SortService<ImmutableTerm<?>, ImmutableTask, ImmutableConstraint<?>> sortService =
		        new SimpleSortService();
		
		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(actionFactory,
		        planFactory, decompositionService, unificationService, sortService);
		
		SimplePlanner planner = new SimplePlanner(domainHelper, plannerHelper);
		
		return planner;	
	}

}
