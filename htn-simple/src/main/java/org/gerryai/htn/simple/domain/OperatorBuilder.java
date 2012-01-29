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
package org.gerryai.htn.simple.domain;

import java.util.List;
import java.util.Set;

import org.gerryai.htn.domain.Operator;
import org.gerryai.logic.Variable;

/**
 * Interface for a class that can build operators.
 * @param <C> class of constraint the builder can handle
 * @param <E> class of effect the builder can handle
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface OperatorBuilder<C, E> {

	/**
	 * Set the name of the operator.
	 * @param name the name
	 * @return the updated builder
	 */
	OperatorBuilder<C, E> setName(String name);
	
	/**
	 * Add an argument to the operator.
	 * @param argument the variable to add
	 * @return the updated builder
	 */
	OperatorBuilder<C, E> addArgument(Variable argument);

	/**
	 * Add a list of arguments to the operator.
	 * @param arguments the variables to add
	 * @return the updated builder
	 */
	OperatorBuilder<C, E> addArguments(List<Variable> arguments);

	/**
	 * Add an argument to the operator.
	 * @param constraint the term to add
	 * @return the updated builder
	 */
	OperatorBuilder<C, E> addPrecondition(C constraint);
	
	/**
	 * Add a set of preconditions to the operator.
	 * @param constraints the constraints to add
	 * @return the updated builder
	 */
	OperatorBuilder<C, E> addPreconditions(Set<C> constraints);

	/**
	 * Add an effect to the operator.
	 * @param effect the effect to add
	 * @return the updated builder
	 */
	OperatorBuilder<C, E> addEffect(E effect);
	
	/**
	 * Add a set of effects to the operator.
	 * @param effects the effects to add
	 * @return the updated builder
	 */
	OperatorBuilder<C, E> addEffects(Set<E> effects);
	
	/**
	 * Build the operator.
	 * @return the operator
	 */
	Operator build();
}
