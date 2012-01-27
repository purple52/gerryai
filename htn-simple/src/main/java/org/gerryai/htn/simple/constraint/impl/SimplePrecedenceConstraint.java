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

import org.gerryai.htn.simple.constraint.ValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.SimpleConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;

import com.google.common.base.Objects;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePrecedenceConstraint
		implements ValidatablePrecedenceConstraint<SimpleConstraintValidator> {

	/**
	 * The task that must come first.
	 */
	private Task precedingTask;
	
	/**
	 * The task that must come last.
	 */
	private Task procedingTask;
	
	/**
	 * Set the task to come first.
	 * @param precedingTask the task
	 */
	public final void setPrecedingTask(Task precedingTask) {
		this.precedingTask = precedingTask;
	}
	
	/**
	 * Set the task to come last.
	 * @param procedingTask the task
	 */
	public final void setProcedingTask(Task procedingTask) {
		this.procedingTask = procedingTask;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public final Task getPrecedingTask() {
		return precedingTask;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Task getProcedingTask() {
		return procedingTask;
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
	public final void add(SimpleConstraintValidator validator) throws InvalidConstraint {
		validator.add(this);
	}
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(precedingTask, procedingTask);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimplePrecedenceConstraint) {
	        final SimplePrecedenceConstraint other = (SimplePrecedenceConstraint) obj;
	        return Objects.equal(precedingTask, other.precedingTask)
	            && Objects.equal(procedingTask, other.procedingTask);
	    } else {
	        return false;
	    }
	}
}
