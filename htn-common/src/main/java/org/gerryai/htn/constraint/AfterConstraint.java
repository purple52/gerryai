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
package org.gerryai.htn.constraint;

import java.util.Set;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Interface for a constraint that dictates the state immediately after this task.
 * @param <T> type of logical term this constraint works with
 * @param <K> type of task this constraint works with
 * @param <I> type of condition this constraint uses
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface AfterConstraint<T extends Term, K extends Task<T>, I extends Condition> extends Constraint<T> {

	/**
	 * The set of tasks for which this constraint must hold.
	 * @return the set of tasks
	 */
	Set<K> getTasks();
	
	/**
	 * The condition that must be true just after the last of the tasks is achieved.
	 * @return the literal
	 */
	I getCondition();
}
