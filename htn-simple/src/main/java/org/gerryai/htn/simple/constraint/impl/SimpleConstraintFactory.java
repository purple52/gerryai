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
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleConstraintFactory implements ConstraintFactory {

	/**
	 * {@inheritDoc}
	 */
	public final SimplePrecedenceConstraint createPrecedenceConstraint(Task precedingTask, Task procedingTask) {
		SimplePrecedenceConstraint constraint = new SimplePrecedenceConstraint();
		constraint.setPrecedingTask(precedingTask);
		constraint.setProcedingTask(procedingTask);
		return constraint;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBeforeConstraint createBeforeConstraint(Set<Task> tasks, Term literal) {
		SimpleBeforeConstraint constraint = new SimpleBeforeConstraint();
		constraint.setTasks(tasks);
		constraint.setLiteral(literal);
		return constraint;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleAfterConstraint createAfterConstraint(Set<Task> tasks, Term literal) {
		SimpleAfterConstraint constraint = new SimpleAfterConstraint();
		constraint.setTasks(tasks);
		constraint.setLiteral(literal);
		return constraint;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBetweenConstraint createBetweenConstraint(Set<Task> precedingTasks,
			Set<Task> procedingTasks, Term literal) {
		SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
		constraint.setPrecedingTasks(precedingTasks);
		constraint.setProcedingTasks(procedingTasks);
		constraint.setLiteral(literal);
		return constraint;
	}

}
