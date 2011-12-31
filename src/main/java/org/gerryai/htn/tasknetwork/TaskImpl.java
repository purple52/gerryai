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
 * Basic implementation of the Task interface.
 */
public class TaskImpl implements Task {
	
	/**
	 * Symbol for this task.
	 */
	private TaskSymbol symbol;
	
	/**
	 * Arguments for this task.
	 */
	private List<TaskArgument> arguments;

	/**
	 * {@inheritDoc}
	 */
	public final TaskSymbol getSymbol() {
		return symbol;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void setSymbol(TaskSymbol symbol) {
		this.symbol = symbol;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<TaskArgument> getArguments() {
		return arguments;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void setArguments(List<TaskArgument> arguments) {
		this.arguments = arguments;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean isPrimitive() {
		//TODO: To be implemented
		return true;
	}
}
