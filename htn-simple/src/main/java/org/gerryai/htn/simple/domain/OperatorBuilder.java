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
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Operator;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface for a class that can build operators.
 * @param <T> type of logical term this operator builder can handle
 * @param <I> class of condition the builder can handle
 * @param <V> type of variable this builder can handle
 * @param <E> class of effect the builder can handle
 * @param <O> type of operator the builder creates
 * @param <B> class of builder being advertised
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface OperatorBuilder<
        T extends Term,
        I extends Condition<T>,
        V extends Variable,
        E extends Effect,
        O extends Operator<T, I, V>,
        B extends OperatorBuilder<T, I, V, E, O, B>> {

	/**
	 * Set the name of the operator.
	 * @param name the name
	 * @return the updated builder
	 */
	B setName(String name);
	
	/**
	 * Add an argument to the operator.
	 * @param argument the variable to add
	 * @return the updated builder
	 */
	B addArgument(V argument);

	/**
	 * Add a list of arguments to the operator.
	 * @param arguments the variables to add
	 * @return the updated builder
	 */
	B addArguments(List<V> arguments);

	/**
	 * Add an argument to the operator.
	 * @param condition the condition to add
	 * @return the updated builder
	 */
	B addPrecondition(I condition);
	
	/**
	 * Add a set of preconditions to the operator.
	 * @param conditions the conditions to add
	 * @return the updated builder
	 */
	B addPreconditions(Set<I> conditions);

	/**
	 * Add an effect to the operator.
	 * @param effect the effect to add
	 * @return the updated builder
	 */
	B addEffect(E effect);
	
	/**
	 * Add a set of effects to the operator.
	 * @param effects the effects to add
	 * @return the updated builder
	 */
	B addEffects(Set<E> effects);
	
    /**
     * Get the name of the operator being built.
     * 
     * @return the name
     */
    String getName();
    
    /**
     * Get the arguments for the operator being built.
     * @return the arguments
     */
    List<V> getArguments();
 
    /**
     * Get the preconditions for the operator being built.
     * @return the preconditions
     */
    Set<I> getPreconditions();

    /**
     * Get the effects for the operator being built.
     * @return the effects
     */
    Set<E> getEffects();
    
	/**
	 * Build the operator.
	 * @return the operator
	 */
	O build();
}
