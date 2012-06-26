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

import org.gerryai.htn.simple.constraint.ImmutableConstraintFactory;
import org.gerryai.htn.simple.constraint.impl.SimpleConstraintFactory;
import org.gerryai.htn.simple.constraint.validation.impl.GenericConstraintValidatorFactory;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilderFactory;
import org.gerryai.htn.simple.domain.impl.SimpleDomainBuilderFactory;
import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleLogicFactory;
import org.gerryai.htn.simple.planner.ImmutablePlannerFactory;
import org.gerryai.htn.simple.planner.ImmutablePlanningFactory;
import org.gerryai.htn.simple.planner.ImmutablePlanningService;
import org.gerryai.htn.simple.problem.ImmutableProblemBuilderFactory;
import org.gerryai.htn.simple.problem.ImmutableStateService;
import org.gerryai.htn.simple.problem.impl.SimpleProblemBuilderFactory;
import org.gerryai.htn.simple.problem.impl.SimpleStateService;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkFactory;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetworkFactory;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlanningFactory implements ImmutablePlanningFactory {

    /**
     * The logic factory to use.
     */
    private ImmutableLogicFactory logicFactory;
    
    /**
     * The domain builder factory to use.
     */
    private ImmutableDomainBuilderFactory domainBuilderFactory;
    
    /**
     * The problem builder factory to use.
     */
    private ImmutableProblemBuilderFactory problemBuilderFactory;
    
    /**
     * The task network factory to use.
     */
    private ImmutableTaskNetworkFactory taskNetworkFactory;
    
    /**
     * The constraint factory to use.
     */
    private ImmutableConstraintFactory constraintFactory;
    
    /**
     * The planner factory to use.
     */
    private ImmutablePlannerFactory plannerFactory;

    /**
     * The state service to use.
     */
    private ImmutableStateService stateService;
    
    /**
     * The planning service to use.
     */
    private ImmutablePlanningService planningService;
    
    /**
     * Constructor to initialise all the factories this factory can produce.
     */
    public SimplePlanningFactory() {
        logicFactory = new SimpleLogicFactory();
        domainBuilderFactory = new SimpleDomainBuilderFactory();
        problemBuilderFactory = new SimpleProblemBuilderFactory();
        
        GenericConstraintValidatorFactory<
                ImmutableTerm<?>,
                ImmutableTask,
                ImmutableCondition> constraintValidatorFactory
            = new GenericConstraintValidatorFactory<
                ImmutableTerm<?>,
                ImmutableTask,
                ImmutableCondition>();
        taskNetworkFactory = new SimpleTaskNetworkFactory(constraintValidatorFactory, logicFactory);

        constraintFactory = new SimpleConstraintFactory();
        stateService = new SimpleStateService();
        plannerFactory = new SimplePlannerFactory(stateService);
        planningService = new SimplePlanningService(plannerFactory);
    }
    
    /**
     * {@inheritDoc}
     */
    public final ImmutableLogicFactory getLogicFactory() {
        return logicFactory;
    }

    /**
     * {@inheritDoc}
     */
    public final ImmutableDomainBuilderFactory getDomainBuilderFactory() {
        return domainBuilderFactory;
    }

    /**
     * {@inheritDoc}
     */
    public final ImmutableProblemBuilderFactory getProblemBuilderFactory() {
        return problemBuilderFactory;
    }

    /**
     * {@inheritDoc}
     */
    public final ImmutableTaskNetworkFactory getTaskNetworkFactory() {
        return taskNetworkFactory;
    }

    /**
     * {@inheritDoc}
     */
    public final ImmutableConstraintFactory getConstraintFactory() {
        return constraintFactory;
    }

    /**
     * {@inheritDoc}
     */
    public final ImmutableStateService getStateService() {
        return stateService;
    }
    /**
     * {@inheritDoc}
     */
    public final ImmutablePlanningService getPlanningService() {
        return planningService;
    }
    
}
