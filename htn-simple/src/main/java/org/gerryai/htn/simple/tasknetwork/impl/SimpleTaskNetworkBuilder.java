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

import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;

/**
 * Concrete builder for SimpleTaskNetwork objects.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskNetworkBuilder extends AbstractTaskNetworkBuilder<SubstitutableTerm,
		SimpleTask, SimpleTaskNetwork, ValidatableConstraint<SubstitutableTerm, SimpleTask, SubstitutableCondition>,
		SubstitutableCondition, SimpleTaskNetworkBuilder> {

	/**
	 * Constructor, taking a constraint validator to use.
	 * @param constraintValidator the constraint validator
	 */
	SimpleTaskNetworkBuilder(ConstraintValidator<SubstitutableTerm, SimpleTask, SubstitutableCondition> constraintValidator) {
		this.setConstraintValidator(constraintValidator);
	}
	
	@Override
	public final SimpleTaskNetwork build() {
		return new SimpleTaskNetwork(this);
	}

	@Override
	protected final SimpleTaskNetworkBuilder me() {
		return this;
	}
}
