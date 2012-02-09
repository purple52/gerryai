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

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Operator;
import org.gerryai.logic.Variable;

/**
 * Interface for a class that can build operators.
 * @param <I> class of condition the builder can handle
 * @param <E> class of effect the builder can handle
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface OperatorBuilder<I extends Condition, E> {

	/**
	 * Set the name of the operator.
	 * @param name the name
	 * @return the updated builder
	 */
	OperatorBuilder<I, E> setName(String name);
	
	/**
	 * Add an argument to the operator.
	 * @param argument the variable to add
	 * @return the updated builder
	 */
	OperatorBuilder<I, E> addArgument(Variable argument);

	/**
	 * Add a list of arguments to the operator.
	 * @param arguments the variables to add
	 * @return the updated builder
	 */
	OperatorBuilder<I, E> addArguments(List<Variable> arguments);

	/**
	 * Add an argument to the operator.
	 * @param condition the condition to add
	 * @return the updated builder
	 */
	OperatorBuilder<I, E> addPrecondition(I condition);
	
	/**
	 * Add a set of preconditions to the operator.
	 * @param conditions the conditions to add
	 * @return the updated builder
	 */
	OperatorBuilder<I, E> addPreconditions(Set<I> conditions);

	/**
	 * Add an effect to the operator.
	 * @param effect the effect to add
	 * @return the updated builder
	 */
	OperatorBuilder<I, E> addEffect(E effect);
	
	/**
	 * Add a set of effects to the operator.
	 * @param effects the effects to add
	 * @return the updated builder
	 */
	OperatorBuilder<I, E> addEffects(Set<E> effects);
	
	/**
	 * Build the operator.
	 * @return the operator
	 */
	Operator<I> build();
}
