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
package org.gerryai.htn.simple.plan;

import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;

/**
 * Builder interface for immutable plans.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface PlanBuilder {

    /**
     * Add an action to the plan being built.
     * @param action the action to add
     * @return updated builder
     */
    PlanBuilder addAction(Action action);
    
    /**
     * Build the finished plan.
     * @return the plan
     */
    Plan build();
}
