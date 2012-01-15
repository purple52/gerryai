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
package org.gerryai.htn.decomposition;

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.unification.Unifier;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface UnificationService {
	
	/**
	 * Given a task and a method, try and find the most general unifier (MGU).
	 * @param task the task to unify
	 * @param method the method to unify with
	 * @return the most general unifier
	 */
	Unifier findUnifier(Task task, Method method) throws UnifierNotFound;
}
