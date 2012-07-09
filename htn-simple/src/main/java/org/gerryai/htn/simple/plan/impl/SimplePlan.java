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
package org.gerryai.htn.simple.plan.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.simple.plan.ImmutablePlanBuilder;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlan implements Plan {

	/**
	 * List of actions that implement this plan.
	 */
	private List<Action> actions;
	
	/**
	 * Constructor.
	 * @param builder the builder to build from
	 */
	protected SimplePlan(Builder builder) {
	    actions = new ArrayList<Action>(builder.getActions());
	}
	/**
	 * {@inheritDoc}
	 */
	public final List<Action> getActions() {
		return Collections.unmodifiableList(actions);
	}
	
	/**
	 * Builder class for SimplePlan objects.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
	public static class Builder implements ImmutablePlanBuilder {
	    
	    /**
	     * The actions to use when building the plan.
	     */
	    private List<Action> actions;
	    
	    /**
	     * Constructor.
	     */
	    protected Builder() {
	        actions = new ArrayList<Action>();
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutablePlanBuilder addAction(Action action) {
	        actions.add(action);
	        return this;
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public final Plan build() {
	        return new SimplePlan(this);
	    }
	    
	    /**
	     * Get the actions added to this builder.
	     * @return the actions
	     */
	    protected final List<Action> getActions() {
	        return Collections.unmodifiableList(actions);
	    }
	}
}
