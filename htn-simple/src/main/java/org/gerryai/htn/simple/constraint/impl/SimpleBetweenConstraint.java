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
import org.gerryai.htn.simple.constraint.ValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.validation.SimpleConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;

import com.google.common.base.Objects;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleBetweenConstraint implements ValidatableBetweenConstraint<SimpleConstraintValidator> {

	/**
	 * The set of tasks that this constraint must hold after.
	 */
	private Set<Task> precedingTasks;

	/**
	 * The set of tasks that this constraint must hold before.
	 */
	private Set<Task> procedingTasks;
	
	/**
	 * The literal that must be true directly between the two sets of tasks.
	 */
	private Condition condition;
	
	/**
	 * Set the set of tasks that this constraint must hold after.
	 * @param precedingTasks the tasks
	 */
	public final void setPrecedingTasks(Set<Task> precedingTasks) {
		this.precedingTasks = precedingTasks;
	}

	/**
	 * Set the set of tasks that this constraint must hold before.
	 * @param procedingTasks the tasks
	 */
	public final void setProcedingTasks(Set<Task> procedingTasks) {
		this.procedingTasks = procedingTasks;
	}
	
	/**
	 * Set the condition that must be true directly after the last of these tasks.
	 * @param condition the condition
	 */
	public final void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	/**
	 * Get the set of tasks that this constraint must hold after.
	 * @return the tasks
	 */
	public final Set<Task> getPrecedingTasks() {
		return precedingTasks;
	}

	/**
	 * Get the set of tasks that this constraint must hold before.
	 * @return the tasks
	 */
	public final Set<Task> getProcedingTasks() {
		return procedingTasks;
	}
	
	/**
	 * Get the condition that must be true directly after the last of these tasks.
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
		return Objects.hashCode(precedingTasks, procedingTasks, condition);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimpleBetweenConstraint) {
	        final SimpleBetweenConstraint other = (SimpleBetweenConstraint) obj;
	        return Objects.equal(precedingTasks, other.precedingTasks)
	        	&& Objects.equal(procedingTasks, other.procedingTasks)
	            && Objects.equal(condition, other.condition);
	    } else {
	        return false;
	    }
	}
}
