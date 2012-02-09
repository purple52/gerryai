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
package org.gerryai.htn.tasknetwork;

import java.util.List;

import org.gerryai.logic.Term;

/**
 * Interface that a task must implement.
 * @param <T> type of logical term this task works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Task<T extends Term> {

	/**
	 * Get this task's name.
	 * @return the symbol
	 */
	String getName();

	/**
	 * Set this task's name.
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Get this task's arguments.
	 * @return the list of arguments
	 */
	List<T> getArguments();
	
	/**
	 * Set this task's arguments.
	 * @param arguments arguments to set
	 */
	void setArguments(List<T> arguments);
	
	/**
	 * Determine whether this task is a primitive.
	 * @return true if task is a primitive
	 */
	boolean isPrimitive();
}
