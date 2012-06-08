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

import org.gerryai.htn.simple.constraint.ImmutableConstraintFactory;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilderFactory;
import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.problem.ImmutableProblemBuilderFactory;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkFactory;

/**
 * Interface for a factory that can create everything needed for generating and solving planning problems.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutablePlanningFactory {

    /**
     * Get a factory for creating logical terms.
     * @return the factory
     */
    ImmutableLogicFactory getLogicFactory();
    
    /**
     * Get a factory for generating domain-related builders.
     * @return the factory
     */
    ImmutableDomainBuilderFactory getDomainBuilderFactory();
    
    /**
     * Get a factory for generating problem-related builders.
     * @return the factory
     */
    ImmutableProblemBuilderFactory getProblemBuilderFactory();
    
    /**
     * Get a factory for generating task networks.
     * @return the factory
     */
    ImmutableTaskNetworkFactory getTaskNetworkFactory();
    
    /**
     * Get a factory for generating constraints.
     * @return the factory
     */
    ImmutableConstraintFactory getConstraintFactory();
    
    /**
     * Get a planning service.
     * @return the planning service
     */
    ImmutablePlanningService getPlanningService();
    
}
