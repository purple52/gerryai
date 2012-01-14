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
import org.gerryai.htn.planner.Planner;
import org.gerryai.htn.planner.PlannerService;
import org.gerryai.htn.problem.Problem;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlannerService implements PlannerService {

	private Planner planner;
	
	public void setPlanner(Planner planner) {
		this.planner = planner;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Plan solve(Problem problem) throws PlanNotFound {
		return planner.findPlan(problem.getState(), problem.getTaskNetwork(), problem.getDomain());

	}

}
