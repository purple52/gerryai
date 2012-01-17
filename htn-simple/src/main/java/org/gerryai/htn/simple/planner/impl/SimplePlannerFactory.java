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

import org.gerryai.htn.aima.unification.AIMAUnificationService;
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.simple.decomposition.SimpleDecompositionService;
import org.gerryai.htn.simple.domain.impl.SimpleDomainHelper;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactory;
import org.gerryai.htn.simple.plan.impl.SimpleActionFactoryHelper;
import org.gerryai.htn.simple.plan.impl.SimplePlanFactory;
import org.gerryai.htn.simple.planner.PlannerFactory;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerFactory implements PlannerFactory {

	/**
	 * {@inheritDoc}
	 */
	public final Planner create(Domain domain) {
		
		SimpleDomainHelper domainHelper = new SimpleDomainHelper(domain);
		
		SimpleActionFactoryHelper actionFactoryHelper = new SimpleActionFactoryHelper();
		SimpleActionFactory actionFactory = new SimpleActionFactory(actionFactoryHelper);
		SimplePlanFactory planFactory = new SimplePlanFactory();
		SimpleDecompositionService decompositionService = new SimpleDecompositionService();
		AIMAUnificationService unificationService = new AIMAUnificationService();
		
		SimplePlannerHelper plannerHelper = new SimplePlannerHelper(actionFactory, planFactory,
				decompositionService, unificationService);
		SimplePlanner planner = new SimplePlanner(domainHelper, plannerHelper);
		
		return planner;	
	}

}
