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

import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleBetweenConstraint implements BetweenConstraint {

	/**
	 * The set of tasks that this constraint must hold after.
	 */
	private Set<Task> precedingTasks;

	/**
	 * The set of tasks that this constraint must hold before.
	 */
	private Set<Task> procedingTasks;
	
	/**
	 * The literal that must be true directly after the last of these tasks.
	 */
	private Term literal;
	
	/**
	 * Set the set of tasks that this constraint must hold after.
	 * @param precedingTasks the tasks
	 */
	public final void setPrecedingTasks(Set<Task> precedingTasks) {
		this.precedingTasks = precedingTasks;
	}

	/**
	 * Set the set of tasks that this constraint must hold before.
	 * @param procedingTasks the tasks
	 */
	public final void setProcedingTasks(Set<Task> procedingTasks) {
		this.procedingTasks = procedingTasks;
	}
	
	/**
	 * Set the literal that must be true directly after the last of these tasks.
	 * @param literal the literal
	 */
	public final void setLiteral(Term literal) {
		this.literal = literal;
	}
	
	/**
	 * Get the set of tasks that this constraint must hold after.
	 * @return the tasks
	 */
	public final Set<Task> getPrecedingTasks() {
		return precedingTasks;
	}

	/**
	 * Get the set of tasks that this constraint must hold before.
	 * @return the tasks
	 */
	public final Set<Task> getProcedingTasks() {
		return procedingTasks;
	}
	
	/**
	 * Get the literal that must be true directly after the last of these tasks.
	 * @return the literal
	 */
	public final Term getLiteral() {
		return literal;
	}

}
