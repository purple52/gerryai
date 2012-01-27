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
package org.gerryai.htn.simple.constraint.validation.impl;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ValidatableAfterConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.ValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.constraint.validation.SimpleConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Class for validating precedence constraints.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleConstraintValidatorImpl implements ConstraintValidator, SimpleConstraintValidator {

	/**
	 * Set of tasks that have been added to the validator so far.
	 */
	private Set<Task> tasks;
	
	/**
	 * Set of precedence constraints that have been added so far.
	 */
	private Set<ValidatablePrecedenceConstraint<?>> precedenceConstraints;
	
	/**
	 * Set of before constraints that have been added so far.
	 */
	private Set<ValidatableBeforeConstraint<?>> beforeConstraints;

	/**
	 * Set of after constraints that have been added so far.
	 */
	private Set<ValidatableAfterConstraint<?>> afterConstraints;

	/**
	 * Set of between constraints that have been added so far.
	 */
	private Set<ValidatableBetweenConstraint<?>> betweenConstraints;
	
	/**
	 * Map of precedence constraints, using their preceding task as the key.
	 * Used to simplify looking for cycles.
	 */
	private Multimap<Task, ValidatablePrecedenceConstraint<?>> precedingTasks;
	
	/**
	 * Default constructor.
	 */
	public SimpleConstraintValidatorImpl() {
		tasks = new HashSet<Task>();
		precedenceConstraints = new HashSet<ValidatablePrecedenceConstraint<?>>();
		precedingTasks = HashMultimap.create();
		beforeConstraints = new HashSet<ValidatableBeforeConstraint<?>>();
		afterConstraints = new HashSet<ValidatableAfterConstraint<?>>();
		betweenConstraints = new HashSet<ValidatableBetweenConstraint<?>>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatablePrecedenceConstraint<?> constraint) {
		
		// Check tasks have already been added
		if (!tasks.contains(constraint.getPrecedingTask()) || !tasks.contains(constraint.getProcedingTask())) {
			return false;
		}
		
		// Check if an existing identical constraint exists
		for (ValidatablePrecedenceConstraint<?> existingConstraint : precedenceConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		
		//Check for cycles
		if (checkForPrecedenceConstraintCycle(constraint.getPrecedingTask(),
				constraint.getProcedingTask())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatableBeforeConstraint<?> constraint) {
		// Check tasks have already been added
		for (Task task : constraint.getTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (ValidatableBeforeConstraint<?> existingConstraint : beforeConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatableAfterConstraint<?> constraint) {
		// Check tasks have already been added
		for (Task task : constraint.getTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (ValidatableAfterConstraint<?> existingConstraint : afterConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatableBetweenConstraint<?> constraint) {
		// Check tasks have already been added
		for (Task task : constraint.getPrecedingTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		for (Task task : constraint.getProcedingTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (ValidatableBetweenConstraint<?> existingConstraint : betweenConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatablePrecedenceConstraint<?> constraint) throws InvalidConstraint {
		if (validate(constraint)) {
			precedenceConstraints.add(constraint);
			precedingTasks.get(constraint.getPrecedingTask()).add(constraint);
		} else {
			throw new InvalidConstraint();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatableBeforeConstraint<?> constraint)
			throws InvalidConstraint {
		if (validate(constraint)) {
			beforeConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatableAfterConstraint<?> constraint)
			throws InvalidConstraint {
		if (validate(constraint)) {
			afterConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatableBetweenConstraint<?> constraint)
			throws InvalidConstraint {
		if (validate(constraint)) {
			betweenConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void add(Task task) {
		tasks.add(task);
	}

	/**
	 * Helper method to help check for cycles.
	 * @param initialTask task we are looking for
	 * @param nextTask next task we should check from
	 * @return true if a cycle was found
	 */
	private boolean checkForPrecedenceConstraintCycle(Task initialTask, Task nextTask) {
		
		if (initialTask.equals(nextTask)) {
			return true;
		} else if (!precedingTasks.containsKey(nextTask)) {
			return false;
		} else {
			for (ValidatablePrecedenceConstraint<?> constraint : precedingTasks.get(nextTask)) {
				if (checkForPrecedenceConstraintCycle(initialTask, constraint.getProcedingTask())) {
					return true;
				}
			}
			return false;
		}
		
	}
}
