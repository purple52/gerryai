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
package org.gerryai.htn.simple.constraint.impl;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;

import com.google.common.base.Objects;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleBetweenConstraint implements SimpleConstraint<SimpleBetweenConstraint>,
		ValidatableBetweenConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> {

	/**
	 * The set of tasks that this constraint must hold after.
	 */
	private Set<SubstitutableTask> precedingTasks;

	/**
	 * The set of tasks that this constraint must hold before.
	 */
	private Set<SubstitutableTask> procedingTasks;
	
	/**
	 * The literal that must be true directly between the two sets of tasks.
	 */
	private SubstitutableCondition condition;
	
    /**
     * Constructor.
     * @param builder the builder to build from
     */
    protected SimpleBetweenConstraint(Builder builder) {
        precedingTasks = builder.getPrecedingTasks();
        procedingTasks = builder.getProcedingTasks();
        condition = builder.getCondition();
    }
	
	/**
	 * Get the set of tasks that this constraint must hold after.
	 * @return the tasks
	 */
	public final Set<SubstitutableTask> getPrecedingTasks() {
		return precedingTasks;
	}

	/**
	 * Get the set of tasks that this constraint must hold before.
	 * @return the tasks
	 */
	public final Set<SubstitutableTask> getProcedingTasks() {
		return procedingTasks;
	}
	
	/**
	 * Get the condition that must be true directly after the last of these tasks.
	 * @return the condition
	 */
	public final SubstitutableCondition getCondition() {
		return condition;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ConstraintValidator<SubstitutableTerm, SubstitutableTask,
			SubstitutableCondition> validator) {
		return validator.validate(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> validator)
			throws InvalidConstraint {
		validator.add(this);
	}
	
    /**
     * {@inheritDoc}
     */
    public final void apply(Substituter<SubstitutableTerm> substituter) {
        condition.apply(substituter);
    }
    
    /**
     * {@inheritDoc}
     */
    public final SimpleConstraintBuilder<SimpleBetweenConstraint> createBuilder() {
        return new Builder();
    }
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(precedingTasks, procedingTasks, condition);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimpleBetweenConstraint) {
	        final SimpleBetweenConstraint other = (SimpleBetweenConstraint) obj;
	        return Objects.equal(precedingTasks, other.precedingTasks)
	        	&& Objects.equal(procedingTasks, other.procedingTasks)
	            && Objects.equal(condition, other.condition);
	    } else {
	        return false;
	    }
	}
	
	/**
     * Builder class for SimpleBetweenConstraint.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements SimpleConstraintBuilder<SimpleBetweenConstraint> {
        
        /**
         * The set of tasks that this constraint must hold after.
         */
        private Set<SubstitutableTask> precedingTasks;

        /**
         * The set of tasks that this constraint must hold before.
         */
        private Set<SubstitutableTask> procedingTasks;
        
        /**
         * The condition that must be true directly after the last of these tasks.
         */
        private SubstitutableCondition condition;
        
        /**
         * Default constructor.
         */
        public Builder() {
            precedingTasks = new HashSet<SubstitutableTask>();
            procedingTasks = new HashSet<SubstitutableTask>();
        }

        /**
         * @param tasks the tasks to add
         * @return the updated builder
         */
        public final Builder addPrecedingTasks(Set<SubstitutableTask> tasks) {
            this.precedingTasks.addAll(tasks);
            return this;
        }

        /**
         * @param tasks the tasks to add
         * @return the updated builder
         */
        public final Builder addProcedingTasks(Set<SubstitutableTask> tasks) {
            this.procedingTasks.addAll(tasks);
            return this;
        }
        
        /**
         * @param condition the condition to set
         * @return the updated builder
         */
        public final Builder setCondition(SubstitutableCondition condition) {
            this.condition = condition;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final Builder copy(SimpleBetweenConstraint constraint) {
            this.precedingTasks = new HashSet<SubstitutableTask>(constraint.getPrecedingTasks());
            this.procedingTasks = new HashSet<SubstitutableTask>(constraint.getProcedingTasks());
            this.condition = constraint.getCondition();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final Builder replace(SubstitutableTask oldTask, Set<SubstitutableTask> newTasks) {
            if (this.precedingTasks.contains(oldTask)) {
                this.precedingTasks.remove(oldTask);
                this.precedingTasks.addAll(newTasks);
            }
            if (this.procedingTasks.contains(oldTask)) {
                this.procedingTasks.remove(oldTask);
                this.procedingTasks.addAll(newTasks);
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder apply(Substituter<SubstitutableTerm> substituter) {
            this.condition.apply(substituter);
            return this;
        }    
        
        /**
         * @return the tasks
         */
        protected final Set<SubstitutableTask> getPrecedingTasks() {
            return precedingTasks;
        }

        /**
         * @return the tasks
         */
        protected final Set<SubstitutableTask> getProcedingTasks() {
            return procedingTasks;
        }
        
        /**
         * @return the condition
         */
        protected final SubstitutableCondition getCondition() {
            return condition;
        }
        
        /**
         * Build the constraint.
         * @return the finished constraint
         */
        public final SimpleBetweenConstraint build() {
            return new SimpleBetweenConstraint(this);
        }
    }
}
