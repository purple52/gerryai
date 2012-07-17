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

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.ImmutableConstraintBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

import com.google.common.base.Objects;
import com.google.common.collect.Multimap;

/**
 * Simple implementation of an after constraint.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleAfterConstraint implements AfterConstraint {

	/**
	 * The set of tasks that this constraint must hold for.
	 */
	private Set<Task> tasks;
	
	/**
	 * The condition that must be true directly after the last of these tasks.
	 */
	private Condition condition;

	/**
     * Constructor.
     * @param builder the builder to build from
     */
    protected SimpleAfterConstraint(Builder builder) {
        tasks = builder.tasks;
        condition = builder.condition;
    }
    
	@Override
	public final Set<Task> getTasks() {
		return Collections.unmodifiableSet(tasks);
	}

	@Override
	public final Condition getCondition() {
		return condition;
	}

    @Override
    public final AfterConstraint apply(Map<Term, Term> substitution) {
        return new Builder()
            .copy(this)
            .apply(substitution)
            .build();
    }
    
    @Override
    public final AfterConstraint replace(Multimap<Task, Task> taskMap) {
        return new Builder()
        .copy(this)
        .replace(taskMap)
        .build();    	
    }
    
	@Override
	public final int hashCode() {
		return Objects.hashCode(tasks, condition);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimpleAfterConstraint) {
	        final SimpleAfterConstraint other = (SimpleAfterConstraint) obj;
	        return Objects.equal(tasks, other.tasks)
	            && Objects.equal(condition, other.condition);
	    } else {
	        return false;
	    }
	}
	
	/**
	 * Builder class for SimpleAfterConstraint.
	 * @author David Edwards <david@more.fool.me.uk>
	 */
	public static class Builder implements ImmutableConstraintBuilder<AfterConstraint> {
	    
	    /**
	     * The set of tasks that this constraint must hold for.
	     */
	    private Set<Task> tasks;
	    
        /**
         * The condition that must be true directly after the last of these tasks.
         */
        private Condition condition;
        
        /**
         * Default constructor.
         */
        public Builder() {
            tasks = new HashSet<Task>();
        }

        /**
         * @param tasks the tasks to add
         * @return the updated builder
         */
        public final Builder addTasks(Set<Task> tasks) {
            this.tasks.addAll(tasks);
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
        public final Builder copy(AfterConstraint constraint) {
            tasks = new HashSet<Task>(constraint.getTasks());
            condition = constraint.getCondition();
            return this;
        }

        @Override
        public final Builder apply(Map<Term, Term> substitution) {
        	condition = condition.applyToCopy(substitution);
        	for (Task oldTask : tasks) {
        		Task newTask = oldTask.applyToCopy(substitution);
        		if (!oldTask.equals(newTask)) {
        			tasks.remove(oldTask);
        			tasks.add(newTask);
        		}
        	}
            return this;
        }      
        
        @Override
        public final Builder replace(Multimap<Task, Task> taskMap) {
        	for (Task task : taskMap.keySet()) {
	            if (tasks.contains(task)) {
	                tasks.remove(task);
	                tasks.addAll(taskMap.get(task));
	            }
        	}
            return this;
        }
        
        @Override
	    public final AfterConstraint build() {
	        return new SimpleAfterConstraint(this);
	    }
	}	
}
