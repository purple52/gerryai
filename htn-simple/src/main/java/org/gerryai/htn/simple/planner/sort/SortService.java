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
package org.gerryai.htn.simple.planner.sort;

import java.util.List;
import java.util.Set;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Interface for a service that can sort tasks based on a set of constraints.
 * @param <K> type of task this service works with
 * @param <C> type of constraint this service works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface SortService<
        K extends Task,
        C extends Constraint> {
    
    /**
     * Given a set of task and a set of constraints, return a sorted list of tasks.
     * @param tasks the tasks to sort
     * @param constraints the constraints to impose
     * @return the sorted list of tasks
     * @throws PlanNotFound if the constraints cannot be satisfied
     */
    List<ImmutableTask> sortByConstaints(Set<ImmutableTask> tasks,
            Set<ImmutableConstraint<?>> constraints) throws PlanNotFound;

}
