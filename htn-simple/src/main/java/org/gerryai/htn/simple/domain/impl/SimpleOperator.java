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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.domain.ImmutableOperatorBuilder;
import org.gerryai.logic.Variable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperator implements Operator {

	/**
	 * Name of this operator.
	 */
	private String name;
	
	/**
	 * Arguments for this operator.
	 */
	private List<Variable> arguments;
	
	/**
	 * Preconditions for this operator.
	 */
	private Set<Condition> preconditions;
	
	/**
	 * Effects of this operator.
	 */
	private Set<Effect> effects;
	
	/**
	 * Constructor for a simple operator.
	 * @param builder the builder to build the operator
	 */
	protected SimpleOperator(Builder builder) {
		this.name = builder.name;
		this.arguments = builder.arguments;
		this.preconditions = builder.preconditions;
		this.effects = builder.effects;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<Variable> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Condition> getPreconditions() {
		return Collections.unmodifiableSet(preconditions);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Effect> getEffects() {
		return Collections.unmodifiableSet(effects);
	}

	/**
	 * Builder class for a SimpleOperator.
	 */
	public static class Builder implements ImmutableOperatorBuilder {

	    /**
	     * Name of the operator.
	     */
	    private String name;
	    
	    /**
	     * List of arguments the operator takes.
	     */
	    private List<Variable> arguments;
	    
	    /**
	     * Set of preconditions that must hold for the operator to be valid.
	     */
	    private Set<Condition> preconditions;
	    
	    /**
	     * Set of effects that the operator has.
	     */
	    private Set<Effect> effects;
	    
	    /**
	     * Default constructor.
	     */
	    protected Builder() {
	        arguments = new ArrayList<Variable>();
	        preconditions = new HashSet<Condition>();
	        effects = new HashSet<Effect>();
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder setName(String name) {
	        this.name = name;
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addArgument(Variable argument) {
	        arguments.add(argument);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addArguments(List<Variable> argument) {
	        arguments.addAll(argument);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addPrecondition(Condition condition) {
	        preconditions.add(condition);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addPreconditions(Set<Condition> conditions) {
	        preconditions.addAll(conditions);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addEffect(Effect effect) {
	        effects.add(effect);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addEffects(Set<Effect> effects) {
	        this.effects.addAll(effects);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final Operator build() {
	        return new SimpleOperator(this);
	    }
	}
	
	@Override
	public final String toString() {
	    return new StringBuilder()
	        .append(name)
	        .append(arguments.toString())
	        .append(',')
	        .append(preconditions.toString())
	        .append(',')
	        .append(effects.toString())
	        .toString();
	}
}
