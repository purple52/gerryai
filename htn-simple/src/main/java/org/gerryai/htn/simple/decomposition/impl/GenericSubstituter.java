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
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.simple.constraint.SubstitutableConstraint;
import org.gerryai.htn.simple.constraint.SubstitutableConstraintFactory;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableConstant;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.SubstitutableVariable;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetworkBuilderFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.gerryai.logic.unification.Substitution;

/**
 * Generic class for doing substitutions using the visitor pattern.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class GenericSubstituter implements Substituter<SubstitutableTerm> {

	/**
	 * The unifier containing the substitution to use.
	 */
	private Substitution<SubstitutableTerm, SubstitutableVariable> unifier;
	
	/**
	 * Constraint factory to use.
	 */
	private SubstitutableConstraintFactory constraintFactory;
	
	/**
	 * Factory for building task and task networks.
	 */
	private SubstitutableTaskNetworkBuilderFactory taskNetworkBuilderFactory;
	
	/**
	 * Constructor taking a unifier.
	 * @param unifier the unifier to work with
	 * @param constraintFactory the factory for creating new tasks and task networks
	 */
	public GenericSubstituter(Substitution<SubstitutableTerm, SubstitutableVariable> unifier,
			SubstitutableConstraintFactory constraintFactory,
			SubstitutableTaskNetworkBuilderFactory taskNetworkBuilderFactory) {
		this.unifier = unifier;
		this.constraintFactory = constraintFactory;
		this.taskNetworkBuilderFactory = taskNetworkBuilderFactory;
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
	public final SubstitutableConstraint<SubstitutableTerm>
			apply(SubstitutableConstraint<SubstitutableTerm> constraint) {
		// TODO Implement
		//Condition updatedCondition = apply(constraint.getCondition());
		//return constraintFactory.createBeforeConstraint(constraint.getTasks(), updatedCondition);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final AfterConstraint<Term, Task<Term>, SubstitutableCondition>
			apply(AfterConstraint<Term, Task<Term>, SubstitutableCondition> constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final BetweenConstraint<Term, Task<Term>, SubstitutableCondition>
			apply(BetweenConstraint<Term, Task<Term>, SubstitutableCondition> constraint) {
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
	public final SubstitutableTask apply(SubstitutableTask task) {
		List<SubstitutableTerm> updatedTerms = new ArrayList<SubstitutableTerm>();		
		//TODO: Implement

		SubstitutableTask updatedTask = taskNetworkBuilderFactory.createTaskBuilder()
				.setName(task.getName())
				.addArguments(updatedTerms)
				.build();
		
		return updatedTask;
	}

}
