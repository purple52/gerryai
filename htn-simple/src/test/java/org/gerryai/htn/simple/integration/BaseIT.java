package org.gerryai.htn.simple.integration;

import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.planner.ImmutablePlanningFactory;
import org.gerryai.htn.simple.planner.impl.SimplePlanningFactory;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
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
    private ImmutableDomain domain;
    
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
	public final ImmutableDomain getDomain() {
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
	abstract ImmutableDomain createDomain() throws PlanNotFound, InvalidConstraint;
}
