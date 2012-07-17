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
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilder;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Simple immutable implementation of a task network.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetwork implements TaskNetwork {

    /**
     * Constraint validator factory.
     */
    private ConstraintValidatorFactory constraintValidatorFactory;
    
	/**
	 * Set of tasks to be solved in this network.
	 */
	private Set<Task> tasks;
	
	/**
	 * Set of before constraints to be met.
	 */
	private Set<BeforeConstraint> beforeConstraints;

	/**
	 * Set of after constraints to be met.
	 */
	private Set<AfterConstraint> afterConstraints;

	/**
	 * Set of between constraints to be met.
	 */
	private Set<BetweenConstraint> betweenConstraints;

	/**
	 * Set of precedence constraints to be met.
	 */
	private Set<PrecedenceConstraint> precedenceConstraints;
	
	/**
	 * Constructor for a simple task.
	 * @param builder the builder to build the task
	 */
	protected SimpleTaskNetwork(Builder builder) {
		this.constraintValidatorFactory = builder.constraintValidatorFactory;
		this.tasks = builder.constraintValidator.getTasks();
		this.beforeConstraints = builder.constraintValidator.getBeforeConstraints();
		this.afterConstraints = builder.constraintValidator.getAfterConstraints();
		this.betweenConstraints = builder.constraintValidator.getBetweenConstraints();
		this.precedenceConstraints = builder.constraintValidator.getPrecedenceConstraints();
	}
	
	@Override
	public final Set<Task> getTasks() {
		return Collections.unmodifiableSet(tasks);
	}

	@Override
	public final Set<BeforeConstraint> getBeforeConstraints() {
		return Collections.unmodifiableSet(beforeConstraints);
	}

	@Override
	public final Set<AfterConstraint> getAfterConstraints() {
		return Collections.unmodifiableSet(afterConstraints);
	}

	@Override
	public final Set<BetweenConstraint> getBetweenConstraints() {
		return Collections.unmodifiableSet(betweenConstraints);
	}

	@Override
	public final Set<PrecedenceConstraint> getPrecedenceConstraints() {
		return Collections.unmodifiableSet(precedenceConstraints);
	}
	
	@Override
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
	
	@Override
    public final TaskNetwork apply(Map<Term, Term> substitution) throws InvalidConstraint {
        return new Builder(constraintValidatorFactory, this)
            .apply(substitution)
            .build();
    }

	@Override
    public final TaskNetwork replace(Task task, TaskNetwork taskNetwork) throws InvalidConstraint {
        return new Builder(constraintValidatorFactory, this)
            .replace(task, taskNetwork)
            .build();
    }
	
	/**
	 * Builder for simple task networks.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
	public static class Builder implements TaskNetworkBuilder {

        /**
         * Constraint validator factory.
         */
        private ConstraintValidatorFactory constraintValidatorFactory;
        
        /**
         * Constraint validator.
         */
        private ConstraintValidator constraintValidator;
        
        /**
         * Constructor, taking a constraint validator factory to use.
         * @param constraintValidatorFactory a factory for creating constraint validators
         */
        public Builder(ConstraintValidatorFactory constraintValidatorFactory) {
        	this.constraintValidatorFactory = constraintValidatorFactory;
        	constraintValidator = constraintValidatorFactory.create();
        }
        
        /**
         * Constructor copying an existing task network.
         * @param constraintValidatorFactory a factory for creating constraint validators
         * @param taskNetwork the task network to copy
         * @throws InvalidConstraint only if the original task network was invalid
         */
        public Builder(ConstraintValidatorFactory constraintValidatorFactory,
        		TaskNetwork taskNetwork) throws InvalidConstraint {
        	this.constraintValidatorFactory = constraintValidatorFactory;
        	constraintValidator = constraintValidatorFactory.create();
        	
        	for (Task task : taskNetwork.getTasks()) {
        		constraintValidator.add(task);
        	}
        	for (BeforeConstraint constraint : taskNetwork.getBeforeConstraints()) {
        		constraintValidator.add(constraint);
        	}
        	for (AfterConstraint constraint : taskNetwork.getAfterConstraints()) {
        		constraintValidator.add(constraint);
        	}
        	for (BetweenConstraint constraint : taskNetwork.getBetweenConstraints()) {
        		constraintValidator.add(constraint);
        	}
        	for (PrecedenceConstraint constraint : taskNetwork.getPrecedenceConstraints()) {
        		constraintValidator.add(constraint);
        	}
        }

		/**
         * @return the constraintValidator
         *
        protected final ConstraintValidator getConstraintValidator() {
            return constraintValidator;
        }*/
        
        /**
         * {@inheritDoc}
         */
        public final Builder addTask(Task task) {
            constraintValidator.add(task);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addTasks(Set<Task> tasks) {
            for (Task task : tasks) {
                constraintValidator.add(task);
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addBeforeConstraint(BeforeConstraint constraint) throws InvalidConstraint {
        	constraintValidator.add(constraint);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addAfterConstraint(AfterConstraint constraint) throws InvalidConstraint {
        	constraintValidator.add(constraint);
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final Builder addBetweenConstraint(BetweenConstraint constraint) throws InvalidConstraint {
        	constraintValidator.add(constraint);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder addPrecedenceConstraint(PrecedenceConstraint constraint) throws InvalidConstraint {
        	constraintValidator.add(constraint);
            return this;
        }
        
        @Override
        public final Builder apply(Map<Term, Term> substitution) throws InvalidConstraint {
        	constraintValidator.apply(substitution);            
            return this;
        }

        @Override
        public final Builder replace(Task oldTask, TaskNetwork newTaskNetwork) throws InvalidConstraint {
        	constraintValidator.replace(oldTask, newTaskNetwork);
            return this;
        }
        
        @Override
        public final TaskNetwork build() {
            return new SimpleTaskNetwork(this);
        }
	}
	
	@Override
	public final String toString() {
	    return new StringBuilder()
	        .append(tasks.toString())
	        .append(',')
	        .append(beforeConstraints.toString())
	        .append(',')
	        .append(afterConstraints.toString())
	        .append(',')
	        .append(betweenConstraints.toString())
	        .append(',')
	        .append(precedenceConstraints.toString())
	        .toString();
	}
}
