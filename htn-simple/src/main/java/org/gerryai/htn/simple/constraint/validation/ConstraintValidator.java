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
package org.gerryai.htn.simple.constraint.validation;

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
 * Implementation of a validator for simple constraints.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ConstraintValidator {

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
	 * Validates and adds the given constraint to the validator.
	 * @param constraint the constraint to add
	 * @throws InvalidConstraint if the constraint cannot be added
	 */
	void add(BeforeConstraint constraint) throws InvalidConstraint;

	/**
	 * Validates and adds the given constraint to the validator.
	 * @param constraint the constraint to add
	 * @throws InvalidConstraint if the constraint cannot be added
	 */
	void add(AfterConstraint constraint) throws InvalidConstraint;

	/**
	 * Validates and adds the given constraint to the validator.
	 * @param constraint the constraint to add
	 * @throws InvalidConstraint if the constraint cannot be added
	 */
	void add(BetweenConstraint constraint) throws InvalidConstraint;

	/**
	 * Validates and adds the given constraint to the validator.
	 * @param constraint the constraint to add
	 * @throws InvalidConstraint if the constraint cannot be added
	 */
	void add(PrecedenceConstraint constraint) throws InvalidConstraint;

	/**
	 * Add the given task to the validator.
	 * @param task the task to add
	 */
	void add(Task task);
	
    /**
     * Apply the provided substitution to the tasks and constraints in this validator.
     * @param substitution the substitution to apply
     * @throws InvalidConstraint if an invalid constraint was encountered
     */
	void apply(Map<Term, Term> substitution) throws InvalidConstraint;
	
	/**
	 * Copy this task network, with the given task replaced by the set of tasks provided.
	 * @param task the task to replace
	 * @param taskNetwork the task network to replace with
	 * @throws InvalidConstraint if an invalid constraint was encountered
	 */
	void replace(Task task, TaskNetwork taskNetwork) throws InvalidConstraint;
}