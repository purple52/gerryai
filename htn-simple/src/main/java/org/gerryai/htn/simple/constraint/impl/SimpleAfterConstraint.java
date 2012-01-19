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

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleAfterConstraint implements AfterConstraint {

	/**
	 * The set of tasks that this constraint must hold for.
	 */
	private Set<Task> tasks;
	
	/**
	 * The literal that must be true directly after the last of these tasks.
	 */
	private Term literal;
	
	/**
	 * Set the set of tasks that this constraint must hold for.
	 * @param tasks the tasks
	 */
	public final void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Set the literal that must be true directly after the last of these tasks.
	 * @param literal the literal
	 */
	public final void setLiteral(Term literal) {
		this.literal = literal;
	}
	
	/**
	 * Get the set of tasks that this constraint must hold for.
	 * @return the tasks
	 */
	public final Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * Get the literal that must be true directly after the last of these tasks.
	 * @return the literal
	 */
	public final Term getLiteral() {
		return literal;
	}

}
