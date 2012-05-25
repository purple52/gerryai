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

import java.util.List;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskBuilder;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkBuilder;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkFactory;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;

/**
 * Factory for SimpleTask and SimpleTaskNetwork objects.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkFactory implements ImmutableTaskNetworkFactory {

    /**
     * Factory used for creating and copying logical terms.
     */
    private ImmutableLogicFactory logicFactory;
    
	/**
	 * Factory for creating constraint validators, as used by the task network builders.
	 */
	private ConstraintValidatorFactory<ImmutableTerm<?>, ImmutableTask,
			ImmutableCondition<?>> constraintValidatorFactory;
	
	/**
	 * Constructor, requiring a factory for creating constraint validators.
	 * @param constraintValidatorFactory the constraint validator factory
	 * @param logicFactory the logic factory
	 */
	public SimpleTaskNetworkFactory(ConstraintValidatorFactory<ImmutableTerm<?>,
			ImmutableTask, ImmutableCondition<?>> constraintValidatorFactory,
			ImmutableLogicFactory logicFactory) {
		this.constraintValidatorFactory = constraintValidatorFactory;
		this.logicFactory = logicFactory;
	}
	
    /**
     * {@inheritDoc}
     */
	public final ImmutableTaskNetwork createTaskNetwork(Set<ImmutableTask> tasks,
	        Set<ImmutableConstraint<?>> constraints) throws InvalidConstraint {
	    return new SimpleTaskNetwork.Builder(constraintValidatorFactory.create())
	        .addTasks(tasks)
	        .addConstraints(constraints)
	        .build();
	}
    
    /**
     * {@inheritDoc}
     */
	public final ImmutableTaskNetwork copyApply(ImmutableTaskNetwork taskNetwork,
	        ImmutableSubstitution substitution) throws InvalidConstraint {
	    return taskNetwork.createCopyBuilder(constraintValidatorFactory.create())
	        .apply(substitution)
	        .build();
	}
    
    /**
     * {@inheritDoc}
     */
	public final ImmutableTaskNetwork copy(ImmutableTaskNetwork taskNetwork, ImmutableSubstitution substitution,
	        ImmutableTask oldTask, ImmutableTaskNetwork replacementNetwork) throws InvalidConstraint {
	    //TODO: Replace!
        return taskNetwork.createCopyBuilder(constraintValidatorFactory.create())
                .apply(substitution)
                .build();	    
	}
    
    /**
     * {@inheritDoc}
     */
    public final ImmutableTaskNetworkBuilder createTaskNetworkBuilder() {
        return new SimpleTaskNetwork.Builder(constraintValidatorFactory.create());
    }
    
    /**
     * {@inheritDoc}
     */
	public final ImmutableTask createTask(String name, List<ImmutableTerm<?>> arguments, boolean isPrimitive) {
	    return new SimpleTask.Builder(logicFactory)
	            .setName(name)
	            .addArguments(arguments)
	            .setIsPrimitive(isPrimitive)
	            .build();
	}

    /**
     * {@inheritDoc}
     */
	public final ImmutableTaskBuilder createTaskBuilder() {
	    return new SimpleTask.Builder(logicFactory);
	}
    
    /**
     * {@inheritDoc}
     */
	public final ImmutableTask copy(ImmutableTask task, ImmutableSubstitution substitution) {
	    return task.createCopyBuilder()
	            .apply(substitution)
	            .build();
	}
}