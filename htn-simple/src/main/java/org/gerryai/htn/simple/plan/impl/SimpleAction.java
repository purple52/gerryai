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

import java.util.Collections;
import java.util.Map;

import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.plan.ImmutableAction;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Variable;

/**
 * An immutable implementation of an action that provides a builder for construction.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleAction implements ImmutableAction {

	/**
	 * Operator that this action is a grounded instance of.
	 */
	private ImmutableOperator operator;
	
	/**
	 * Bindings that ground the operator.
	 */
	private Map<Variable, Constant> bindings;
	
	/**
	 * Constructor only called by the builder class.
	 * @param builder the builder
	 */
	protected SimpleAction(Builder builder) {
	    this.operator = builder.getOperator();
	    this.bindings = builder.getBindings();
	}
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableOperator getOperator() {
		return operator;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Map<Variable, Constant> getBindings() {
		return Collections.unmodifiableMap(bindings);
	}
	
	/**
	 * Builder class for SimpleAction objects.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
	public static class Builder {
	    
	    /**
	     * Operator that this action is a grounded instance of.
	     */
	    private ImmutableOperator operator;
	    
	    /**
	     * Bindings that ground the operator.
	     */
	    private Map<Variable, Constant> bindings;
	    
	    /**
	     * Set the operator the action to be built will implement.
	     * @param operator the operator
	     * @return an updated builder
	     */
	    public final Builder setOperator(ImmutableOperator operator) {
	        this.operator = operator;
	        return this;
	    }

	    /**
	     * Set the bindings the action to be built will be grounded with.
	     * @param bindings the bindings
	     * @return an updated builder
	     */
	    public final Builder setBindings(Map<Variable, Constant> bindings) {
	        this.bindings = bindings;
	        return this;
	    }
	    
	    /**
	     * Get the operator the action to be built will implement.
	     * @return the operator
	     */
	    protected final ImmutableOperator getOperator() {
	        return operator;
	    }

	    /**
	     * Get the bindings that the action to be built will be grounded with.
	     * @return the bindings
	     */
	    protected final Map<Variable, Constant> getBindings() {
	        return bindings;
	    }
	    
	    /**
	     * Build the action.
	     * @return the action
	     */
	    public final ImmutableAction build() {
	        return new SimpleAction(this);
	    }
	}
}
