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
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Interface for a domain builder.
 * @param <D> the type of domain this builder can build
 * @param <O> the type of operator this domain will use
 * @param <M> the type of method this domain will use
 * @param <T> the type of logical term this domain will use
 * @param <K> the type of task this domain builder uses
 * @param <N> the type of task network this domain builder uses
 * @param <C> the type of constraint this domain builder uses
 * @param <I> the class of condition the builder will handle
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface DomainBuilder<
		D extends Domain<O, M, T, K, N, C, I>,
		O extends Operator<I>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition> {
	
	/**
	 * Add an operator to the domain.
	 * @param operator the operator to add
	 * @return the updated builder
	 */
	DomainBuilder<D, O, M, T, K, N, C, I> addOperator(O operator);
	
	/**
	 * Add a method to the domain.
	 * @param method the method to add
	 * @return the updated builder
	 */
	DomainBuilder<D, O, M, T, K, N, C, I> addMethod(M method);

	/**
	 * Build the domain.
	 * @return the domain
	 */
	Domain<O, M, T, K, N, C, I> build();
}
