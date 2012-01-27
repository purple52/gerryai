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
package org.gerryai.htn.simple.constraint;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;

/**
 * Extended constraint interface that supports being validated.
 * The constraint needs to identify what class of validator it needs to use.
 * @param <V> the class of validator that the constraint uses
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ValidatableConstraint<V extends ConstraintValidator> extends Constraint {

	/**
	 * Validate this constraint using the validator provided.
	 * @param validator the validator to use
	 * @return true if the constraint passed validation
	 */
	boolean validate(V validator);
	
	/**
	 * Add the constraint to the validator so it is considered when validating in future.
	 * @param validator the validator to add to.
	 * @throws InvalidConstraint if constraint cannot be added
	 */
	void add(V validator) throws InvalidConstraint;
	
}
