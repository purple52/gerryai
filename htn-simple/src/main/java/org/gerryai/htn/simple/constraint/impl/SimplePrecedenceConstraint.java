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

import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.simple.constraint.ImmutableConstraintBuilder;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

import com.google.common.base.Objects;
import com.google.common.collect.Multimap;

/**
 * Simple implementation of a precedence constraint.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePrecedenceConstraint	implements PrecedenceConstraint {

	/**
	 * The tasks that must come first.
	 */
	private Set<Task> precedingTasks;
	
	/**
	 * The tasks that must come last.
	 */
	private Set<Task> proceedingTasks;
	
    /**
     * Constructor.
     * @param builder the builder to build from
     */
    protected SimplePrecedenceConstraint(Builder builder) {
        precedingTasks = builder.precedingTasks;
        proceedingTasks = builder.proceedingTasks;
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
    public final PrecedenceConstraint apply(Map<Term, Term> substitution) {
        return new Builder()
            .copy(this)
            .apply(substitution)
            .build();
    }
    
    @Override
    public final PrecedenceConstraint replace(Multimap<Task, Task> taskMap) {
        return new Builder()
        .copy(this)
        .replace(taskMap)
        .build();    	
    }
    
	@Override
	public final int hashCode() {
		return Objects.hashCode(precedingTasks, proceedingTasks);
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof SimplePrecedenceConstraint) {
	        final SimplePrecedenceConstraint other = (SimplePrecedenceConstraint) obj;
	        return Objects.equal(precedingTasks, other.precedingTasks)
	            && Objects.equal(proceedingTasks, other.proceedingTasks);
	    } else {
	        return false;
	    }
	}
	
	  /**
     * Builder class for SimpleBetweenConstraint.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements ImmutableConstraintBuilder<PrecedenceConstraint> {
        
        /**
         * The tasks that must come first.
         */
        private Set<Task> precedingTasks;

        /**
         * The tasks that must come last.
         */
        private Set<Task> proceedingTasks;
        
        /**
         * @param tasks the tasks to set
         * @return the updated builder
         */
        public final Builder setPrecedingTasks(Set<Task> tasks) {
            this.precedingTasks = tasks;
            return this;
        }

        /**
         * @param tasks the tasks to set
         * @return the updated builder
         */
        public final Builder setProcedingTasks(Set<Task> tasks) {
            this.proceedingTasks = tasks;
            return this;
        }
        
        @Override
        public final Builder copy(PrecedenceConstraint constraint) {
        	precedingTasks = new HashSet<Task>(constraint.getPrecedingTasks());
            proceedingTasks = new HashSet<Task>(constraint.getProcedingTasks());
            return this;
        }

        @Override
        public final Builder apply(Map<Term, Term> substitution) {
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
        public final PrecedenceConstraint build() {
            return new SimplePrecedenceConstraint(this);
        }
    }
}
