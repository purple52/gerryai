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
package org.gerryai.htn.simple.constraint.validation.impl;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Generic constraint validator factory.
 * @param <T> type of term the validator will handle
 * @param <K> type of task the validator will handle
 * @param <I> type of condition the validator will handle
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class GenericConstraintValidatorFactory<T extends Term, K extends Task<T>, I extends Condition>
		implements ConstraintValidatorFactory<T, K, I> {

	/**
	 * {@inheritDoc}
	 */
	public final GenericConstraintValidator<T, K, I> create() {
		return new GenericConstraintValidator<T, K, I>();
	}

}
