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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ConstraintFactory;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleConstraintFactory implements
        ConstraintFactory<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>> {

    /**
     * {@inheritDoc}
     */
    public final SimplePrecedenceConstraint
            createPrecedenceConstraint(ImmutableTask precedingTask, ImmutableTask procedingTask) {
        Set<ImmutableTask> precedingTasks = new HashSet<ImmutableTask>(1);
        precedingTasks.add(precedingTask);
        Set<ImmutableTask> procedingTasks = new HashSet<ImmutableTask>(1);
        procedingTasks.add(procedingTask);        
        return createPrecedenceConstraint(precedingTasks, procedingTasks);
    }

    
	/**
	 * {@inheritDoc}
	 */
	public final SimplePrecedenceConstraint
			createPrecedenceConstraint(Set<ImmutableTask> precedingTasks, Set<ImmutableTask> procedingTasks) {
		return new SimplePrecedenceConstraint.Builder()
		        .setPrecedingTasks(precedingTasks)
		        .setProcedingTasks(procedingTasks)
		        .build();
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBeforeConstraint createBeforeConstraint(Set<ImmutableTask> tasks,
	        ImmutableCondition<?> condition) {
        return new SimpleBeforeConstraint.Builder()
                .addTasks(tasks)
                .setCondition(condition)
                .build();
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleAfterConstraint createAfterConstraint(Set<ImmutableTask> tasks,
	        ImmutableCondition<?> condition) {
		return new SimpleAfterConstraint.Builder()
		        .addTasks(tasks)
		        .setCondition(condition)
		        .build();
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBetweenConstraint createBetweenConstraint(Set<ImmutableTask> precedingTasks,
			Set<ImmutableTask> procedingTasks, ImmutableCondition<?> condition) {
        return new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(precedingTasks)
                .addProcedingTasks(procedingTasks)
                .setCondition(condition)
                .build();
    }

}
