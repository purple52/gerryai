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
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilderFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.gerryai.logic.unification.Substitution;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * Unification service that use an AIMA unifier underneath.
 * @param <O> the type of operator this service works with
 * @param <M> the type of method this service works with
 * @param <T> the type of logical term this service works with
 * @param <K> the type of task this service works with
 * @param <N> the type of task network this service works with
 * @param <C> the type of constraint this service works with
 * @param <I> the class of condition the builder will handle
 * @param <V> the type of variable this service works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public class AIMAUnificationService<
		O extends Operator<I>,
		M extends Method<T, K, N, C>,
		T extends Term,
		K extends Task<T>,
		N extends TaskNetwork<T, K, C>,
		C extends Constraint<T>,
		I extends Condition,
		V extends Variable>
				implements UnificationService<M, T, K, N, C, I, V> {

	/**
	 * AIMA Unifier object to do the underlying expression unification.
	 */
	private aima.core.logic.fol.Unifier unifier;

	/**
	 * Converter to convert between our classes and the AIMA FOL classes.
	 */
	private AIMAConverter<T, V, K> converter;
	
	/**
	 * Factory for creating task network builders.
	 */
	private TaskNetworkBuilderFactory<T, K, N, C> taskNetworkBuilderFactory;
	
	/**
	 * Constructor taking all required dependencies.
	 * @param unifier AIMA unifier object
	 * @param converter converted to translate between the two class schemes
	 * @param domainHelper helper object to deal with the domain
	 * @param taskNetworkBuilderFactory factory for creating task network builders
	 */
	public AIMAUnificationService(aima.core.logic.fol.Unifier unifier,
			AIMAConverter<T, V, K> converter,
			DomainHelper<O, M, T, K, N, C, I> domainHelper,
			TaskNetworkBuilderFactory<T, K, N, C> taskNetworkBuilderFactory) {
		this.unifier = unifier;
		this.converter = converter;
		this.taskNetworkBuilderFactory = taskNetworkBuilderFactory;
	}
	/**
	 * {@inheritDoc}
	 */
	public final Substitution<T, V>	findUnifier(K task, M method) {

		Predicate taskPredicate = converter.convert(task);
		Predicate methodTaskPredicate = converter.convert(method.getTask());
		
		Map<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term> map
				= unifier.unify(taskPredicate, methodTaskPredicate);
		return converter.convert(map);
	}

	/**
	 * {@inheritDoc}
	 */
	public final N apply(
			Substitution<T, V> unifier, 
			N taskNetwork) {
		
		// TODO Add support constraints
		Set<K> updatedTasks = new HashSet<K>();
		for (K task : taskNetwork.getTasks()) {
			K updatedTask = apply(unifier, task);
			updatedTasks.add(updatedTask);
		}

		N updatedTaskNetwork = taskNetworkBuilderFactory.createTaskNetworkBuilder()
				.addTasks(updatedTasks)
				.build();
		
		return updatedTaskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final K apply(Substitution<T, V> unifier, K task) {
		
		List<T> updatedTerms = new ArrayList<T>();		
		for (T term : task.getArguments()) {
			if (unifier.getMap().containsKey(term)) {
				updatedTerms.add(unifier.getMap().get(term));
			} else {
				updatedTerms.add(term);
			}
		}

		K updatedTask = taskNetworkBuilderFactory.createTaskBuilder()
				.setName(task.getName())
				.addArguments(updatedTerms)
				.build();
		
		return updatedTask;
	}

}
