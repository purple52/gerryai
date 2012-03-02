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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkBuilder;
import org.gerryai.htn.tasknetwork.Task;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskNetwork implements ImmutableTaskNetwork {

	/**
	 * Set of tasks to be solved in this network.
	 */
	private Set<ImmutableTask> tasks;
	
	/**
	 * Set of constraints to be met.
	 */
	private Set<ImmutableConstraint<?>> constraints;
	
	/**
	 * Constructor for a simple task.
	 * @param builder the builder to build the task
	 */
	protected SimpleTaskNetwork(ImmutableTaskNetworkBuilder builder) {
		this.tasks = builder.getTasks();
		this.constraints = builder.getConstraints();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableTask> getTasks() {
		return Collections.unmodifiableSet(tasks);
	}

	/**
	 * {@inheritDoc}
	 *
	public final void setTasks(Set<SubstitutableTask> tasks) {
		this.tasks = tasks;
	}
    */
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableConstraint<?>> getConstraints() {
		return Collections.unmodifiableSet(constraints);
	}

	/**
	 * {@inheritDoc}
	 *
	public final void setConstraints(Set<SubstitutableValidatableConstraint> constraints) {
		this.constraints = constraints;
	}
	*/
	
    /**
     * {@inheritDoc}
     */
    public final void apply(Substituter<SubstitutableTerm> substituter) {
        Set<ImmutableTask> newTasks = new HashSet<ImmutableTask>(tasks.size());
        for (ImmutableTask task : tasks) {
            ImmutableTask newTask = task.createCopyBuilder()
                    .apply(substituter)
                    .build();
            newTasks.add(newTask);
        }
        tasks = newTasks;
        Set<ImmutableConstraint<?>> newConstraints = new HashSet<ImmutableConstraint<?>>(constraints.size());
        for (ImmutableConstraint<?> constraint : constraints) {
            ImmutableConstraint<?> replacementConstraint = constraint.createCopyBuilder()
                    .apply(substituter)
                    .build();
            newConstraints.add(replacementConstraint);
        }
        constraints = newConstraints;
    }

	/**
	 * {@inheritDoc}
	 */
	public final boolean isPrimitive() {
		
		for (Task<SubstitutableTerm> task : getTasks()) {
			if (!task.isPrimitive()) {
				// If any of our tasks are non-primitive, the whole network is non-primitive
				return false;
			}
		}
		
		// None of our tasks were non-primitive, so the whole network is primitive
		return true;
	}

	public static class Builder implements ImmutableTaskNetworkBuilder {

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
        public Builder(ConstraintValidator<SubstitutableTerm, ImmutableTask,
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
        public final Builder addTask(ImmutableTask task) {
            tasks.add(task);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addTasks(Set<ImmutableTask> tasks) {
            this.tasks.addAll(tasks);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addConstraint(
                ImmutableConstraint<?> constraint) throws InvalidConstraint {
            addConstraintInternal(constraint);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addConstraints(
                Set<ImmutableConstraint<?>> constraints) throws InvalidConstraint {
            for (ImmutableConstraint<?> constraint : constraints) {
                addConstraintInternal(constraint);
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder copy(ImmutableTaskNetwork taskNetwork) throws InvalidConstraint {
            tasks = new HashSet<ImmutableTask>(taskNetwork.getTasks());
            // Assume original task network is valid
            constraints = new HashSet<ImmutableConstraint<?>>(taskNetwork.getConstraints());
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder apply(Substituter<SubstitutableTerm> substituter) {
        
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
        public final ImmutableTaskNetwork build() {
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
}
