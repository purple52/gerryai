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

import java.util.Map;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

import com.google.common.collect.Multimap;

/**
 * Interface for constraint builders used by this HTN implementation.
 * Allows constraints to be copied and have substitutions applied without
 * the called needing to know what type of constraint is being handled.
 * @param <C> type of constraint this builder generates
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableConstraintBuilder<C extends Constraint<C>> {

    /**
     * Copy the provided constraint as a basis for a new one.
     * @param constraint the constraint to copy
     * @return the updated builder
     */
    ImmutableConstraintBuilder<C> copy(C constraint);
    
    /**
     * Replace tasks in the constrain being built using the map provided.
     * @param taskMap map to tasks to sets of tasks that they should be replaced with
     * @return the updated builder
     */
    ImmutableConstraintBuilder<C> replace(Multimap<Task, Task> taskMap);
 
    /**
     * Apply the substituter provided to the conditions of the constraint.
     * @param substitution the substitution to apply
     * @return the updated builder
     */
    ImmutableConstraintBuilder<C> apply(Map<Term, Term> substitution);
    
    /**
     * Build the constraint.
     * @return the finished constraint
     */
    C build();
}
