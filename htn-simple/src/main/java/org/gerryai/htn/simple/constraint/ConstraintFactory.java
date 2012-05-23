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
import org.gerryai.logic.Term;

/**
 * @param <T> type of logical term used by the constraints created by this factory
 * @param <K> type of task used by the constraints created by this factory
 * @param <I> type of condition used by the constraints created by this factory
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface ConstraintFactory<T extends Term, K extends Task<T>, I extends Condition> {

    /**
     * Create a precedence constraint.
     * @param precedingTask the task that must come first
     * @param procedingTask the task that must come last
     * @return the constraint
     */
    PrecedenceConstraint<T, K> createPrecedenceConstraint(K precedingTask, K procedingTask);
    
    /**
	 * Create a precedence constraint.
	 * @param precedingTasks the tasks that must come first
	 * @param procedingTasks the tasks that must come last
	 * @return the constraint
	 */
	PrecedenceConstraint<T, K> createPrecedenceConstraint(Set<K> precedingTasks, Set<K> procedingTasks);
	
    /**
     * Create a before constraint.
     * @param task a task that the constraint must hold for
     * @param condition the condition that must be true directly before the first task in the set
     * @return the constraint
     */
    BeforeConstraint<T, K, I> createBeforeConstraint(K task, I condition);

    /**
	 * Create a before constraint.
	 * @param tasks set of tasks that the constraint must hold for
	 * @param condition the condition that must be true directly before the first task in the set
	 * @return the constraint
	 */
	BeforeConstraint<T, K, I> createBeforeConstraint(Set<K> tasks, I condition);
	
	/**
	 * Create an after constraint.
	 * @param tasks set of tasks that the constrain must hold for
	 * @param condition the condition that must be true directly after the last task in the set
	 * @return the constraint
	 */
	AfterConstraint<T, K, I> createAfterConstraint(Set<K> tasks, I condition);
	
	/**
	 * Create a between constraint.
	 * @param precedingTasks the set of tasks that this constraint must hold after
	 * @param procedingTasks the set of tasks that this constraint must hold before
	 * @param condition the condition that must be true between the two sets of tasks
	 * @return the constraint
	 */
	BetweenConstraint<T, K, I> createBetweenConstraint(Set<K> precedingTasks, Set<K> procedingTasks, I condition);

}
