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
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.unification.Unifier;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDecompositionServiceTest {
	
  
    @Test
    public void testDecompose() {
    	
    	UnificationService mockUnificationService = mock(UnificationService.class);
    	SimpleDecompositionService decompositionService = new SimpleDecompositionService(mockUnificationService);
    	
    	// TaskA will be decomposed into TaskB and TaskC
    	Task mockTaskA = mock(Task.class);
    	Task mockTaskB = mock(Task.class);
    	Task mockTaskC = mock(Task.class);
    	
    	Set<Task> taskNetworkTasks = new HashSet<Task>();
    	taskNetworkTasks.add(mockTaskA);
    	Set<Task> methodSubTasks = new HashSet<Task>();
    	methodSubTasks.add(mockTaskB);
    	methodSubTasks.add(mockTaskC);
    	
    	// Original task network and unified version - unification makes no change
    	TaskNetwork mockTaskNetwork = mock(TaskNetwork.class);
    	when(mockTaskNetwork.getTasks()).thenReturn(taskNetworkTasks);
    	TaskNetwork mockUnifiedTaskNetwork = mock(TaskNetwork.class);
    	when(mockUnifiedTaskNetwork.getTasks()).thenReturn(taskNetworkTasks);
    	
    	// Original method sub tasks and unified version - unification makes no change
    	TaskNetwork mockMethodSubTasks = mock(TaskNetwork.class);
    	when(mockTaskNetwork.getTasks()).thenReturn(methodSubTasks);
    	TaskNetwork mockUnifiedMethodSubTasks = mock(TaskNetwork.class);
    	when(mockUnifiedMethodSubTasks.getTasks()).thenReturn(methodSubTasks);
    	
    	// Mock method converts TaskA into TaskB and TaskC
    	Method mockMethod = mock(Method.class);
    	when(mockMethod.getTask()).thenReturn(mockTaskA);
    	when(mockMethod.getTaskNetwork()).thenReturn(mockMethodSubTasks);
    	
    	// Mock unifier to make no changes
    	Unifier mockUnifier = mock(Unifier.class);
    	when(mockUnificationService.apply(mockUnifier, mockTaskNetwork)).thenReturn(mockUnifiedTaskNetwork);
    	when(mockUnificationService.apply(mockUnifier, mockMethodSubTasks)).thenReturn(mockUnifiedMethodSubTasks);
    	
    	//Make the decompose call
    	TaskNetwork decomposedTaskNetwork = decompositionService.decompose(mockUnifier, mockTaskNetwork, mockTaskA, mockMethod);

    	// Verify that the original task network and the method's sub tasks got the unifier applied
    	verify(mockUnificationService).apply(mockUnifier, mockMethodSubTasks);
    	verify(mockUnificationService).apply(mockUnifier, mockTaskNetwork);
    	
    	// Confirm that the resultant task network contains only the correct tasks
    	assertEquals(2, decomposedTaskNetwork.getTasks().size());
    	assertFalse(decomposedTaskNetwork.getTasks().contains(mockTaskA));
    	assertTrue(decomposedTaskNetwork.getTasks().contains(mockTaskB));
    	assertTrue(decomposedTaskNetwork.getTasks().contains(mockTaskC));
    	
    	
    }
}
