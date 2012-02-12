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

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Extension of validatable and precedence constraint interfaces.
 * @param <T> type of logical term this constraint uses
 * @param <K> type of task  the constraint applies to
 * @param <I> type of condition this constraint uses
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface ValidatableAfterConstraint<T extends Term, K extends Task<T>, I extends Condition>
		extends ValidatableConstraint<T, K, I>, AfterConstraint<T, K, I> {

}
