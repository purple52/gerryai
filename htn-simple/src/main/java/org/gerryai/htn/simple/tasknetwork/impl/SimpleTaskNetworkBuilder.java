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

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;

/**
 * Generic builder for task networks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkBuilder
		implements TaskNetworkBuilder<SubstitutableTerm,
        ImmutableTask, SubstitutableTaskNetwork, ImmutableConstraint<?>> {

	/**
	 * Set of tasks we are building up.
	 */
	private Set<ImmutableTask> tasks;
	
	/**
	 * Set of constraints we are building up.
	 */
	private Set<ImmutableConstraint<?>> constraints;
	
	/**
	 * Constraint validator.
	 */
	private ConstraintValidator<SubstitutableTerm, ImmutableTask, SubstitutableCondition> constraintValidator;
	
	/**
     * Constructor, taking a constraint validator to use.
     * @param constraintValidator the constraint validator
     */
    SimpleTaskNetworkBuilder(ConstraintValidator<SubstitutableTerm, ImmutableTask,
            SubstitutableCondition> constraintValidator) {
        this.constraintValidator = constraintValidator;
        tasks = new HashSet<ImmutableTask>();
        constraints = new HashSet<ImmutableConstraint<?>>();
    }
	
	/**
	 * @return the constraintValidator
	 */
	protected final ConstraintValidator<SubstitutableTerm, ImmutableTask,
	        SubstitutableCondition> getConstraintValidator() {
		return constraintValidator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addTask(ImmutableTask task) {
		tasks.add(task);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addTasks(Set<ImmutableTask> tasks) {
		this.tasks.addAll(tasks);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addConstraint(
	        ImmutableConstraint<?> constraint) throws InvalidConstraint {
		addConstraintInternal(constraint);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final SimpleTaskNetworkBuilder addConstraints(
			Set<ImmutableConstraint<?>> constraints) throws InvalidConstraint {
		for (ImmutableConstraint<?> constraint : constraints) {
			addConstraintInternal(constraint);
		}
		return this;
	}

	/**
     * {@inheritDoc}
     */
	public final SimpleTaskNetworkBuilder copy(SubstitutableTaskNetwork taskNetwork) throws InvalidConstraint {
	    tasks = new HashSet<ImmutableTask>(taskNetwork.getTasks());
	    // Assume original task network is valid
	    constraints = new HashSet<ImmutableConstraint<?>>(taskNetwork.getConstraints());
	    return this;
	}
	
	/**
     * {@inheritDoc}
     */
    public final SimpleTaskNetworkBuilder apply(Substituter<SubstitutableTerm> substituter) {

        //TODO: Implement
        for (ImmutableTask task : tasks) {
            // Update every task
            for (ImmutableConstraint<?> constraint : constraints) {
                // Update every constraint that refers to the original task
            }
        }
        
        for (ImmutableConstraint<?> constraint : constraints) {
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
	public final Set<ImmutableTask> getTasks() {
		return tasks;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableConstraint<?>> getConstraints() {
		return constraints;
	}
	
	/**
	 * Internal helper method to add a single constraint.
	 * Checks the validity of the constraint and updates the validator
	 * @param constraint the constraint
	 * @throws InvalidConstraint if the constraint was invalid
	 */
	private void addConstraintInternal(ImmutableConstraint<?> constraint) throws InvalidConstraint {
		if (constraint.validate(getConstraintValidator())) { 
			constraints.add(constraint);
			constraint.add(getConstraintValidator());
		} else {
			throw new InvalidConstraint();
		}
	}

}
