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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.impl.SimpleConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;

/**
 * Generic builder for task networks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkBuilder
		implements TaskNetworkBuilder<SubstitutableTerm,
        SubstitutableTask, SubstitutableTaskNetwork, SimpleConstraint<?>> {

	/**
	 * Set of tasks we are building up.
	 */
	private Set<SubstitutableTask> tasks;
	
	/**
	 * Set of constraints we are building up.
	 */
	private Set<SimpleConstraint<?>> constraints;
	
	/**
	 * Constraint validator.
	 */
	private ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> constraintValidator;
	
	/**
     * Constructor, taking a constraint validator to use.
     * @param constraintValidator the constraint validator
     */
    SimpleTaskNetworkBuilder(ConstraintValidator<SubstitutableTerm, SubstitutableTask,
            SubstitutableCondition> constraintValidator) {
        this.constraintValidator = constraintValidator;
        tasks = new HashSet<SubstitutableTask>();
        constraints = new HashSet<SimpleConstraint<?>>();
    }
	
	/**
	 * @return the constraintValidator
	 */
	protected final ConstraintValidator<SubstitutableTerm, SubstitutableTask,
	        SubstitutableCondition> getConstraintValidator() {
		return constraintValidator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addTask(SubstitutableTask task) {
		tasks.add(task);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addTasks(Set<SubstitutableTask> tasks) {
		this.tasks.addAll(tasks);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addConstraint(
	        SimpleConstraint<?> constraint) throws InvalidConstraint {
		addConstraintInternal(constraint);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addConstraints(
			Set<SimpleConstraint<?>> constraints) throws InvalidConstraint {
		for (SimpleConstraint<?> constraint : constraints) {
			addConstraintInternal(constraint);
		}
		return this;
	}

	/**
     * {@inheritDoc}
     */
	public final SimpleTaskNetworkBuilder copy(SubstitutableTaskNetwork taskNetwork) throws InvalidConstraint {
	    tasks = new HashSet<SubstitutableTask>(taskNetwork.getTasks());
	    // Assume original task network is valid
	    constraints = new HashSet<SimpleConstraint<?>>(taskNetwork.getConstraints());
	    return this;
	}
	
	/**
     * {@inheritDoc}
     */
    public final SimpleTaskNetworkBuilder apply(Substituter<SubstitutableTerm> substituter) {

        //TODO: Implement
        for (SubstitutableTask task : tasks) {
            // Update every task
            for (SimpleConstraint<?> constraint : constraints) {
                // Update every constraint that refers to the original task
            }
        }
        
        for (SimpleConstraint<?> constraint : constraints) {
            // Update the condition in every constraint
        }

        return this;
    }
    
	/**
	 * {@inheritDoc}
	 */
    public final SubstitutableTaskNetwork build() {
        return new SimpleTaskNetwork(this);
    }
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<SubstitutableTask> getTasks() {
		return tasks;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<SimpleConstraint<?>> getConstraints() {
		return constraints;
	}
	
	/**
	 * Internal helper method to add a single constraint.
	 * Checks the validity of the constraint and updates the validator
	 * @param constraint the constraint
	 * @throws InvalidConstraint if the constraint was invalid
	 */
	private void addConstraintInternal(SimpleConstraint<?> constraint) throws InvalidConstraint {
		if (constraint.validate(getConstraintValidator())) { 
			constraints.add(constraint);
			constraint.add(getConstraintValidator());
		} else {
			throw new InvalidConstraint();
		}
	}

}
