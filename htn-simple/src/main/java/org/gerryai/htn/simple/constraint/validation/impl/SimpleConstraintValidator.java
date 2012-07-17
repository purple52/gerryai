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
package org.gerryai.htn.simple.constraint.validation.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Class for validating tasks and constraints.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleConstraintValidator implements ConstraintValidator {

	/**
	 * Set of tasks that have been added to the validator so far.
	 */
	private Set<Task> tasks;
	
	/**
	 * Set of precedence constraints that have been added so far.
	 */
	private Set<PrecedenceConstraint> precedenceConstraints;
	
	/**
	 * Set of before constraints that have been added so far.
	 */
	private Set<BeforeConstraint> beforeConstraints;

	/**
	 * Set of after constraints that have been added so far.
	 */
	private Set<AfterConstraint> afterConstraints;

	/**
	 * Set of between constraints that have been added so far.
	 */
	private Set<BetweenConstraint> betweenConstraints;
	
	/**
	 * Map of precedence constraints, using their preceding task as the key.
	 * Used to simplify looking for cycles.
	 */
	private Multimap<Task, PrecedenceConstraint> precedingTasks;
	
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
	
	/**
	 * Default constructor.
	 */
	public SimpleConstraintValidator() {
		tasks = new HashSet<Task>();
		precedenceConstraints = new HashSet<PrecedenceConstraint>();
		precedingTasks = HashMultimap.create();
		beforeConstraints = new HashSet<BeforeConstraint>();
		afterConstraints = new HashSet<AfterConstraint>();
		betweenConstraints = new HashSet<BetweenConstraint>();
	}

	@Override
	public final void add(PrecedenceConstraint constraint) throws InvalidConstraint {
		if (validate(constraint)) {
			precedenceConstraints.add(constraint);
			for (Task task : constraint.getPrecedingTasks()) {
			    precedingTasks.get(task).add(constraint);
			}
		} else {
			throw new InvalidConstraint();
		}
	}
	
	@Override
	public final void add(BeforeConstraint constraint) throws InvalidConstraint {
		if (validate(constraint)) {
			beforeConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}
	}

	@Override
	public final void add(AfterConstraint constraint) throws InvalidConstraint {
		if (validate(constraint)) {
			afterConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}		
	}

	@Override
	public final void add(BetweenConstraint constraint) throws InvalidConstraint {
		if (validate(constraint)) {
			betweenConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}		
	}
	
	@Override
	public final void add(Task task) {
		tasks.add(task);
	}
	
    @Override
    public final void apply(Map<Term, Term> substitution) {

        // Build a map of tasks to their replacements
        Multimap<Task, Task> taskReplacementMap = HashMultimap.create();
        for (Task task : tasks) {
            Task newTask = task.applyToCopy(substitution);
            taskReplacementMap.put(task, newTask);
        }
        // Replace the existing tasks in this builder
        tasks = new HashSet<Task>(taskReplacementMap.values());
        
        // Build the sets of replacement constraints
        beforeConstraints = applyReplace(beforeConstraints, substitution, taskReplacementMap);
        afterConstraints = applyReplace(afterConstraints, substitution, taskReplacementMap);
        betweenConstraints = applyReplace(betweenConstraints, substitution, taskReplacementMap);
        precedenceConstraints = applyReplace(precedenceConstraints, substitution, taskReplacementMap);

    }
	
    @Override
    public final void replace(Task oldTask, TaskNetwork newTaskNetwork) {
        //TODO: Check implementation
        
        // Build a new set of tasks
    	int numTasks = tasks.size() + newTaskNetwork.getTasks().size() - 1;
        Set<Task> newTasks = new HashSet<Task>(numTasks);
        for (Task task : tasks) {
            if (!task.equals(oldTask)) {
                newTasks.add(task);
            } else {
                newTasks.addAll(newTaskNetwork.getTasks());
            }
        }
        tasks = newTasks;
        
        // Update existing constraints
        Multimap<Task, Task> taskMap = HashMultimap.create();
        taskMap.putAll(oldTask, newTaskNetwork.getTasks());
        beforeConstraints = mergeAndReplace(beforeConstraints,
        		newTaskNetwork.getBeforeConstraints(), taskMap);
        afterConstraints = mergeAndReplace(afterConstraints,
        		newTaskNetwork.getAfterConstraints(), taskMap);
        betweenConstraints = mergeAndReplace(betweenConstraints,
        		newTaskNetwork.getBetweenConstraints(), taskMap);
        precedenceConstraints = mergeAndReplace(precedenceConstraints,
        		newTaskNetwork.getPrecedenceConstraints(), taskMap);
    }
    
    /**
     * Apply a task substitution to a set of constraints.
     * @param oldConstraints existing constraints
     * @param substitution the substitution to apply to the constraints
     * @param taskMap map of tasks to the sets of tasks to replace them with
     * @param <T> type of constraint being processed
     * @return the set of constraints with the task substitution applied
     */
    private <T extends Constraint<T>> Set<T> applyReplace(Set<T> oldConstraints,
    													  Map<Term, Term> substitution,
    		                                              Multimap<Task, Task> taskMap) {
        Set<T> newConstraints = new HashSet<T>(oldConstraints.size());
        for (T constraint : oldConstraints) {
        	T newConstraint = constraint
        			.apply(substitution)
        			.replace(taskMap);
            newConstraints.add(newConstraint);
        }
        return newConstraints;
    }
    
    /**
     * Combine two sets of constraints and apply a task substitution to the old constraints.
     * @param oldConstraints existing constraints
     * @param newConstraints new constraints to add
     * @param taskMap map of tasks to the sets of tasks to replace them with
     * @param <T> type of constraint being processed
     * @return the combined set of constraints
     */
    private <T extends Constraint<T>> Set<T> mergeAndReplace(Set<T> oldConstraints,
    		Set<T> newConstraints, Multimap<Task, Task> taskMap) {
        int numConstraints = oldConstraints.size() + newConstraints.size();
        Set<T> updatedConstraints = new HashSet<T>(numConstraints);
        for (T oldConstraint : oldConstraints) {
        	T newConstraint = oldConstraint.replace(taskMap);
        	updatedConstraints.add(newConstraint);
        }
        // Add the new constraints
        updatedConstraints.addAll(newConstraints);
        return updatedConstraints;
    }
    
	/**
	 * Validation check for simple precedence constraints, but does not add the constraint.
	 * @param constraint the constraint to validate
	 * @return true if the constraint passes validation
	 */
	public final boolean validate(PrecedenceConstraint constraint) {
		
		// Check tasks have already been added
	    for (Task task : constraint.getPrecedingTasks()) {
	        if (!tasks.contains(task)) {
	            return false;
	        }
	    }
        for (Task task : constraint.getProcedingTasks()) {
            if (!tasks.contains(task)) {
                return false;
            }
        }
        
		// Check if an existing identical constraint exists
		for (PrecedenceConstraint existingConstraint : precedenceConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		
		//Check for cycles
		for (Task precedingTask : constraint.getPrecedingTasks()) {
		    for (Task procedingTask : constraint.getProcedingTasks()) {
		        if (checkForPrecedenceConstraintCycle(precedingTask, procedingTask)) {
		            return false;
		        }
		    }
		}
		
		return true;
	}
	
	/**
	 * Validation check for simple before constraints, but does not add the constraint.
	 * @param constraint the constraint to validate
	 * @return true if the constraint passes validation
	 */
	private boolean validate(BeforeConstraint constraint) {
		// Check tasks have already been added
		for (Task task : constraint.getTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (BeforeConstraint existingConstraint : beforeConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validation check for simple after constraints, but does not add the constraint.
	 * @param constraint the constraint to validate
	 * @return true if the constraint passes validation
	 */
	private boolean validate(AfterConstraint constraint) {
		// Check tasks have already been added
		for (Task task : constraint.getTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (AfterConstraint existingConstraint : afterConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Validation check for simple between constraints, but does not add the constraint.
	 * @param constraint the constraint to validate
	 * @return true if the constraint passes validation
	 */
	private boolean validate(BetweenConstraint constraint) {
		// Check tasks have already been added
		for (Task task : constraint.getPrecedingTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		for (Task task : constraint.getProcedingTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (BetweenConstraint existingConstraint : betweenConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Helper method to help check for cycles.
	 * @param initialTask task we are looking for
	 * @param nextTask next task we should check from
	 * @return true if a cycle was found
	 */
	private boolean checkForPrecedenceConstraintCycle(Task initialTask, Task nextTask) {
		
		if (initialTask.equals(nextTask)) {
			return true;
		} else if (!precedingTasks.containsKey(nextTask)) {
			return false;
		} else {
			for (PrecedenceConstraint constraint : precedingTasks.get(nextTask)) {
				for (Task task : constraint.getProcedingTasks()) {
				    if (checkForPrecedenceConstraintCycle(initialTask, task)) {
				        return true;
				    }
				}
			}
			return false;
		}
		
	}
}
