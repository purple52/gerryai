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
package org.gerryai.htn.simple.constraint.impl;

import java.util.Set;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.ValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.validation.SimpleConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;

import com.google.common.base.Objects;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleBeforeConstraint implements ValidatableBeforeConstraint<SimpleConstraintValidator> {

	/**
	 * The set of tasks that this constraint must hold for.
	 */
	private Set<Task> tasks;
	
	/**
	 * The condition that must be true directly before the first of these tasks.
	 */
	private Condition condition;
	
	/**
	 * Set the set of tasks that this constraint must hold for.
	 * @param tasks the tasks
	 */
	public final void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Set the condition that must be true directly before the first of these tasks.
	 * @param condition the condition
	 */
	public final void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	/**
	 * Get the set of tasks that this constraint must hold for.
	 * @return the tasks
	 */
	public final Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * Get the condition that must be true directly before the first of these tasks.
	 * @return the condition
	 */
	public final Condition getCondition() {
		return condition;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(SimpleConstraintValidator validator) {
		return validator.validate(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(SimpleConstraintValidator validator)
			throws InvalidConstraint {
		validator.add(this);
	}
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(tasks, condition);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimpleBeforeConstraint) {
	        final SimpleBeforeConstraint other = (SimpleBeforeConstraint) obj;
	        return Objects.equal(tasks, other.tasks)
	            && Objects.equal(condition, other.condition);
	    } else {
	        return false;
	    }
	}
}
