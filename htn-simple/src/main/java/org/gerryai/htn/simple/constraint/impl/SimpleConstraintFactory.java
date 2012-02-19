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

import org.gerryai.htn.simple.constraint.SubstitutableConstraintFactory;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleConstraintFactory implements SubstitutableConstraintFactory {

	/**
	 * {@inheritDoc}
	 */
	public final SimplePrecedenceConstraint
			createPrecedenceConstraint(SubstitutableTask precedingTask, SubstitutableTask procedingTask) {
		return new SimplePrecedenceConstraint(precedingTask, procedingTask);
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBeforeConstraint createBeforeConstraint(Set<SubstitutableTask> tasks,
			SubstitutableCondition condition) {
		return new SimpleBeforeConstraint(tasks, condition);
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleAfterConstraint createAfterConstraint(Set<SubstitutableTask> tasks,
			SubstitutableCondition condition) {
		return new SimpleAfterConstraint(tasks, condition);
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBetweenConstraint createBetweenConstraint(Set<SubstitutableTask> precedingTasks,
			Set<SubstitutableTask> procedingTasks, SubstitutableCondition condition) {
		return new SimpleBetweenConstraint(precedingTasks, procedingTasks, condition);
	}

}
