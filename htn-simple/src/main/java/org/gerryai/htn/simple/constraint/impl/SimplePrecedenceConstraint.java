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

import org.gerryai.htn.simple.constraint.SubstitutableConstraint;
import org.gerryai.htn.simple.constraint.ValidatablePrecedenceConstraint;
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
public class SimplePrecedenceConstraint	implements
		ValidatablePrecedenceConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition>,
		SubstitutableConstraint<SubstitutableTerm> {

	/**
	 * The task that must come first.
	 */
	private SubstitutableTask precedingTask;
	
	/**
	 * The task that must come last.
	 */
	private SubstitutableTask procedingTask;
	
    /**
     * Constructor.
     * @param builder the builder to build from
     */
    protected SimplePrecedenceConstraint(Builder builder) {
        precedingTask = builder.getPrecedingTask();
        procedingTask = builder.getProcedingTask();
    }
	
	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTask getPrecedingTask() {
		return precedingTask;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SubstitutableTask getProcedingTask() {
		return procedingTask;
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
	public final void add(ConstraintValidator<SubstitutableTerm, SubstitutableTask,
			SubstitutableCondition> validator) throws InvalidConstraint {
		validator.add(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void apply(Substituter<SubstitutableTerm> substituter) {
		// Nothing to do
	}
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(precedingTask, procedingTask);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimplePrecedenceConstraint) {
	        final SimplePrecedenceConstraint other = (SimplePrecedenceConstraint) obj;
	        return Objects.equal(precedingTask, other.precedingTask)
	            && Objects.equal(procedingTask, other.procedingTask);
	    } else {
	        return false;
	    }
	}
	
	   /**
     * Builder class for SimpleBetweenConstraint.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder {
        
        /**
         * The task that must come first.
         */
        private SubstitutableTask precedingTask;

        /**
         * The task that must come last.
         */
        private SubstitutableTask procedingTask;
        
        /**
         * @param task the task to set
         * @return the updated builder
         */
        public final Builder setPrecedingTask(SubstitutableTask task) {
            this.precedingTask = task;
            return this;
        }

        /**
         * @param task the task to set
         * @return the updated builder
         */
        public final Builder setProcedingTask(SubstitutableTask task) {
            this.procedingTask = task;
            return this;
        }
        
        /**
         * @return the task
         */
        protected final SubstitutableTask getPrecedingTask() {
            return precedingTask;
        }

        /**
         * @return the task
         */
        protected final SubstitutableTask getProcedingTask() {
            return procedingTask;
        }
        
        /**
         * Build the constraint.
         * @return the finished constraint
         */
        public final SimplePrecedenceConstraint build() {
            return new SimplePrecedenceConstraint(this);
        }
    }
}
