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
package org.gerryai.htn.aima.unification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.aima.AIMAConverter;
import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilderFactory;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;
import org.gerryai.logic.unification.Unifier;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 * 
 */
public class AIMAUnificationService implements UnificationService {

	/**
	 * AIMA Unifier object to do the underlying expression unification.
	 */
	private aima.core.logic.fol.Unifier unifier;

	/**
	 * Converter to convert between our classes and the AIMA FOL classes.
	 */
	private AIMAConverter converter;
	
	/**
	 * Helper for checking whether tasks are primitive or not.
	 */
	private DomainHelper domainHelper;
	
	/**
	 * Factory for creating task network builders.
	 */
	private TaskNetworkBuilderFactory<Task, Constraint>  taskNetworkBuilderFactory;
	
	/**
	 * {@inheritDoc}
	 */
	public final Unifier findUnifier(Task task, Method method) {

		Predicate taskPredicate = converter.convert(task);
		Predicate methodTaskPredicate = converter.convert(method.getTask());

		Map<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term> map
				= unifier.unify(taskPredicate, methodTaskPredicate);
		return converter.convert(map);
	}

	/**
	 * {@inheritDoc}
	 */
	public final TaskNetwork apply(Unifier unifier, TaskNetwork taskNetwork) {
		
		// TODO Add support constraints
		Set<Task> updatedTasks = new HashSet<Task>();
		for (Task task : taskNetwork.getTasks()) {
			Task updatedTask = apply(unifier, task);
			updatedTasks.add(updatedTask);
		}

		TaskNetwork updatedTaskNetwork =
				taskNetworkBuilderFactory.create()
				.addTasks(updatedTasks)
				.build();
		
		return updatedTaskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTask apply(Unifier unifier, Task task) {
		
		List<Term> updatedTerms = new ArrayList<Term>();		
		for (Term term : task.getArguments()) {
			if (unifier.getMap().containsKey(term)) {
				updatedTerms.add(unifier.getMap().get(term));
			} else {
				updatedTerms.add(term);
			}
		}

		SimpleTask updatedTask = new SimpleTaskBuilder(domainHelper)
				.setName(task.getName())
				.addArguments(updatedTerms)
				.build();
		
		return updatedTask;
	}

}
