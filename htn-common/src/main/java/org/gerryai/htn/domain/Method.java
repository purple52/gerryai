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

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Interface that a method must implement.
 * @param <T> type of logical term this method works with
 * @param <K> the type of task this method works with
 * @param <N> the type of task network this method works with
 * @param <C> the type of constraint this method works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Method<
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>> {

	/**
	 * Get the name of this method.
	 * @return the name
	 */
	String getName();
	
	/**
	 * Set the name of this method.
	 * @param name name to set
	 */
	//void setName(String name);
	
	/**
	 * Get the task for this method.
	 * @return the task
	 */
	K getTask();
	
	/**
	 * Set the task for this method.
	 * @param task task to set
	 */
	//void setTask(K task);
	
	/**
	 * Get the task network for this method.
	 * @return the task network
	 */
	N getTaskNetwork();
	
	/**
	 * Set the task network for this method.
	 * @param taskNetwork task network to set
	 */
	//void setTaskNetwork(N taskNetwork);
	
}
