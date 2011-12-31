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

/**
 * Interface that a problem must implement.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface Problem {
	
	/**
	 * Get the state of the problem.
	 * @return the state
	 */
	State getState();
	
	/**
	 * Set the state of the problem.
	 * @param state state to set
	 */
	void setState(State state);
	
	/**
	 * Get the task network for this problem.
	 * @return the task network
	 */
	TaskNetwork getTaskNetwork();
	
	/**
	 * Set the task network for this problem.
	 * @param taskNetwork task network to set
	 */
	void setTaskNetwork(TaskNetwork taskNetwork);
	
	/**
	 * Get the domain for this problem.
	 * @return the domain
	 */
	Domain getDomain();
	
	/**
	 * Set the domain for this problem.
	 * @param domain domain to set
	 */
	void setDomain(Domain domain);

}
