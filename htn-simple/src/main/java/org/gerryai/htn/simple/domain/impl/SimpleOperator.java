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

import java.util.List;
import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Operator;
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
	private Set<Constraint> preconditions;
	
	/**
	 * Effects of this operator.
	 */
	private Set<Effect> effects;
	
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<Variable> getArguments() {
		return arguments;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setArguments(List<Variable> arguments) {
		this.arguments = arguments;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Constraint> getPreconditions() {
		return preconditions;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setPreconditions(Set<Constraint> preconditions) {
		this.preconditions = preconditions;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Effect> getEffects() {
		return effects;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setEffects(Set<Effect> effects) {
		this.effects = effects;
	}

}
