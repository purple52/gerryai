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

import java.util.Set;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Factory for creating constraints.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ConstraintFactory {

    /**
     * Create a precedence constraint.
     * @param precedingTask the task that must come first
     * @param procedingTask the task that must come last
     * @return the constraint
     */
    PrecedenceConstraint createPrecedenceConstraint(Task precedingTask, Task procedingTask);
    
    /**
	 * Create a precedence constraint.
	 * @param precedingTasks the tasks that must come first
	 * @param procedingTasks the tasks that must come last
	 * @return the constraint
	 */
    PrecedenceConstraint createPrecedenceConstraint(Set<Task> precedingTasks, Set<Task> procedingTasks);
	
    /**
     * Create a before constraint.
     * @param task a task that the constraint must hold for
     * @param condition the condition that must be true directly before the first task in the set
     * @return the constraint
     */
    BeforeConstraint createBeforeConstraint(Task task, Condition condition);

    /**
	 * Create a before constraint.
	 * @param tasks set of tasks that the constraint must hold for
	 * @param condition the condition that must be true directly before the first task in the set
	 * @return the constraint
	 */
    BeforeConstraint createBeforeConstraint(Set<Task> tasks, Condition condition);
	
	/**
	 * Create an after constraint.
	 * @param tasks set of tasks that the constrain must hold for
	 * @param condition the condition that must be true directly after the last task in the set
	 * @return the constraint
	 */
	AfterConstraint createAfterConstraint(Set<Task> tasks, Condition condition);
	
	/**
	 * Create a between constraint.
	 * @param precedingTasks the set of tasks that this constraint must hold after
	 * @param procedingTasks the set of tasks that this constraint must hold before
	 * @param condition the condition that must be true between the two sets of tasks
	 * @return the constraint
	 */
	BetweenConstraint createBetweenConstraint(Set<Task> precedingTasks, Set<Task> procedingTasks, Condition condition);

}
