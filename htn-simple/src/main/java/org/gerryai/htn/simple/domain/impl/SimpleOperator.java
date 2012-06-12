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

import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.domain.ImmutableOperatorBuilder;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableVariable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperator implements ImmutableOperator {

	/**
	 * Name of this operator.
	 */
	private String name;
	
	/**
	 * Arguments for this operator.
	 */
	private List<ImmutableVariable<?>> arguments;
	
	/**
	 * Preconditions for this operator.
	 */
	private Set<ImmutableCondition> preconditions;
	
	/**
	 * Effects of this operator.
	 */
	private Set<Effect> effects;
	
	/**
	 * Constructor for a simple operator.
	 * @param builder the builder to build the operator
	 */
	protected SimpleOperator(ImmutableOperatorBuilder builder) {
		this.name = builder.getName();
		this.arguments = builder.getArguments();
		this.preconditions = builder.getPreconditions();
		this.effects = builder.getEffects();
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
	public final List<ImmutableVariable<?>> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableCondition> getPreconditions() {
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
	    private List<ImmutableVariable<?>> arguments;
	    
	    /**
	     * Set of preconditions that must hold for the operator to be valid.
	     */
	    private Set<ImmutableCondition> preconditions;
	    
	    /**
	     * Set of effects that the operator has.
	     */
	    private Set<Effect> effects;
	    
	    /**
	     * Default constructor.
	     */
	    protected Builder() {
	        arguments = new ArrayList<ImmutableVariable<?>>();
	        preconditions = new HashSet<ImmutableCondition>();
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
	    public final ImmutableOperatorBuilder addArgument(ImmutableVariable<?> argument) {
	        arguments.add(argument);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addArguments(List<ImmutableVariable<?>> argument) {
	        arguments.addAll(argument);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addPrecondition(ImmutableCondition condition) {
	        preconditions.add(condition);
	        return this;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public final ImmutableOperatorBuilder addPreconditions(Set<ImmutableCondition> conditions) {
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
	    public final ImmutableOperator build() {
	        return new SimpleOperator(this);
	    }

	    /**
	     * @return the name
	     */
	    public final String getName() {
	        return name;
	    }

	    /**
	     * @return the arguments
	     */
	    public final List<ImmutableVariable<?>> getArguments() {
	        return arguments;
	    }

	    /**
	     * @return the preconditions
	     */
	    public final Set<ImmutableCondition> getPreconditions() {
	        return preconditions;
	    }

	    /**
	     * @return the effects
	     */
	    public final Set<Effect> getEffects() {
	        return effects;
	    }

	}
}
