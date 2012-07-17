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
package org.gerryai.htn.simple.decomposition.impl;

import java.util.Map;

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Simple implementation of a decomposition service, for decomposing a task
 * within a network using a specified method and unifier.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleDecompositionService implements DecompositionService {
	
	/**
	 * {@inheritDoc}
	 */
	public final TaskNetwork decompose(Map<Term, Term> substitution,
			TaskNetwork taskNetwork, Task task, Method method) throws InvalidConstraint {
	    
		// Apply unifier where relevant
		TaskNetwork unifiedMethodSubTasks = method.getTaskNetwork().apply(substitution);
		
		// TODO: Confirm if unifier really needs to be applied to the rest of the task network
		TaskNetwork updatedTaskNetwork = taskNetwork.apply(substitution)
		        .replace(task, unifiedMethodSubTasks);
		
		return updatedTaskNetwork;
	}
}
