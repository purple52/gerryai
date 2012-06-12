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

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface for a factory that creates domain and operator builders.
 * @param <D> the class of domain that the builder will handle
 * @param <O> the class of operator the builder will handle
 * @param <M> the class of method the builder will handle
 * @param <T> the class of logical term the builders will handle
 * @param <K> the class of task the builders can handle
 * @param <N> the class of task network the builders will handle
 * @param <C> the class of constraint the builder will handle
 * @param <I> the class of condition the builder will handle
 * @param <V> type of variable this factory will handle
 * @param <E> the class of effect the builder will handle
 * @param <B> the class of operator builder being used
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface DomainBuilderFactory<
		D extends Domain<O, M, T, K, N, C, I, V>,
		O extends Operator<I, V>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition<T>,
		V extends Variable,
		E extends Effect,
		B extends OperatorBuilder<T, I, V, E, O, B>> {

	/**
	 * Create a domain builder of the required type.
	 * @return the domain builder
	 */
	ImmutableDomainBuilder createDomainBuilder();
	
	/**
	 * Create an operator builder of the required type.
	 * @return the operator builder
	 */
	B createOperatorBuilder();

	/**
	 * Create a method builder of the required type.
	 * @return the method builder
	 */
	MethodBuilder<T, K, N, C, M> createMethodBuilder();
}
