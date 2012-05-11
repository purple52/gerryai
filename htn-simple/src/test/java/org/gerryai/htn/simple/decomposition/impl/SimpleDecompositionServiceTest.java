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
package org.gerryai.htn.simple.decomposition.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetworkBuilder;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDecompositionServiceTest {
	
  
    @Test
    @SuppressWarnings("unchecked")
    public void testDecompose() throws InvalidConstraint {
    	
    	ConstraintValidatorFactory<SubstitutableTerm, ImmutableTask,
                SubstitutableCondition> mockConstraintValidatorFactory = mock(ConstraintValidatorFactory.class);

    	SimpleDecompositionService decompositionService = new SimpleDecompositionService(mockConstraintValidatorFactory);
    	
    	// TaskA will be decomposed into TaskB and TaskC
    	ImmutableTask mockTaskA = mock(ImmutableTask.class);
    	ImmutableTask mockTaskB = mock(ImmutableTask.class);
    	ImmutableTask mockTaskC = mock(ImmutableTask.class);
    	
    	Set<ImmutableTask> taskNetworkTasks = new HashSet<ImmutableTask>();
    	taskNetworkTasks.add(mockTaskA);
    	Set<ImmutableTask> methodSubTasks = new HashSet<ImmutableTask>();
    	methodSubTasks.add(mockTaskB);
    	methodSubTasks.add(mockTaskC);
    	
    	ImmutableTaskNetworkBuilder mockTaskNetworkBuilderA = mock(ImmutableTaskNetworkBuilder.class);
    	ImmutableTaskNetworkBuilder mockTaskNetworkBuilderB = mock(ImmutableTaskNetworkBuilder.class);
    	ImmutableTaskNetworkBuilder mockTaskNetworkBuilderC = mock(ImmutableTaskNetworkBuilder.class);
    	ImmutableTaskNetworkBuilder mockTaskNetworkBuilderD = mock(ImmutableTaskNetworkBuilder.class);
    	ImmutableTaskNetworkBuilder mockTaskNetworkBuilderE = mock(ImmutableTaskNetworkBuilder.class);
        
    	// Original task network and unified version - unification makes no change
    	ImmutableTaskNetwork mockTaskNetwork = mock(ImmutableTaskNetwork.class);
    	when(mockTaskNetwork.getTasks()).thenReturn(taskNetworkTasks);
    	ImmutableTaskNetwork mockUnifiedTaskNetwork = mock(ImmutableTaskNetwork.class);
    	when(mockUnifiedTaskNetwork.getTasks()).thenReturn(taskNetworkTasks);
    	
    	// Original method sub tasks and unified version - unification makes no change
    	ImmutableTaskNetwork mockMethodSubTasks = mock(ImmutableTaskNetwork.class);
    	when(mockMethodSubTasks.getTasks()).thenReturn(methodSubTasks);
    	
    	ImmutableTaskNetwork mockUnifiedMethodSubTasks = mock(ImmutableTaskNetwork.class);
    	when(mockUnifiedMethodSubTasks.getTasks()).thenReturn(methodSubTasks);
    	
    	// Mock method converts TaskA into TaskB and TaskC
    	SubstitutableMethod mockMethod = mock(SubstitutableMethod.class);
    	when(mockMethod.getTask()).thenReturn(mockTaskA);
    	when(mockMethod.getTaskNetwork()).thenReturn(mockMethodSubTasks);
    	
    	// Mock unifier to make no changes
    	ImmutableSubstitution mockSubstitution = mock(ImmutableSubstitution.class);

    	when(mockMethodSubTasks.createCopyBuilder(any(ConstraintValidator.class))).thenReturn(mockTaskNetworkBuilderA);
        when(mockTaskNetworkBuilderA.apply(mockSubstitution)).thenReturn(mockTaskNetworkBuilderB);
        when(mockTaskNetworkBuilderB.build()).thenReturn(mockUnifiedMethodSubTasks);

        when(mockTaskNetwork.createCopyBuilder(any(ConstraintValidator.class))).thenReturn(mockTaskNetworkBuilderC);
        when(mockTaskNetworkBuilderC.apply(mockSubstitution)).thenReturn(mockTaskNetworkBuilderD);
        when(mockTaskNetworkBuilderD.replace(mockTaskA, mockUnifiedMethodSubTasks)).thenReturn(mockTaskNetworkBuilderB);
        when(mockTaskNetworkBuilderE.build()).thenReturn(mockUnifiedMethodSubTasks);

    	//Make the decompose call
    	ImmutableTaskNetwork decomposedTaskNetwork = decompositionService.decompose(mockSubstitution, mockTaskNetwork, mockTaskA, mockMethod);

    	// Verify that the original task network and the method's sub tasks got the unifier applied
    	//verify(mockUnificationService).apply(mockUnifier, mockMethodSubTasks);
    	//verify(mockUnificationService).apply(mockUnifier, mockTaskNetwork);
    	
    	// Confirm that the resultant task network contains only the correct tasks
    	assertEquals(2, decomposedTaskNetwork.getTasks().size());
    	assertFalse(decomposedTaskNetwork.getTasks().contains(mockTaskA));
    	assertTrue(decomposedTaskNetwork.getTasks().contains(mockTaskB));
    	assertTrue(decomposedTaskNetwork.getTasks().contains(mockTaskC));
    	
    	
    }
}
