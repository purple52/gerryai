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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.simple.domain.OperatorBuilder;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.logic.Variable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperatorBuilder implements OperatorBuilder<SimpleCondition, Effect> {

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
	private Set<SimpleCondition> preconditions;
	
	/**
	 * Set of effects that the operator has.
	 */
	private Set<Effect> effects;
	
	/**
	 * Default constructor.
	 */
	protected SimpleOperatorBuilder() {
		arguments = new ArrayList<Variable>();
		preconditions = new HashSet<SimpleCondition>();
		effects = new HashSet<Effect>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperatorBuilder setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperatorBuilder addArgument(Variable argument) {
		arguments.add(argument);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperatorBuilder addArguments(List<Variable> argument) {
		arguments.addAll(argument);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperatorBuilder addPrecondition(SimpleCondition condition) {
		preconditions.add(condition);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperatorBuilder addPreconditions(Set<SimpleCondition> conditions) {
		preconditions.addAll(conditions);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperatorBuilder addEffect(Effect effect) {
		effects.add(effect);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperatorBuilder addEffects(Set<Effect> effects) {
		this.effects.addAll(effects);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleOperator build() {
		return new SimpleOperator(this);
	}

	/**
	 * @return the name
	 */
	protected final String getName() {
		return name;
	}

	/**
	 * @return the arguments
	 */
	protected final List<Variable> getArguments() {
		return arguments;
	}

	/**
	 * @return the preconditions
	 */
	protected final Set<SimpleCondition> getPreconditions() {
		return preconditions;
	}

	/**
	 * @return the effects
	 */
	protected final Set<Effect> getEffects() {
		return effects;
	}

}
