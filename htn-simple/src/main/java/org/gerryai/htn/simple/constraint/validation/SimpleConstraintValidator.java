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

import org.gerryai.htn.simple.constraint.ValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Implementation of a validator for simple constraints.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface SimpleConstraintValidator extends ConstraintValidator {

	/**
	 * Validation check for simple precedence constraints, but does not add the constraint.
	 * @param constraint the constraint to validate
	 * @return true if the constraint passes validation
	 */
	boolean validate(ValidatablePrecedenceConstraint<?> constraint);

	/**
	 * Validates and adds the given constraint to the validator.
	 * @param constraint the constraint to add
	 * @throws InvalidConstraint if the constraint cannot be added
	 */
	void add(ValidatablePrecedenceConstraint<?> constraint) throws InvalidConstraint;

	/**
	 * Add the given task to the validator.
	 * @param task the task to add
	 */
	void add(Task task);
}