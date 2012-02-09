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

import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.constraint.impl.SimpleAfterConstraint;
import org.gerryai.htn.simple.constraint.impl.SimpleBeforeConstraint;
import org.gerryai.htn.simple.constraint.impl.SimpleBetweenConstraint;
import org.gerryai.htn.simple.constraint.impl.SimpleConstraintFactory;
import org.gerryai.htn.simple.constraint.impl.SimplePrecedenceConstraint;
import org.gerryai.htn.simple.decomposition.SimpleSubstituter;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.domain.impl.SimpleMethod;
import org.gerryai.htn.simple.domain.impl.SimpleOperator;
import org.gerryai.htn.simple.logic.impl.SimpleCondition;
import org.gerryai.htn.simple.logic.impl.SimpleConstant;
import org.gerryai.htn.simple.logic.impl.SimpleTerm;
import org.gerryai.htn.simple.logic.impl.SimpleUnifier;
import org.gerryai.htn.simple.logic.impl.SimpleVariable;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilderFactory;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;
import org.gerryai.htn.simple.tasknetwork.impl.AbstractTaskBuilder;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTaskNetwork;
import org.gerryai.logic.Term;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleSubstituterImpl implements SimpleSubstituter<SimpleTerm, SimpleVariable, SimpleConstant> {

	/**
	 * The unifier containing the substitution to use.
	 */
	private SimpleUnifier unifier;
	
	/**
	 * The domain helper to use.
	 */
	private DomainHelper<SimpleOperator, SimpleMethod, SimpleTerm, SimpleTask,
			SimpleTaskNetwork, ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition> domainHelper;
	
	/**
	 * Constraint factory to use.
	 */
	private SimpleConstraintFactory constraintFactory;
	
	private TaskNetworkBuilderFactory<SimpleTerm, SimpleTask, SimpleTaskNetwork, ValidatableConstraint<SimpleTerm, SimpleTask>> taskNetworkBuilder;
	
	/**
	 * Constructor taking a unifier.
	 * @param unifier the unifier to work with
	 */
	public SimpleSubstituterImpl(SimpleUnifier unifier,
			DomainHelper<SimpleOperator, SimpleMethod, SimpleTerm, SimpleTask,
			SimpleTaskNetwork, ValidatableConstraint<SimpleTerm, SimpleTask>, SimpleCondition> domainHelper,
			SimpleConstraintFactory constraintFactory) {
		this.unifier = unifier;
		this.domainHelper = domainHelper;
		this.constraintFactory = constraintFactory;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTerm apply(SimpleVariable variable) {
		
		//TODO Copy of original?
		
		for (SimpleVariable substitutionVariable : unifier.getMap().keySet()) {
			if (substitutionVariable.equals(variable)) {
				return unifier.getMap().get(substitutionVariable);
			}
		}

		return (SimpleTerm) variable;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTerm apply(SimpleConstant constant) {
		
		//TODO Implement
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleBeforeConstraint apply(SimpleBeforeConstraint constraint) {
		// TODO Implement
		//Condition updatedCondition = apply(constraint.getCondition());
		//return constraintFactory.createBeforeConstraint(constraint.getTasks(), updatedCondition);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleAfterConstraint apply(SimpleAfterConstraint constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleBetweenConstraint apply(SimpleBetweenConstraint constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimplePrecedenceConstraint apply(
			SimplePrecedenceConstraint constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTask apply(SimpleTask task) {
		List<SimpleTerm> updatedTerms = new ArrayList<SimpleTerm>();		
		for (SimpleTerm term : task.getArguments()) {
			if (unifier.getMap().containsKey(term)) {
				updatedTerms.add(unifier.getMap().get(term));
			} else {
				updatedTerms.add(term);
			}
		}

		SimpleTask updatedTask = taskNetworkBuilder.createTaskBuilder()
				.setName(task.getName())
				.addArguments(updatedTerms)
				.build();
		
		return updatedTask;
	}

}
