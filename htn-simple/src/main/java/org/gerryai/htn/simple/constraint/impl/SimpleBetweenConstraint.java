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

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.ImmutableConstraintBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

import com.google.common.base.Objects;
import com.google.common.collect.Multimap;

/**
 * Simple implementation of a between constraint.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleBetweenConstraint implements BetweenConstraint {

	/**
	 * The set of tasks that this constraint must hold after.
	 */
	private Set<Task> precedingTasks;

	/**
	 * The set of tasks that this constraint must hold before.
	 */
	private Set<Task> proceedingTasks;
	
	/**
	 * The literal that must be true directly between the two sets of tasks.
	 */
	private Condition condition;
	
    /**
     * Constructor.
     * @param builder the builder to build from
     */
    protected SimpleBetweenConstraint(Builder builder) {
        precedingTasks = builder.precedingTasks;
        proceedingTasks = builder.proceedingTasks;
        condition = builder.condition;
    }
	
	@Override
	public final Set<Task> getPrecedingTasks() {
		return Collections.unmodifiableSet(precedingTasks);
	}

	@Override
	public final Set<Task> getProcedingTasks() {
		return Collections.unmodifiableSet(proceedingTasks);
	}
	
	@Override
	public final Condition getCondition() {
		return condition;
	}
	
    @Override
    public final BetweenConstraint apply(Map<Term, Term> substitution) {
        return new Builder()
            .copy(this)
            .apply(substitution)
            .build();
    }
    
    @Override
    public final BetweenConstraint replace(Multimap<Task, Task> taskMap) {
        return new Builder()
        .copy(this)
        .replace(taskMap)
        .build();    	
    }
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(precedingTasks, proceedingTasks, condition);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimpleBetweenConstraint) {
	        final SimpleBetweenConstraint other = (SimpleBetweenConstraint) obj;
	        return Objects.equal(precedingTasks, other.precedingTasks)
	        	&& Objects.equal(proceedingTasks, other.proceedingTasks)
	            && Objects.equal(condition, other.condition);
	    } else {
	        return false;
	    }
	}
	
	/**
     * Builder class for SimpleBetweenConstraint.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements ImmutableConstraintBuilder<BetweenConstraint> {     

    	/**
         * The set of tasks that this constraint must hold after.
         */
        private Set<Task> precedingTasks;

        /**
         * The set of tasks that this constraint must hold before.
         */
        private Set<Task> proceedingTasks;
        
        /**
         * The condition that must be true directly after the last of these tasks.
         */
        private Condition condition;
        
        /**
         * Default constructor.
         */
        public Builder() {
            precedingTasks = new HashSet<Task>();
            proceedingTasks = new HashSet<Task>();
        }

        /**
         * @param tasks the tasks to add
         * @return the updated builder
         */
        public final Builder addPrecedingTasks(Set<Task> tasks) {
            precedingTasks.addAll(tasks);
            return this;
        }

        /**
         * @param tasks the tasks to add
         * @return the updated builder
         */
        public final Builder addProcedingTasks(Set<Task> tasks) {
        	proceedingTasks.addAll(tasks);
            return this;
        }
        
        /**
         * @param condition the condition to set
         * @return the updated builder
         */
        public final Builder setCondition(Condition condition) {
            this.condition = condition;
            return this;
        }

        @Override
        public final Builder copy(BetweenConstraint constraint) {
        	precedingTasks = new HashSet<Task>(constraint.getPrecedingTasks());
            proceedingTasks = new HashSet<Task>(constraint.getProcedingTasks());
            condition = constraint.getCondition();
            return this;
        }

        @Override
        public final Builder apply(Map<Term, Term> substitution) {
        	condition = condition.applyToCopy(substitution);
        	for (Task oldTask : precedingTasks) {
        		Task newTask = oldTask.applyToCopy(substitution);
        		if (!oldTask.equals(newTask)) {
        			precedingTasks.remove(oldTask);
        			precedingTasks.add(newTask);
        		}
        	}
        	for (Task oldTask : proceedingTasks) {
        		Task newTask = oldTask.applyToCopy(substitution);
        		if (!oldTask.equals(newTask)) {
        			proceedingTasks.remove(oldTask);
        			proceedingTasks.add(newTask);
        		}
        	}
        	return this;
        }      
        
        @Override
        public final Builder replace(Multimap<Task, Task> taskMap) {
        	for (Task task : taskMap.keySet()) {
	            if (precedingTasks.contains(task)) {
	            	precedingTasks.remove(task);
	            	precedingTasks.addAll(taskMap.get(task));
	            }
	            if (proceedingTasks.contains(task)) {
	            	proceedingTasks.remove(task);
	            	proceedingTasks.addAll(taskMap.get(task));
	            }
	        }
            return this;
        }
                  
        /**
         * Build the constraint.
         * @return the finished constraint
         */
        public final BetweenConstraint build() {
            return new SimpleBetweenConstraint(this);
        }
    }
}
