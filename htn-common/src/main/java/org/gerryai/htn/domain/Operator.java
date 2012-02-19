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
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Operator<I extends Condition> {
	
	/**
	 * Get the name of this operator.
	 * @return the name
	 */
	String getName();
	
	/**
	 * Set the name of this operator.
	 * @param name name to set
	 */
	//void setName(String name);
	
	/**
	 * Get the list of arguments for this operator.
	 * @return the list of arguments
	 */
	List<Variable> getArguments();
	
	/**
	 * Set the list of arguments for this operator.
	 * @param arguments list of arguments to set
	 */
	//void setArguments(List<Variable> arguments);
	
	/**
	 * Get the preconditions for this operator.
	 * @return the preconditions
	 */
	Set<I> getPreconditions();
	
	/**
	 * Set the preconditions for this operator.
	 * @param preconditions preconditions to set
	 */
	//void setPreconditions(Set<I> preconditions);
	
	/**
	 * Get the effects of this operator.
	 * @return the effects
	 */
	Set<Effect> getEffects();
	
	/**
	 * Set the effects of this operator.
	 * @param effects effects to set
	 */
	//void setEffects(Set<Effect> effects);

}
