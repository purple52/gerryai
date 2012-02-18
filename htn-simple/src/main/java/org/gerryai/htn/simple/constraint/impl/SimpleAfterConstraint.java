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

import org.gerryai.htn.simple.constraint.SubstitutableConstraint;
import org.gerryai.htn.simple.constraint.ValidatableAfterConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;

import com.google.common.base.Objects;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleAfterConstraint implements ValidatableAfterConstraint<SubstitutableTerm,
		SubstitutableTask, SubstitutableCondition>, SubstitutableConstraint<SubstitutableTerm> {

	/**
	 * The set of tasks that this constraint must hold for.
	 */
	private Set<SubstitutableTask> tasks;
	
	/**
	 * The condition that must be true directly after the last of these tasks.
	 */
	private SubstitutableCondition condition;
	
	/**
	 * Set the set of tasks that this constraint must hold for.
	 * @param tasks the tasks
	 */
	public final void setTasks(Set<SubstitutableTask> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Set the condition that must be true directly after the last of these tasks.
	 * @param condition the literal
	 */
	public final void setCondition(SubstitutableCondition condition) {
		this.condition = condition;
	}
	
	/**
	 * Get the set of tasks that this constraint must hold for.
	 * @return the tasks
	 */
	public final Set<SubstitutableTask> getTasks() {
		return tasks;
	}

	/**
	 * Get the condition that must be true directly after the last of these tasks.
	 * @return the condition
	 */
	public final SubstitutableCondition getCondition() {
		return condition;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ConstraintValidator<SubstitutableTerm, SubstitutableTask,
			SubstitutableCondition> validator) {
		return validator.validate(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> validator)
			throws InvalidConstraint {
		validator.add(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void apply(Substituter<SubstitutableTerm> substituter) {
		condition.apply(substituter);
	}
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(tasks, condition);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimpleAfterConstraint) {
	        final SimpleAfterConstraint other = (SimpleAfterConstraint) obj;
	        return Objects.equal(tasks, other.tasks)
	            && Objects.equal(condition, other.condition);
	    } else {
	        return false;
	    }
	}
}
