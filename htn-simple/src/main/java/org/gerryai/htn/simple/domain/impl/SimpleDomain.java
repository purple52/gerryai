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

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilder;

/**
 * Simple representation of a domain.
 * Wraps a set of operators and a set of methods to define the domain.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleDomain implements Domain {

	/**
	 * Operators available in this domain.
	 */
	private Set<Operator> operators;
	
	/**
	 * Methods available in this domain.
	 */
	private Set<Method> methods;
	
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
	public final Set<Operator> getOperators() {
		return Collections.unmodifiableSet(operators);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Method> getMethods() {
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
	    private Set<Operator> operators;
	    
	    /**
	     * Set of methods that the domain uses.
	     */
	    private Set<Method> methods;
	    
	    /**
	     * Default constructor.
	     */
	    public Builder() {
	        operators = new HashSet<Operator>();
	        methods = new HashSet<Method>();
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder addOperator(Operator operator) {
	        operators.add(operator);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Builder addMethod(Method method) {
	        methods.add(method);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Domain build() {
	        return new SimpleDomain(this);
	    }

	    /**
	     * @return the operators
	     */
	    public final Set<Operator> getOperators() {
	        return operators;
	    }

	    /**
	     * @return the methods
	     */
	    public final Set<Method> getMethods() {
	        return methods;
	    }

	}
}
