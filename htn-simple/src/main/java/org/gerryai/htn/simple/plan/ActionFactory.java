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
package org.gerryai.htn.simple.plan;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.TaskNotActionable;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Interface for a factory that can generate actions.
 * @param <E> type of effect this action uses
 * @param <O> type of operator this action uses
 * @param <I> type of condition the action uses
 * @param <A> type of action this factory generates
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ActionFactory<
        E extends Effect,
        O extends Operator<E, I>,
        I extends Condition,
        A extends Action<E, O, I>> {

	/**
	 * Convert a task into an action.
	 * @param task task to convert
	 * @return the action for this task
	 * @throws TaskNotActionable if the task cannot be converted into an action
	 */
	A create(Task task) throws TaskNotActionable;
}
