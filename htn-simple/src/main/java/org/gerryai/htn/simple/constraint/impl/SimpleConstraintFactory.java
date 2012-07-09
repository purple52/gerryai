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

import org.gerryai.htn.simple.constraint.ImmutableConstraintFactory;
import org.gerryai.htn.simple.constraint.ImmutableValidatableAfterConstraint;
import org.gerryai.htn.simple.constraint.ImmutableValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.ImmutableValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.ImmutableValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.tasknetwork.Task;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleConstraintFactory implements ImmutableConstraintFactory {

    /**
     * {@inheritDoc}
     */
    public final ImmutableValidatablePrecedenceConstraint
            createPrecedenceConstraint(Task precedingTask, Task procedingTask) {
        Set<Task> precedingTasks = new HashSet<Task>(1);
        precedingTasks.add(precedingTask);
        Set<Task> procedingTasks = new HashSet<Task>(1);
        procedingTasks.add(procedingTask);        
        return createPrecedenceConstraint(precedingTasks, procedingTasks);
    }

    
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableValidatablePrecedenceConstraint
			createPrecedenceConstraint(Set<Task> precedingTasks, Set<Task> procedingTasks) {
		return new SimplePrecedenceConstraint.Builder()
		        .setPrecedingTasks(precedingTasks)
		        .setProcedingTasks(procedingTasks)
		        .build();
	}

	   /**
     * {@inheritDoc}
     */
    public final ImmutableValidatableBeforeConstraint createBeforeConstraint(Task task,
            ImmutableCondition condition) {
        Set<Task> tasks = new HashSet<Task>(1);
        tasks.add(task);
        return createBeforeConstraint(tasks, condition);
    }
    
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableValidatableBeforeConstraint createBeforeConstraint(Set<Task> tasks,
	        ImmutableCondition condition) {
        return new SimpleBeforeConstraint.Builder()
                .addTasks(tasks)
                .setCondition(condition)
                .build();
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableValidatableAfterConstraint createAfterConstraint(Set<Task> tasks,
	        ImmutableCondition condition) {
		return new SimpleAfterConstraint.Builder()
		        .addTasks(tasks)
		        .setCondition(condition)
		        .build();
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableValidatableBetweenConstraint createBetweenConstraint(Set<Task> precedingTasks,
			Set<Task> procedingTasks, ImmutableCondition condition) {
        return new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(precedingTasks)
                .addProcedingTasks(procedingTasks)
                .setCondition(condition)
                .build();
    }

}
