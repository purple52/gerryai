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

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

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
	 * Get the task for this method.
	 * @return the task
	 */
	Task getTask();
	
	/**
	 * Get the task network for this method.
	 * @return the task network
	 */
	TaskNetwork getTaskNetwork();
	
}
