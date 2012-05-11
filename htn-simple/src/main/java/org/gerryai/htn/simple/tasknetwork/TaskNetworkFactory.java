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
package org.gerryai.htn.simple.tasknetwork;

import java.util.List;
import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.unification.Substitution;

/**
 * Interface for a factory that creates task network builders.
 * @param <T> the class of task the builder will handle
 * @param <K> type of task this factory uses
 * @param <N> type of task network this factory uses
 * @param <C> the class of constraint the builder will handle
 * @param <S> type of substitution the factory will handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskNetworkFactory<
		T extends SubstitutableTerm,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		S extends Substitution<T, T>> {

	/**
	 * Create a task network using the tasks and constraints provided.
	 * @param tasks the tasks
	 * @param constraints the constraints
	 * @return the task network builder
	 * @throws InvalidConstraint if any of the supplied constraints are invalid
	 */
	N createTaskNetwork(Set<K> tasks, Set<C> constraints) throws InvalidConstraint;
	
	/**
	 * Copy the given task network and apply a substitution.
	 * @param taskNetwork the network to copy
	 * @param substitution the substitution to apply
	 * @return the new task network
	 * @throws InvalidConstraint if any of constraints involved end up being invalid
	 */
	N copy(N taskNetwork, S substitution) throws InvalidConstraint;
	
	/**
	 * Copy the given task network, apply a substitution and replace a task with a network.
	 * @param taskNetwork the task network to copy
	 * @param substitution the substitution to apply
	 * @param oldTask the task to replace
	 * @param replacementNetwork the network to replace with
	 * @return the new task network
	 * @throws InvalidConstraint if any of constraints involved end up being invalid
	 */
	N copy(N taskNetwork, S substitution, K oldTask, N replacementNetwork) throws InvalidConstraint;
	
	/**
	 * Create a task using the name and arguments provided.
	 * @param name the name
	 * @param arguments the arguments
	 * @param isPrimitive whether the task is primitive or not
	 * @return the task builder
	 */
	K createTask(String name, List<T> arguments, boolean isPrimitive);
	
	/**
	 * Copy the given task and apply the given substituter.
	 * @param task the task to copy
	 * @param substitution the substitution to apply
	 * @return the new task
	 */
	K copy(K task, S substitution);
}
