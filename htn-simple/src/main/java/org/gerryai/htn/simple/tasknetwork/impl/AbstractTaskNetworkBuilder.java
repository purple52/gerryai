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

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Generic builder for task networks.
 * @param <T> type of logical terms these tasks will use
 * @param <K> type of task this builder will create
 * @param <N> type of task network this builder will create
 * @param <C> type of constraint this builder will create
 * @param <I> type of condition the constraints will use
 * @param <B> type of builder implemented
 * @author David Edwards <david@more.fool.me.uk>
 */
public abstract class AbstractTaskNetworkBuilder<
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends ValidatableConstraint<T, K, I>,
		I extends Condition,
		B extends AbstractTaskNetworkBuilder<T, K, N, C, I, B>>
		implements TaskNetworkBuilder<T, K, N, C> {

	/**
	 * Set of tasks we are building up.
	 */
	private Set<K> tasks;
	
	/**
	 * Set of constraints we are building up.
	 */
	private Set<C> constraints;
	
	/**
	 * Constraint validator.
	 */
	private ConstraintValidator<T, K, I> constraintValidator;
	
	/**
	 * Default constructor.
	 */
	protected AbstractTaskNetworkBuilder() {
		tasks = new HashSet<K>();
		constraints = new HashSet<C>();
	}
	
	/**
	 * @return the constraintValidator
	 */
	protected final ConstraintValidator<T, K, I> getConstraintValidator() {
		return constraintValidator;
	}

	/**
	 * @param constraintValidator the constraintValidator to set
	 */
	protected final void setConstraintValidator(ConstraintValidator<T, K, I> constraintValidator) {
		this.constraintValidator = constraintValidator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final B addTask(K task) {
		tasks.add(task);
		return me();
	}

	/**
	 * {@inheritDoc}
	 */
	public final B addTasks(Set<K> tasks) {
		this.tasks.addAll(tasks);
		return me();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final B addConstraint(
			C constraint) throws InvalidConstraint {
		addConstraintInternal(constraint);
		return me();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final B addConstraints(
			Set<C> constraints) throws InvalidConstraint {
		for (C constraint : constraints) {
			addConstraintInternal(constraint);
		}
		return me();
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract N build();
	
	/**
	 * Return this.
	 * @return this
	 */
	protected abstract B me();
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<K> getTasks() {
		return tasks;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<C> getConstraints() {
		return constraints;
	}
	
	/**
	 * Internal helper method to add a single constraint.
	 * Checks the validity of the constraint and updates the validator
	 * @param constraint the constraint
	 * @throws InvalidConstraint if the constraint was invalid
	 */
	private void addConstraintInternal(C constraint) throws InvalidConstraint {
		if (constraint.validate(getConstraintValidator())) { 
			constraints.add(constraint);
			constraint.add(getConstraintValidator());
		} else {
			throw new InvalidConstraint();
		}
	}

}
