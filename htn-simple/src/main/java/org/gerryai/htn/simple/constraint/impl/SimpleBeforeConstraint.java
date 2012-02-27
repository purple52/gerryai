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

import org.gerryai.htn.simple.constraint.ValidatableBeforeConstraint;
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
public class SimpleBeforeConstraint implements SimpleConstraint<SimpleBeforeConstraint>,
        ValidatableBeforeConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> {

    /**
     * The set of tasks that this constraint must hold for.
     */
    private Set<SubstitutableTask> tasks;

    /**
     * The condition that must be true directly before the first of these tasks.
     */
    private SubstitutableCondition condition;

    /**
     * Constructor.
     * @param builder the builder to build from
     */
    protected SimpleBeforeConstraint(Builder builder) {
        tasks = builder.getTasks();
        condition = builder.getCondition();
    }

    /**
     * Get the set of tasks that this constraint must hold for.
     * 
     * @return the tasks
     */
    public final Set<SubstitutableTask> getTasks() {
        return tasks;
    }

    /**
     * Get the condition that must be true directly before the first of these
     * tasks.
     * 
     * @return the condition
     */
    public final SubstitutableCondition getCondition() {
        return condition;
    }

    /**
     * {@inheritDoc}
     */
    public final boolean validate(
            ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> validator) {
        return validator.validate(this);
    }

    /**
     * {@inheritDoc}
     */
    public final void add(
            ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> validator)
            throws InvalidConstraint {
        validator.add(this);
    }

    /**
     * {@inheritDoc}
     */
    public final void apply(Substituter<SubstitutableTerm> substituter) {
        condition.apply(substituter);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(tasks, condition);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof SimpleBeforeConstraint) {
            final SimpleBeforeConstraint other = (SimpleBeforeConstraint) obj;
            return Objects.equal(tasks, other.tasks)
                    && Objects.equal(condition, other.condition);
        } else {
            return false;
        }
    }

    /**
     * Builder class for SimpleBeforeConstraint.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements SimpleConstraintBuilder<SimpleBeforeConstraint> {
        
        /**
         * The set of tasks that this constraint must hold for.
         */
        private Set<SubstitutableTask> tasks;
        
        /**
         * The condition that must be true directly after the last of these tasks.
         */
        private SubstitutableCondition condition;
        
        /**
         * Default constructor.
         */
        public Builder() {
            tasks = new HashSet<SubstitutableTask>();
        }

        /**
         * @param tasks the tasks to add
         * @return the updated builder
         */
        public final Builder addTasks(Set<SubstitutableTask> tasks) {
            this.tasks.addAll(tasks);
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
         * @return the tasks
         */
        protected final Set<SubstitutableTask> getTasks() {
            return tasks;
        }
        
        /**
         * @return the condition
         */
        protected final SubstitutableCondition getCondition() {
            return condition;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder copy(SimpleBeforeConstraint constraint) {
            this.tasks = new HashSet<SubstitutableTask>(constraint.getTasks());
            this.condition = constraint.getCondition();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final Builder replace(SubstitutableTask oldTask, Set<SubstitutableTask> newTasks) {
            this.tasks.remove(oldTask);
            this.tasks.addAll(newTasks);
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
         * Build the constraint.
         * @return the finished constraint
         */
        public final SimpleBeforeConstraint build() {
            return new SimpleBeforeConstraint(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final SimpleConstraintBuilder<SimpleBeforeConstraint> createBuilder() {
        return new Builder();
    }
}
