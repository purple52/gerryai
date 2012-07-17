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
package org.gerryai.htn.simple.integration;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.planner.ImmutablePlanningFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningFactory;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.logic.builder.SentenceBuilder;
import org.junit.Before;

/**
 * Base class for methods shared by integration tests.
 * @author David Edwards <david@more.fool.me.uk>
 */
public abstract class BaseIT {

	/**
	 * Planning factory used by the test.
	 */
    private ImmutablePlanningFactory planningFactory;
    
	/**
     * Domain being used by the test.
     */
    private Domain domain;
    
    /**
     * Get the planning factory being used in this test.
     * @return the planning factory
     */
    public final ImmutablePlanningFactory getPlanningFactory() {
		return planningFactory;
	}

    /**
     * Get the domain being used by this test.
     * @return the domain
     */
	public final Domain getDomain() {
		return domain;
	}

    /**
     * Initialise the planning factory and domain.
     * @throws InvalidConstraint  only if test is broken
     * @throws PlanNotFound only if test is broken
     */
    @Before
    public final void init() throws PlanNotFound, InvalidConstraint {
        planningFactory = new SimplePlanningFactory();
        domain = createDomain();
    }
    
	/**
	 * Utility method to get a sentence builder.
	 * @return a sentence builder
	 */
	protected final  SentenceBuilder getSentenceBuilder() {
	    return planningFactory.getLogicFactory().sentenceBuilder();
	}
	
    /**
     * Create the domain being used by this test.
     * @return the domain
     * @throws PlanNotFound only if test is broken
     * @throws InvalidConstraint only if test is broken
     */
	abstract Domain createDomain() throws PlanNotFound, InvalidConstraint;
}
