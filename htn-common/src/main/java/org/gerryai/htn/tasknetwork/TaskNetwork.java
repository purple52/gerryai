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

import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.logic.Term;

/**
 * Interface that a task network must implement.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskNetwork {

	/**
	 * Get the tasks that make up this network.
	 * @return the tasks
	 */
	Set<Task> getTasks();

	/**
	 * Get all the before constraints for this network.
	 * @return the before constraints
	 */
	Set<BeforeConstraint> getBeforeConstraints();

	/**
	 * Get all the before constraints for this network.
	 * @return the before constraints
	 */
	Set<AfterConstraint> getAfterConstraints();

	/**
	 * Get all the between constraints for this network.
	 * @return the between constraints
	 */
	Set<BetweenConstraint> getBetweenConstraints();

	/**
	 * Get all the between constraints for this network.
	 * @return the between constraints
	 */
	Set<PrecedenceConstraint> getPrecedenceConstraints();
	
	/**
	 * Check if the task network is primitive by examining its tasks.
	 * @return whether the task network is primitive
	 */
	boolean isPrimitive();
	
    /**
     * Copy this task network and apply the provided substituter to the copy.
     * @param substitution the substitution to apply
     * @return a copy of the original task network with the substitution applied
     * @throws InvalidConstraint if an invalid constraint was encountered
     */
	TaskNetwork apply(Map<Term, Term> substitution) throws InvalidConstraint;
	
	/**
	 * Copy this task network, with the given task replaced by the set of tasks provided.
	 * @param task the task to replace
	 * @param taskNetwork the task network to replace with
	 * @return a copy of the original task network with the replacement applied
	 * @throws InvalidConstraint if an invalid constraint was encountered
	 */
	TaskNetwork replace(Task task, TaskNetwork taskNetwork) throws InvalidConstraint;
}
