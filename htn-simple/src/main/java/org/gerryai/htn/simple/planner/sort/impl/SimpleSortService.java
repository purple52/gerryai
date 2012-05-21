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
package org.gerryai.htn.simple.planner.sort.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.planner.sort.SortService;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;

import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * Simple implementation of the SortService interface.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleSortService implements SortService<ImmutableTerm<?>, ImmutableTask, ImmutableConstraint<?>> {

    /**
     * {@inheritDoc}
     */
    public final List<ImmutableTask> sortByConstaints(Set<ImmutableTask> tasks,
            Set<ImmutableConstraint<?>> constraints) throws PlanNotFound {
        
        // TODO: Inject this list rather than compute it unsafely at runtime
        Set<PrecedenceConstraint<ImmutableTerm<?>, ImmutableTask>> precedenceConstraints =
                new HashSet<PrecedenceConstraint<ImmutableTerm<?>, ImmutableTask>>();
        for (ImmutableConstraint<?> constraint : constraints) {
            if ( constraint instanceof PrecedenceConstraint<?, ?>) {
                precedenceConstraints.add((PrecedenceConstraint<ImmutableTerm<?>, ImmutableTask>) constraint);
            }
        }
        
        //SimpleDirectedGraph
        //TaskComparator<ImmutableTask> comparator
        List<ImmutableTask> sortedTasks = new ArrayList<ImmutableTask>(tasks);
        
        return sortedTasks;
    }

}
