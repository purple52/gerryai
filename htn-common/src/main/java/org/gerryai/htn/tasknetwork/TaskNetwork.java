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

import java.util.Set;

import org.gerryai.logic.Constraint;

/**
 * Interface that a task network must implement.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface TaskNetwork {

	/**
	 * Get the tasks that make up this network.
	 * @return the tasks
	 */
	Set<Task> getTasks();
	
	/**
	 * Set the tasks that make up this network.
	 * @param tasks tasks to set
	 */
	void setTasks(Set<Task> tasks);
	
	/**
	 * Get the constraints for this network.
	 * @return the constraints
	 */
	Set<Constraint> getConstraints();
	
	/**
	 * Set the constraints for this network.
	 * @param constraints constraints to set
	 */
	void setConstraints(Set<Constraint> constraints);
	
	/**
	 * Check if the task network is primitive by examining its tasks.
	 * @return whether the task network is primitive
	 */
	boolean isPrimitive();
}