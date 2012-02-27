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
package org.gerryai.htn.simple.constraint.impl;

import java.util.Set;

import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;

/**
 * Interface for constraint builders used by this HTN implementation.
 * Allows constraints to be copied and have substitutions applied without
 * the called needing to know what type of constraint is being handled.
 * @param <C> type of constraint this builder generates
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface SimpleConstraintBuilder<C extends SimpleConstraint<C>> {

    /**
     * Copy the provided constraint as a basis for a new one.
     * @param constraint the constraint to copy
     * @return the updated builder
     */
    SimpleConstraintBuilder<C> copy(C constraint);
    
    /**
     * @param oldTask the task to replace
     * @param newTasks the tasks to replace with
     * @return the updated builder
     */
    SimpleConstraintBuilder<C> replace(SubstitutableTask oldTask, Set<SubstitutableTask> newTasks);
    
    /**
     * Apply the substituter provided to the conditions of the constraint.
     * @param substituter the substituter to apply
     * @return the updated builder
     */
    SimpleConstraintBuilder<C> apply(Substituter<SubstitutableTerm> substituter);
    
    /**
     * Build the constraint.
     * @return the finished constraint
     */
    C build();
}
