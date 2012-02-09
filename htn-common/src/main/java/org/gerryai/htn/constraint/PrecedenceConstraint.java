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
package org.gerryai.htn.constraint;

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Interface for a constraint that dictates what order two tasks must be completed in.
 * @param <T> type of logical term this constraint works with
 * @param <K> type of task this constraint works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface PrecedenceConstraint<T extends Term, K extends Task<T>> extends Constraint<T> {

	/**
	 * Get the task that must come first.
	 * @return the task
	 */
	K getPrecedingTask();

	/**
	 * Set the task that must come first.
	 * @param task the task
	 */
	void setPrecedingTask(K task);
	
	/**
	 * Get the task that must come last.
	 * @return the task
	 */
	K getProcedingTask();
	
	/**
	 * Set the task that must come last.
	 * @param task the task
	 */
	void setProcedingTask(K task);
}
