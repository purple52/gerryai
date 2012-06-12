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

import org.gerryai.htn.simple.constraint.ImmutableConstraintBuilder;
import org.gerryai.htn.simple.constraint.ImmutableValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;

import com.google.common.base.Objects;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePrecedenceConstraint	implements ImmutableValidatablePrecedenceConstraint {

	/**
	 * The task that must come first.
	 */
	private Set<ImmutableTask> precedingTasks;
	
	/**
	 * The task that must come last.
	 */
	private Set<ImmutableTask> procedingTasks;
	
    /**
     * Constructor.
     * @param builder the builder to build from
     */
    protected SimplePrecedenceConstraint(Builder builder) {
        precedingTasks = builder.getPrecedingTasks();
        procedingTasks = builder.getProcedingTasks();
    }
	
	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableTask> getPrecedingTasks() {
		return precedingTasks;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<ImmutableTask> getProcedingTasks() {
		return procedingTasks;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ConstraintValidator<ImmutableTerm<?>, ImmutableTask,
	        ImmutableCondition> validator) {
		return validator.validate(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ConstraintValidator<ImmutableTerm<?>, ImmutableTask,
	        ImmutableCondition> validator) throws InvalidConstraint {
		validator.add(this);
	}
	
    /**
     * {@inheritDoc}
     */
    public final ImmutableConstraintBuilder<ImmutableValidatablePrecedenceConstraint> createCopyBuilder() {
        return new Builder()
            .copy(this);
    }
    
	@Override
	public final int hashCode() {
		return Objects.hashCode(precedingTasks, procedingTasks);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimplePrecedenceConstraint) {
	        final SimplePrecedenceConstraint other = (SimplePrecedenceConstraint) obj;
	        return Objects.equal(precedingTasks, other.precedingTasks)
	            && Objects.equal(procedingTasks, other.procedingTasks);
	    } else {
	        return false;
	    }
	}
	
	  /**
     * Builder class for SimpleBetweenConstraint.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements ImmutableConstraintBuilder<ImmutableValidatablePrecedenceConstraint> {
        
        /**
         * The task that must come first.
         */
        private Set<ImmutableTask> precedingTasks;

        /**
         * The task that must come last.
         */
        private Set<ImmutableTask> procedingTasks;
        
        /**
         * @param tasks the tasks to set
         * @return the updated builder
         */
        public final Builder setPrecedingTasks(Set<ImmutableTask> tasks) {
            this.precedingTasks = tasks;
            return this;
        }

        /**
         * @param tasks the tasks to set
         * @return the updated builder
         */
        public final Builder setProcedingTasks(Set<ImmutableTask> tasks) {
            this.procedingTasks = tasks;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder copy(ImmutableValidatablePrecedenceConstraint constraint) {
            this.precedingTasks = new HashSet<ImmutableTask>(constraint.getPrecedingTasks());
            this.procedingTasks = new HashSet<ImmutableTask>(constraint.getProcedingTasks());
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final Builder replace(ImmutableTask oldTask, Set<ImmutableTask> newTasks) {
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
        public final Builder replace(ImmutableTask oldTask, ImmutableTask newTask) {
            if (this.precedingTasks.contains(oldTask)) {
                this.precedingTasks.remove(oldTask);
                this.precedingTasks.add(newTask);
            }
            if (this.procedingTasks.contains(oldTask)) {
                this.procedingTasks.remove(oldTask);
                this.procedingTasks.add(newTask);
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder apply(ImmutableSubstitution substitution) {
            // Do nothing; precedence constraints do not have conditions
            return this;
        }  
        
        /**
         * @return the task
         */
        protected final Set<ImmutableTask> getPrecedingTasks() {
            return precedingTasks;
        }

        /**
         * @return the task
         */
        protected final Set<ImmutableTask> getProcedingTasks() {
            return procedingTasks;
        }
        
        /**
         * Build the constraint.
         * @return the finished constraint
         */
        public final ImmutableValidatablePrecedenceConstraint build() {
            return new SimplePrecedenceConstraint(this);
        }
    }
}
