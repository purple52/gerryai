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

import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Interface for a builder that creates task networks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskNetworkBuilder {

	/**
	 * Add a task.
	 * @param task the task
	 * @return the updated builder
	 */
	TaskNetworkBuilder addTask(Task task);

	/**
	 * Add a set of tasks.
	 * @param tasks the tasks
	 * @return the updated builder
	 */
	TaskNetworkBuilder addTasks(Set<Task> tasks);
	
	/**
	 * Add a before constraint, checking it is valid for this network.
	 * @param constraint the constraint
	 * @return the updated builder
	 * @throws InvalidConstraint if the constraint could not be added
	 */
	TaskNetworkBuilder addBeforeConstraint(BeforeConstraint constraint) throws InvalidConstraint;

	/**
	 * Add an after constraint, checking it is valid for this network.
	 * @param constraint the constraint
	 * @return the updated builder
	 * @throws InvalidConstraint if the constraint could not be added
	 */
	TaskNetworkBuilder addAfterConstraint(AfterConstraint constraint) throws InvalidConstraint;
	
	/**
	 * Add a between constraint, checking it is valid for this network.
	 * @param constraint the constraint
	 * @return the updated builder
	 * @throws InvalidConstraint if the constraint could not be added
	 */
	TaskNetworkBuilder addBetweenConstraint(BetweenConstraint constraint) throws InvalidConstraint;

	/**
	 * Add a precedence constraint, checking it is valid for this network.
	 * @param constraint the constraint
	 * @return the updated builder
	 * @throws InvalidConstraint if the constraint could not be added
	 */
	TaskNetworkBuilder addPrecedenceConstraint(PrecedenceConstraint constraint) throws InvalidConstraint;
	
	/**
	 * Adds all the tasks and constraints from the given network, replacing any existing.
	 * @param taskNetwork the network to copy from
	 * @return the updated builder
	 * @throws InvalidConstraint if any of the network's constraints could not be added
	 *
	TaskNetworkBuilder copy(TaskNetwork taskNetwork)  throws InvalidConstraint;
	*/
	
    /**
     * Apply the provided substituter to the arguments provided so far.
     * @param substitution the substitution to apply
     * @return the updated builder
     * @throws InvalidConstraint if an invalid constrain was detected
     */
	TaskNetworkBuilder apply(Map<Term, Term> substitution) throws InvalidConstraint;
	
	/**
	 * Replace the given task with the network of tasks and constraints provided.
	 * @param oldTask the task to replace
	 * @param taskNetwork the network to replace with
	 * @return the updated builder
	 * @throws InvalidConstraint  if an invalid constraint was detected
	 */
	TaskNetworkBuilder replace(Task oldTask, TaskNetwork taskNetwork) throws InvalidConstraint;
	
	/**
	 * Build the task network.
	 * @return the task network
	 */
	TaskNetwork build();
}
