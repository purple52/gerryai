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
 * Interface that a method must implement.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Method {

	/**
	 * Get the name of this method.
	 * @return the name
	 */
	String getName();
	
	/**
	 * Set the name of this method.
	 * @param name name to set
	 */
	void setName(String name);
	
	/**
	 * Get the task for this method.
	 * @return the task
	 */
	Task getTask();
	
	/**
	 * Set the task for this method.
	 * @param task task to set
	 */
	void setTask(Task task);
	
	/**
	 * Get the subtasks for this method.
	 * @return the sub tasks
	 */
	List<Task> getSubTasks();
	
	/**
	 * Set the sub tasks for this method.
	 * @param subTasks sub tasks to set
	 */
	void setSubTasks(List<Task> subTasks);
	
	/**
	 * Get the constraint for this method.
	 * @return the constraint
	 */
	Constraint getConstraint();
	
	/**
	 * Set the constraint for this method.
	 * @param constraint constraint to set
	 */
	void setConstraint(Constraint constraint);
}
