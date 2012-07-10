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
import org.gerryai.htn.simple.decomposition.impl.SimpleDecompositionService;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableDomainHelper;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.impl.SimpleDomainHelper;
import org.gerryai.htn.simple.plan.ActionFactory;
import org.gerryai.htn.simple.plan.ActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactory;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimplePlanBuilderFactory;
import org.gerryai.htn.simple.planner.ImmutablePlanner;
import org.gerryai.htn.simple.planner.ImmutablePlannerFactory;
import org.gerryai.htn.simple.planner.sort.SortService;
import org.gerryai.htn.simple.planner.sort.impl.SimpleSortService;
import org.gerryai.htn.simple.problem.ImmutableStateService;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;

/**
 * Simple factory for creating planners that generate imutable plans.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlannerFactory implements ImmutablePlannerFactory {
    
    /**
     * The state service.
     */
    private ImmutableStateService stateService;
    
    /**
     * Constructor taking a state service to use.
     * @param stateService the state service to use
     */
    public SimplePlannerFactory(ImmutableStateService stateService) {
        this.stateService = stateService;
    }

	/**
	 * {@inheritDoc}
	 */
	public final ImmutablePlanner create(ImmutableDomain domain) {
		
	    ImmutableDomainHelper domainHelper = new SimpleDomainHelper(domain);
		
		ActionFactoryHelper actionFactoryHelper = new SimpleActionFactoryHelper(domainHelper);
		ActionFactory actionFactory = new SimpleActionFactory(actionFactoryHelper);
		SimplePlanBuilderFactory planFactory = new SimplePlanBuilderFactory();
		
		aima.core.logic.fol.Unifier unifier = new aima.core.logic.fol.Unifier();
		AIMAConverter converter = new AIMAConverterImpl();
		ConstraintValidatorFactory constraintValidatorFactory
				= new GenericConstraintValidatorFactory();
		AIMAUnificationService<ImmutableMethod> unificationService =
		        new AIMAUnificationService<ImmutableMethod>(unifier, converter);
		
		DecompositionService<ImmutableMethod, ImmutableTaskNetwork,
		        ImmutableConstraint<?>> decompositionService =
				new SimpleDecompositionService(constraintValidatorFactory);
		
		SortService<ImmutableConstraint<?>> sortService =
		        new SimpleSortService();

		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(actionFactory,
		        planFactory, decompositionService, unificationService, sortService,
		        stateService, domainHelper);
		
		SimplePlanner planner = new SimplePlanner(domainHelper, plannerHelper);
		
		return planner;	
	}

}
