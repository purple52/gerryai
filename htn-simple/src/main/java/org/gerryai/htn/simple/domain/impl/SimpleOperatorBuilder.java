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
import org.gerryai.htn.simple.domain.SubstitutableOperator;
import org.gerryai.htn.simple.domain.SubstitutableOperatorBuilder;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.logic.Variable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperatorBuilder implements SubstitutableOperatorBuilder {

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
	private Set<SubstitutableCondition> preconditions;
	
	/**
	 * Set of effects that the operator has.
	 */
	private Set<Effect> effects;
	
	/**
	 * Default constructor.
	 */
	protected SimpleOperatorBuilder() {
		arguments = new ArrayList<Variable>();
		preconditions = new HashSet<SubstitutableCondition>();
		effects = new HashSet<Effect>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperatorBuilder setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperatorBuilder addArgument(Variable argument) {
		arguments.add(argument);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperatorBuilder addArguments(List<Variable> argument) {
		arguments.addAll(argument);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperatorBuilder addPrecondition(SubstitutableCondition condition) {
		preconditions.add(condition);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperatorBuilder addPreconditions(Set<SubstitutableCondition> conditions) {
		preconditions.addAll(conditions);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperatorBuilder addEffect(Effect effect) {
		effects.add(effect);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperatorBuilder addEffects(Set<Effect> effects) {
		this.effects.addAll(effects);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableOperator build() {
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
	public final List<Variable> getArguments() {
		return arguments;
	}

	/**
	 * @return the preconditions
	 */
	public final Set<SubstitutableCondition> getPreconditions() {
		return preconditions;
	}

	/**
	 * @return the effects
	 */
	public final Set<Effect> getEffects() {
		return effects;
	}

}
