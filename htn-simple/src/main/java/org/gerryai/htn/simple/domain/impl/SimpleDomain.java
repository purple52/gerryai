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
package org.gerryai.htn.simple.domain.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.domain.ImmutableDomainBuilder;
import org.gerryai.htn.simple.domain.ImmutableDomain;
import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;

/**
 * Simple representation of a domain.
 * Wraps a set of operators and a set of methods to define the domain.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleDomain implements ImmutableDomain {

	/**
	 * Operators available in this domain.
	 */
	private Set<ImmutableOperator> operators;
	
	/**
	 * Methods available in this domain.
	 */
	private Set<ImmutableMethod> methods;
	
	/**
	 * Constructor for a simple domain.
	 * @param builder the builder to build this domain from
	 */
	protected SimpleDomain(ImmutableDomainBuilder builder) {
		operators = builder.getOperators();
		methods = builder.getMethods();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableOperator> getOperators() {
		return Collections.unmodifiableSet(operators);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableMethod> getMethods() {
		return Collections.unmodifiableSet(methods);
	}

	/**
	 * Builder class for SimpleDomains.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
	public static class Builder implements ImmutableDomainBuilder {

	    /**
	     * Set of operators the domain uses.
	     */
	    private Set<ImmutableOperator> operators;
	    
	    /**
	     * Set of methods that the domain uses.
	     */
	    private Set<ImmutableMethod> methods;
	    
	    /**
	     * Default constructor.
	     */
	    protected Builder() {
	        operators = new HashSet<ImmutableOperator>();
	        methods = new HashSet<ImmutableMethod>();
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder addOperator(ImmutableOperator operator) {
	        operators.add(operator);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder addMethod(ImmutableMethod method) {
	        methods.add(method);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final SimpleDomain build() {
	        return new SimpleDomain(this);
	    }

	    /**
	     * @return the operators
	     */
	    public final Set<ImmutableOperator> getOperators() {
	        return operators;
	    }

	    /**
	     * @return the effects
	     */
	    public final Set<ImmutableMethod> getMethods() {
	        return methods;
	    }

	}
}
