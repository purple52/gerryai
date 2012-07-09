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

import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.planner.ImmutablePlanner;
import org.gerryai.htn.simple.planner.ImmutablePlannerFactory;
import org.gerryai.htn.simple.planner.ImmutablePlanningService;
import org.gerryai.htn.simple.problem.ImmutableProblem;

/**
 * A simple domain non-specific planning service that uses the simple planner.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlanningService implements ImmutablePlanningService {

	/**
	 * A factory for creating planners.
	 */
	private ImmutablePlannerFactory plannerFactory;
	
	/**
	 * Constructor taking a factory for creating planners.
	 * @param plannerFactory the factory
	 */
	public SimplePlanningService(ImmutablePlannerFactory plannerFactory) {
		this.plannerFactory = plannerFactory;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Plan solve(ImmutableProblem problem) throws PlanNotFound {
		
		// Create a planner that will work in the domain of the given problem
		ImmutablePlanner planner = plannerFactory.create(problem.getDomain());
				
		// Find a plan of the given problem
		return planner.findPlan(problem.getState(), problem.getTaskNetwork());
	}

}
