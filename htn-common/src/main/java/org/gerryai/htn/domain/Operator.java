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

import java.util.List;
import java.util.Set;

import org.gerryai.logic.Variable;

/**
 * Interface that an operator must implement.
 * @param <I> type of condition this operator takes
 * @param <V> type of variable this operator uses
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Operator<I extends Condition, V extends Variable> {
	
	/**
	 * Get the name of this operator.
	 * @return the name
	 */
	String getName();
	
	/**
	 * Get the list of arguments for this operator.
	 * @return the list of arguments
	 */
	List<V> getArguments();
	
	/**
	 * Get the preconditions for this operator.
	 * @return the preconditions
	 */
	Set<I> getPreconditions();
	
	/**
	 * Get the effects of this operator.
	 * @return the effects
	 */
	Set<Effect> getEffects();

}
