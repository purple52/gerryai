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
package org.gerryai.htn.simple.decomposition.impl;

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.simple.constraint.ConstraintFactory;
import org.gerryai.htn.simple.constraint.SubstitutableConstraint;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableConstant;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.SubstitutableVariable;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilderFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;
import org.gerryai.logic.unification.Substitution;

/**
 * Generic class for doing substitutions using the visitor pattern.
 * @param <T> type of logical term required
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class GenericSubstituter<T extends SubstitutableTerm> implements Substituter<T> {

	/**
	 * The unifier containing the substitution to use.
	 */
	private Substitution<T, SubstitutableVariable> unifier;
	
	/**
	 * Constraint factory to use.
	 */
	//private ConstraintFactory<T, SubstitutableTask<T>, SubstitutableCondition> constraintFactory;
	
	/**
	 * Factory for building task and task networks.
	 */
	private TaskNetworkBuilderFactory<T,
			SubstitutableTask<T>,
			TaskNetwork<T, SubstitutableTask<T>,
			Constraint<T>>,
			Constraint<T>>
			taskNetworkBuilderFactory;
	
	/**
	 * Constructor taking a unifier.
	 * @param unifier the unifier to work with
	 * @param constraintFactory the factory for creating new tasks and task networks
	 */
	public GenericSubstituter(Substitution<T, SubstitutableVariable> unifier,
			ConstraintFactory<T, SubstitutableTask<T>, SubstitutableCondition> constraintFactory) {
		this.unifier = unifier;
		//this.constraintFactory = constraintFactory;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTerm apply(SubstitutableVariable variable) {
		
		//TODO Copy of original?
		
		for (SubstitutableVariable substitutionVariable : unifier.getMap().keySet()) {
			if (substitutionVariable.equals(variable)) {
				return unifier.getMap().get(substitutionVariable);
			}
		}

		return variable;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTerm apply(SubstitutableConstant constant) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTerm apply(SubstitutableTerm term) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableConstraint<T> apply(SubstitutableConstraint<T> constraint) {
		// TODO Implement
		//Condition updatedCondition = apply(constraint.getCondition());
		//return constraintFactory.createBeforeConstraint(constraint.getTasks(), updatedCondition);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final AfterConstraint<Term, Task<Term>, SubstitutableCondition> apply(AfterConstraint<Term, Task<Term>, SubstitutableCondition> constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final BetweenConstraint<Term, Task<Term>, SubstitutableCondition> apply(BetweenConstraint<Term, Task<Term>, SubstitutableCondition> constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final PrecedenceConstraint<Term, Task<Term>> apply(
			PrecedenceConstraint<Term, Task<Term>> constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTask<T> apply(SubstitutableTask<T> task) {
		List<T> updatedTerms = new ArrayList<T>();		
		//TODO: Implement

		SubstitutableTask<T> updatedTask = taskNetworkBuilderFactory.createTaskBuilder()
				.setName(task.getName())
				.addArguments(updatedTerms)
				.build();
		
		return updatedTask;
	}

}
