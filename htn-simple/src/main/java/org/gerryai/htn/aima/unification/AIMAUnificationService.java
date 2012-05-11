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
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkFactory;
import org.gerryai.logic.Variable;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * Unification service that use an AIMA unifier underneath.
 * @param <O> the type of operator this service works with
 * @param <M> the type of method this service works with
 * @param <I> the class of condition the builder will handle
 * @param <V> the type of variable this service works with
 * @author David Edwards <david@more.fool.me.uk>
 */
public class AIMAUnificationService<
		O extends Operator<I>,
		M extends Method<SubstitutableTerm, ImmutableTask, ImmutableTaskNetwork, ImmutableConstraint<?>>,
		I extends Condition,
		V extends Variable>
				implements UnificationService<M, SubstitutableTerm, ImmutableTask,
				ImmutableTaskNetwork, ImmutableConstraint<?>, I> {

	/**
	 * AIMA Unifier object to do the underlying expression unification.
	 */
	private aima.core.logic.fol.Unifier unifier;

	/**
	 * Converter to convert between our classes and the AIMA FOL classes.
	 */
	private AIMAConverter<SubstitutableTerm, V, ImmutableTask> converter;
	
	/**
	 * Factory for creating task network builders.
	 */
	private TaskNetworkFactory<SubstitutableTerm, ImmutableTask,
	        ImmutableTaskNetwork, ImmutableConstraint<?>, ImmutableSubstitution> taskNetworkBuilderFactory;
	
	/**
	 * Constructor taking all required dependencies.
	 * @param unifier AIMA unifier object
	 * @param converter converted to translate between the two class schemes
	 * @param domainHelper helper object to deal with the domain
	 * @param taskNetworkBuilderFactory factory for creating task network builders
	 */
	public AIMAUnificationService(aima.core.logic.fol.Unifier unifier,
			AIMAConverter<SubstitutableTerm, V, ImmutableTask> converter,
			DomainHelper<O, M, SubstitutableTerm, ImmutableTask, ImmutableTaskNetwork,
			        ImmutableConstraint<?>, I> domainHelper,
			TaskNetworkFactory<SubstitutableTerm, ImmutableTask,  ImmutableTaskNetwork,
			        ImmutableConstraint<?>, ImmutableSubstitution> taskNetworkBuilderFactory) {
		this.unifier = unifier;
		this.converter = converter;
		this.taskNetworkBuilderFactory = taskNetworkBuilderFactory;
	}
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableSubstitution findUnifier(ImmutableTask task, M method) {

		Predicate taskPredicate = converter.convert(task);
		Predicate methodTaskPredicate = converter.convert(method.getTask());
		
		Map<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term> map
				= unifier.unify(taskPredicate, methodTaskPredicate);
		return converter.convert(map);
	}

	/**
	 * {@inheritDoc}
	 * @throws InvalidConstraint 
	 */
	public final ImmutableTaskNetwork apply(ImmutableSubstitution unifier, 
			ImmutableTaskNetwork taskNetwork) throws InvalidConstraint {
		
		// TODO Add support constraints
		Set<ImmutableTask> updatedTasks = new HashSet<ImmutableTask>();
		for (ImmutableTask task : taskNetwork.getTasks()) {
		    ImmutableTask updatedTask = apply(unifier, task);
			updatedTasks.add(updatedTask);
		}

		ImmutableTaskNetwork updatedTaskNetwork = taskNetworkBuilderFactory.createTaskNetwork(
		        updatedTasks, taskNetwork.getConstraints());
		
		return updatedTaskNetwork;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableTask apply(ImmutableSubstitution unifier, ImmutableTask task) {
		
		List<SubstitutableTerm> updatedTerms = new ArrayList<SubstitutableTerm>();		
		for (SubstitutableTerm term : task.getArguments()) {
			if (unifier.getMap().containsKey(term)) {
				updatedTerms.add(unifier.getMap().get(term));
			} else {
				updatedTerms.add(term);
			}
		}

		ImmutableTask updatedTask = taskNetworkBuilderFactory.createTask(
		        task.getName(), updatedTerms, task.isPrimitive());
		
		return updatedTask;
	}

}
