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
package org.gerryai.htn.simple.planner;

import org.gerryai.htn.planner.PlanningService;
import org.gerryai.htn.simple.problem.ImmutableProblem;
import org.gerryai.htn.simple.problem.ImmutableState;

/**
 * Interface for an immutable domain planning service.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutablePlanningService extends PlanningService<
        ImmutableState,
        ImmutableProblem> {

}
