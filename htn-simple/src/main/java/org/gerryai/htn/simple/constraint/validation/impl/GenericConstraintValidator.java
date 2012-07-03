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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.ValidatableAfterConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.ValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Class for validating precedence constraints.
 * @param <K> type of task being used by the constraints being validated
 * @param <I> type of condition being used by the constraints being validated
 * @author David Edwards <david@more.fool.me.uk>
 */
public class GenericConstraintValidator<K extends Task, I extends Condition>
		implements ConstraintValidator<K, I> {

	/**
	 * Set of tasks that have been added to the validator so far.
	 */
	private Set<K> tasks;
	
	/**
	 * Set of precedence constraints that have been added so far.
	 */
	private Set<ValidatablePrecedenceConstraint<K, I>> precedenceConstraints;
	
	/**
	 * Set of before constraints that have been added so far.
	 */
	private Set<ValidatableBeforeConstraint<K, I>> beforeConstraints;

	/**
	 * Set of after constraints that have been added so far.
	 */
	private Set<ValidatableAfterConstraint<K, I>> afterConstraints;

	/**
	 * Set of between constraints that have been added so far.
	 */
	private Set<ValidatableBetweenConstraint<K, I>> betweenConstraints;
	
	/**
	 * Map of precedence constraints, using their preceding task as the key.
	 * Used to simplify looking for cycles.
	 */
	private Multimap<K, ValidatablePrecedenceConstraint<K, I>> precedingTasks;
	
	/**
	 * Default constructor.
	 */
	public GenericConstraintValidator() {
		tasks = new HashSet<K>();
		precedenceConstraints = new HashSet<ValidatablePrecedenceConstraint<K, I>>();
		precedingTasks = HashMultimap.create();
		beforeConstraints = new HashSet<ValidatableBeforeConstraint<K, I>>();
		afterConstraints = new HashSet<ValidatableAfterConstraint<K, I>>();
		betweenConstraints = new HashSet<ValidatableBetweenConstraint<K, I>>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatablePrecedenceConstraint<K, I> constraint) {
		
		// Check tasks have already been added
	    for (K task : constraint.getPrecedingTasks()) {
	        if (!tasks.contains(task)) {
	            return false;
	        }
	    }
        for (K task : constraint.getProcedingTasks()) {
            if (!tasks.contains(task)) {
                return false;
            }
        }
        
		// Check if an existing identical constraint exists
		for (ValidatablePrecedenceConstraint<K, I> existingConstraint : precedenceConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		
		//Check for cycles
		for (K precedingTask : constraint.getPrecedingTasks()) {
		    for (K procedingTask : constraint.getProcedingTasks()) {
		        if (checkForPrecedenceConstraintCycle(precedingTask, procedingTask)) {
		            return false;
		        }
		    }
		}
		
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatableBeforeConstraint<K, I> constraint) {
		// Check tasks have already been added
		for (K task : constraint.getTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (ValidatableBeforeConstraint<K, I> existingConstraint : beforeConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatableAfterConstraint<K, I> constraint) {
		// Check tasks have already been added
		for (K task : constraint.getTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (ValidatableAfterConstraint<K, I> existingConstraint : afterConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(ValidatableBetweenConstraint<K, I> constraint) {
		// Check tasks have already been added
		for (K task : constraint.getPrecedingTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		for (K task : constraint.getProcedingTasks()) {
			if (!tasks.contains(task)) {
				return false;
			}
		}
		// Check if an existing identical constraint exists
		for (ValidatableBetweenConstraint<K, I> existingConstraint : betweenConstraints) {	
			if (existingConstraint.equals(constraint)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatablePrecedenceConstraint<K, I> constraint)
			throws InvalidConstraint {
		if (validate(constraint)) {
			precedenceConstraints.add(constraint);
			for (K task : constraint.getPrecedingTasks()) {
			    precedingTasks.get(task).add(constraint);
			}
		} else {
			throw new InvalidConstraint();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatableBeforeConstraint<K, I> constraint)
			throws InvalidConstraint {
		if (validate(constraint)) {
			beforeConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatableAfterConstraint<K, I> constraint)
			throws InvalidConstraint {
		if (validate(constraint)) {
			afterConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	public final void add(ValidatableBetweenConstraint<K, I> constraint)
			throws InvalidConstraint {
		if (validate(constraint)) {
			betweenConstraints.add(constraint);
		} else {
			throw new InvalidConstraint();
		}		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void add(K task) {
		tasks.add(task);
	}

	/**
	 * Helper method to help check for cycles.
	 * @param initialTask task we are looking for
	 * @param nextTask next task we should check from
	 * @return true if a cycle was found
	 */
	private boolean checkForPrecedenceConstraintCycle(K initialTask, K nextTask) {
		
		if (initialTask.equals(nextTask)) {
			return true;
		} else if (!precedingTasks.containsKey(nextTask)) {
			return false;
		} else {
			for (ValidatablePrecedenceConstraint<K, I> constraint : precedingTasks.get(nextTask)) {
				for (K task : constraint.getProcedingTasks()) {
				    if (checkForPrecedenceConstraintCycle(initialTask, task)) {
				        return true;
				    }
				}
			}
			return false;
		}
		
	}
}
