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

/**
 * Interface that a task must implement.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Task {

	/**
	 * Get this task's symbol.
	 * @return the symbol
	 */
	TaskSymbol getSymbol();

	/**
	 * Set this task's symbol.
	 * @param newSymbol the symbol to set
	 */
	void setSymbol(TaskSymbol newSymbol);
	
	/**
	 * Get this task's arguments.
	 * @return the arguments
	 */
	List<TaskArgument> getArguments();
	
	/**
	 * Set this task's arguments.
	 * @param newArguments the arguments to set
	 */
	void setArguments(List<TaskArgument> newArguments);
	
	/**
	 * Determine whether this task is a primitive.
	 * @return true if task is a primitive
	 */
	boolean isPrimitive();
}
