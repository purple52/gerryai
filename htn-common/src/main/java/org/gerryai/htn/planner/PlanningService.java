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
package org.gerryai.htn.planner;

import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.problem.Problem;

/**
 * Interface for a service that can solve problems.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface PlanningService {
	
	/**
	 * Find a plan that solve the given problem.
	 * @param problem the problem to be solved
	 * @return a solution
	 * @throws PlanNotFound if no plan exists for the given problem
	 */
	Plan solve(Problem problem) throws PlanNotFound;
	
}