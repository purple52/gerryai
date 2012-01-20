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

import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.tasknetwork.TaskBuilder;
import org.gerryai.logic.Term;

/**
 * Builder for simple tasks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskBuilder implements TaskBuilder {

	/**
	 * Domain helper for looking up operators.
	 */
	private DomainHelper domainHelper;
	
	/**
	 * Name of the task being built.
	 */
	private String name;
	
	/**
	 * List of arguments for the task.
	 */
	private List<Term> arguments;

	/**
	 * Constructor, requires a domain helper to determine if the task is primitive.
	 * @param domainHelper domain helper to use
	 */
	public SimpleTaskBuilder(DomainHelper domainHelper) {
		arguments = new ArrayList<Term>();
		this.domainHelper = domainHelper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder addArgument(Term term) {
		arguments.add(term);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskBuilder addArguments(List<Term> terms) {
		arguments.addAll(terms);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleAbstractTask build() {
		
		SimpleAbstractTask task;
		
		try {
			domainHelper.getOperatorByName(name);
			task = new SimplePrimitiveTask(this);
		} catch (OperatorNotFound e) {
			task = new SimpleNonPrimitiveTask(this);
		}

		return task;
	}

	/**
	 * Get the name of the task to be built.
	 * @return the name
	 */
	protected final String getName() {
		return name;
	}
	
	/**
	 * Get the arguments for the tack to be built.
	 * @return the arguments
	 */
	protected final List<Term> getArguments() {
		return arguments;
	}

}
