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

import org.gerryai.htn.simple.constraint.SubstitutableValidatableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilderFactory;

/**
 * Factory for SimpleTask and SimpleTaskNetwork objects.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkBuilderFactory implements
		TaskNetworkBuilderFactory<SubstitutableTerm, SubstitutableTask, SubstitutableTaskNetwork, 
		SubstitutableValidatableConstraint> {

	/**
	 * Factory for creating constraint validators, as used by the task network builders.
	 */
	private ConstraintValidatorFactory<SubstitutableTerm, SubstitutableTask,
			SubstitutableCondition> constraintValidatorFactory;
	
	/**
	 * Constructor, requiring a factory for creating constraint validators.
	 * @param constraintValidatorFactory the constraint validator factory
	 */
	public SimpleTaskNetworkBuilderFactory(ConstraintValidatorFactory<SubstitutableTerm,
			SubstitutableTask, SubstitutableCondition> constraintValidatorFactory) {
		this.constraintValidatorFactory = constraintValidatorFactory;
	}
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder createTaskNetworkBuilder() {
		ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>
				constraintValidator = constraintValidatorFactory.create();
		return new SimpleTaskNetworkBuilder(constraintValidator);	
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder createTaskBuilder() {
		return new SimpleTaskBuilder();	
	}
}
