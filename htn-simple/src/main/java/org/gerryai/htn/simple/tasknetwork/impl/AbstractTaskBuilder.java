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
package org.gerryai.htn.simple.tasknetwork.impl;

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.simple.tasknetwork.TaskBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Generic builder for tasks.
 * @param <T> type of logical terms these tasks will use
 * @param <K> type of task this builder will create
 * @param <B> type of task builder extending this base class
 * @author David Edwards <david@more.fool.me.uk>
 */
public abstract class AbstractTaskBuilder< T extends Term, K extends Task<T>,
		B extends AbstractTaskBuilder<T, K, B>> implements TaskBuilder<T, K> {

	/**
	 * Name of the task being built.
	 */
	private String name;
	
	/**
	 * Whether the task is primitive.
	 */
	private boolean isPrimitive;
	
	/**
	 * List of arguments for the task.
	 */
	private List<T> arguments;
	
	/**
	 * Default constructor.
	 */
	protected AbstractTaskBuilder() {
		arguments = new ArrayList<T>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final B setName(String name) {
		this.name = name;
		return me();
	}

	/**
	 * {@inheritDoc}
	 */
	public final B setIsPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
		return me();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final B addArgument(T term) {
		arguments.add(term);
		return me();
	}

	/**
	 * {@inheritDoc}
	 */
	public final B addArguments(List<T> terms) {
		arguments.addAll(terms);
		return me();
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract K build();

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean getIsPrimitive() {
		return isPrimitive;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<T> getArguments() {
		return arguments;
	}
	
	/**
	 * Return this.
	 * @return this
	 */
	protected abstract B me();

}
