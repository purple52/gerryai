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

import org.gerryai.htn.simple.constraint.SubstitutableValidatableConstraint;
import org.gerryai.htn.simple.decomposition.DecompositionService;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.logic.unification.Substitution;

/**
 * Simple implementation of a decomposition service, for decomposing a task
 * within a network using a specified method and unifier.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDecompositionService implements
		DecompositionService<SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
		SubstitutableValidatableConstraint> {

	/**
	 * Service for performing unification actions.
	 */
	private UnificationService<SubstitutableMethod, SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork,
			SubstitutableValidatableConstraint,
			SubstitutableCondition, SimpleVariable> unificationService;
	
	/**
	 * Set the unification service.
	 * @param unificationService the unification service to use
	 */
	public SimpleDecompositionService(UnificationService<SubstitutableMethod, SubstitutableTerm, SubstitutableTask,
			SubstitutableTaskNetwork, SubstitutableValidatableConstraint,
			SubstitutableCondition, SimpleVariable> unificationService) {
		this.unificationService = unificationService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTaskNetwork decompose(Substitution<SubstitutableTerm, SimpleVariable> unifier,
			SubstitutableTaskNetwork taskNetwork, SubstitutableTask task, SubstitutableMethod method) {
		// TODO: Ensure the original task network is never altered
	    
		// Apply unifier where relevant
		SubstitutableTaskNetwork unifiedMethodSubTasks = unificationService.apply(unifier, method.getTaskNetwork());
		// TODO: Confirm if unifier really needs to be applied to the rest of the task network
		SubstitutableTaskNetwork updatedTaskNetwork = unificationService.apply(unifier, taskNetwork);
		
		// Replace the original task with the sub tasks provided by the method
		updatedTaskNetwork.getTasks().remove(task);
		updatedTaskNetwork.getTasks().addAll(unifiedMethodSubTasks.getTasks());
		
		// TODO Complete implementation, including constraints
		
		return updatedTaskNetwork;
	}

}
