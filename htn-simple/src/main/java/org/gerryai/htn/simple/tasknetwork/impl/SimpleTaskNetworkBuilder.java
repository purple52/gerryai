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
package org.gerryai.htn.simple.tasknetwork.impl;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Builder for simple task networks.
 * @param <V> the class of constraint validator to use
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkBuilder<V extends ConstraintValidator>
		implements TaskNetworkBuilder<Task, ValidatableConstraint<V>> {

	/**
	 * Set of tasks we are building up.
	 */
	private Set<Task> tasks;
	
	/**
	 * Set of constraints we are building up.
	 */
	private Set<Constraint> constraints;
	
	/**
	 * Constraint validator.
	 */
	private V validator;
	
	/**
	 * Default constructor.
	 * @param validator the constraint validator to use
	 */
	public SimpleTaskNetworkBuilder(V validator) {
		tasks = new HashSet<Task>();
		constraints = new HashSet<Constraint>();
		this.validator = validator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder<V> addTask(Task task) {
		tasks.add(task);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder<V> addTasks(Set<Task> tasks) {
		this.tasks.addAll(tasks);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder<V>
			addConstraint(ValidatableConstraint<V> constraint) throws InvalidConstraint {
		addConstraintInternal(constraint);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder<V> addConstraints(Set<ValidatableConstraint<V>> constraints)
			throws InvalidConstraint {
		for (ValidatableConstraint<V> constraint : constraints) {
			addConstraintInternal(constraint);
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetwork build() {
		return new SimpleTaskNetwork(this);
	}
	
	/**
	 * Get the set of tasks for the task network to be built.
	 * @return the tasks
	 */
	protected final Set<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * Get the set of constraints for the task network to be built.
	 * @return the constraints
	 */
	protected final Set<Constraint> getConstraints() {
		return constraints;
	}
	
	/**
	 * Internal helper method to add a single constraint.
	 * Checks the validity of the constraint and updates the validator
	 * @param constraint the constraint
	 * @throws InvalidConstraint if the constraint was invalid
	 */
	private void addConstraintInternal(ValidatableConstraint<V> constraint) throws InvalidConstraint {
		if (constraint.validate(validator)) { 
			constraints.add(constraint);
			constraint.add(validator);
		} else {
			throw new InvalidConstraint();
		}
	}

}
