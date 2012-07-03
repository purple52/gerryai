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

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Interface for factories that create constraint validators.
 * @param <K> type of task the validator will work with
 * @param <I> type of condition the validator will work with
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface ConstraintValidatorFactory<
        K extends Task,
        I extends Condition> {

	/**
	 * Create a validator.
	 * @return the validator
	 */
	ConstraintValidator<K, I> create();
}
