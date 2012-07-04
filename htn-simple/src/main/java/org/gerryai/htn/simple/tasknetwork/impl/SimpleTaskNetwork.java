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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.ImmutableConstraintBuilder;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.logic.Term;
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
	 */
	public final Set<ImmutableConstraint<?>> getConstraints() {
		return Collections.unmodifiableSet(constraints);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isPrimitive() {
		
		for (Task task : getTasks()) {
			if (!task.isPrimitive()) {
				// If any of our tasks are non-primitive, the whole network is non-primitive
				return false;
			}
		}
		
		// None of our tasks were non-primitive, so the whole network is primitive
		return true;
	}
	
	/**
     * {@inheritDoc}
     */
    public final Builder createCopyBuilder(ConstraintValidator<ImmutableTask,
            ImmutableCondition> constraintValidator) throws InvalidConstraint {
        return new Builder(constraintValidator)
            .copy(this);
    }

	/**
	 * Builder for simple task networks.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
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
        private ConstraintValidator<ImmutableTask, ImmutableCondition> constraintValidator;
        
        /**
         * Constructor, taking a constraint validator to use.
         * @param constraintValidator the constraint validator
         */
        public Builder(ConstraintValidator<ImmutableTask,
                ImmutableCondition> constraintValidator) {
            this.constraintValidator = constraintValidator;
            tasks = new HashSet<ImmutableTask>();
            constraints = new HashSet<ImmutableConstraint<?>>();
        }
        
        /**
         * @return the constraintValidator
         */
        protected final ConstraintValidator<ImmutableTask,
                ImmutableCondition> getConstraintValidator() {
            return constraintValidator;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addTask(ImmutableTask task) {
            tasks.add(task);
            constraintValidator.add(task);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addTasks(Set<ImmutableTask> tasks) {
            this.tasks.addAll(tasks);
            for (ImmutableTask task : tasks) {
                constraintValidator.add(task);
            }
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
        public final Builder apply(Map<Term, Term> substitution) {
        
            // Build a map of tasks to their replacements
            Map<ImmutableTask, ImmutableTask> taskReplacementMap =
                    new HashMap<ImmutableTask, ImmutableTask>(tasks.size());
            for (ImmutableTask task : tasks) {
                ImmutableTask newTask = task.createCopyBuilder()
                        .apply(substitution)
                        .build();
                taskReplacementMap.put(task, newTask);
            }
            // Replace the existing tasks in this builder
            tasks = new HashSet<ImmutableTask>(taskReplacementMap.values());
            
            // Build a set of replacement constraints
            Set<ImmutableConstraint<?>> newConstraints = new HashSet<ImmutableConstraint<?>>(constraints.size());
            for (ImmutableConstraint<?> constraint : constraints) {
                ImmutableConstraintBuilder<?> builder = constraint.createCopyBuilder()
                        .apply(substitution);
                // Replace the tasks in the constraint with the new ones
                for (ImmutableTask task : taskReplacementMap.keySet()) {
                    builder = builder.replace(task, taskReplacementMap.get(task));
                }
                newConstraints.add(builder.build());
            }
            constraints = newConstraints;
        
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final Builder replace(ImmutableTask oldTask, ImmutableTaskNetwork newTaskNetwork) {
            //TODO: Check implementation
            
            // Build a new set of tasks
            Set<ImmutableTask> newTasks = new HashSet<ImmutableTask>(tasks.size() + newTaskNetwork.getTasks().size());
            for (ImmutableTask task : tasks) {
                if (!task.equals(oldTask)) {
                    newTasks.add(task);
                } else {
                    newTasks.addAll(newTaskNetwork.getTasks());
                }
            }
            
            // Update existing constraints
            int numConstraints = constraints.size() + newTaskNetwork.getConstraints().size();
            Set<ImmutableConstraint<?>> newConstraints = new HashSet<ImmutableConstraint<?>>(numConstraints);
            for (ImmutableConstraint<?> oldConstraint : constraints) {
                ImmutableConstraint<?> newConstraint = oldConstraint.createCopyBuilder()
                        .replace(oldTask, newTaskNetwork.getTasks())
                        .build();
                newConstraints.add(newConstraint);
            }
            
            // Add existing constraints
            newConstraints.addAll(newTaskNetwork.getConstraints());
            
            tasks = newTasks;
            constraints = newConstraints;
            
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
	
	@Override
	public final String toString() {
	    return new StringBuilder()
	        .append(tasks.toString())
	        .append(',')
	        .append(constraints.toString())
	        .toString();
	}
}
