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
package org.gerryai.htn.domain;

import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Interface that a domain must implement.
 * @param <O> type of operator this domain uses
 * @param <M> type of operator this domain uses
 * @param <T> type of logical term this domain uses
 * @param <K> type of task this domain uses
 * @param <N> type of task network this domain uses
 * @param <C> type of constraint this domain uses
 * @param <I> the class of condition the domain will handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Domain<
		O extends Operator<I>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition> {

	/**
	 * Get the full set of available operators for this domain.
	 * @return the operators
	 */
	Set<O> getOperators();
	
	/**
	 * Set the available operators for this domain.
	 * @param operators the set of operators
	 */
	void setOperators(Set<O> operators);
	
	/**
	 * Get the set of methods available for this domain.
	 * @return the methods
	 */
	Set<M> getMethods();
	
	/**
	 * Set the available methods for this domain.
	 * @param methods the set of methods
	 */
	void setMethods(Set<M> methods);
	
}
