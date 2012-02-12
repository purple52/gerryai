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

import org.gerryai.htn.simple.constraint.ConstraintFactory;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.impl.SimpleTerm;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleConstraintFactory implements ConstraintFactory<SimpleTerm, SimpleTask, SubstitutableCondition> {

	/**
	 * {@inheritDoc}
	 */
	public final SimplePrecedenceConstraint
			createPrecedenceConstraint(SimpleTask precedingTask, SimpleTask procedingTask) {
		SimplePrecedenceConstraint constraint = new SimplePrecedenceConstraint();
		constraint.setPrecedingTask(precedingTask);
		constraint.setProcedingTask(procedingTask);
		return constraint;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBeforeConstraint createBeforeConstraint(Set<SimpleTask> tasks,
			SubstitutableCondition condition) {
		SimpleBeforeConstraint constraint = new SimpleBeforeConstraint();
		constraint.setTasks(tasks);
		constraint.setCondition(condition);
		return constraint;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleAfterConstraint createAfterConstraint(Set<SimpleTask> tasks, SubstitutableCondition condition) {
		SimpleAfterConstraint constraint = new SimpleAfterConstraint();
		constraint.setTasks(tasks);
		constraint.setCondition(condition);
		return constraint;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBetweenConstraint createBetweenConstraint(Set<SimpleTask> precedingTasks,
			Set<SimpleTask> procedingTasks, SubstitutableCondition condition) {
		SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
		constraint.setPrecedingTasks(precedingTasks);
		constraint.setProcedingTasks(procedingTasks);
		constraint.setCondition(condition);
		return constraint;
	}

}
