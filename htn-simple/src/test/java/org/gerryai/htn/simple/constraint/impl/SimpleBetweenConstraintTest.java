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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 * 
 */
public class SimpleBetweenConstraintTest {

    /**
     * Test get/set preceding tasks.
     */
    @Test
    public final void testConstructor() {
        Task mockPrecedingTask = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockPrecedingTask);
        Task mockProcedingTask = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockProcedingTask);
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        
        ImmutableValidatableBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockCondition)
                .build();
        
        assertEquals(mockPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockProcedingTasks, constraint.getProcedingTasks());
        assertEquals(mockCondition, constraint.getCondition());
    }

   
    /**
     * Test validate is called.
     */
    @Test
    public final void testValidate() {
        Task mockPrecedingTask = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockPrecedingTask);
        Task mockProcedingTask = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockProcedingTask);
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableCondition> mockValidator = mock(ConstraintValidator.class);
 
        ImmutableValidatableBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockCondition)
                .build();
        constraint.validate(mockValidator);

        verify(mockValidator).validate(constraint);
    }

    /**
     * Test that constraint is added to validator.
     * @throws InvalidConstraint only if test fails
     */
    @Test
    public final void testAdd() throws InvalidConstraint {
        Task mockPrecedingTask = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockPrecedingTask);
        Task mockProcedingTask = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockProcedingTask);
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableCondition> mockValidator = mock(ConstraintValidator.class);
 
        ImmutableValidatableBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockCondition)
                .build();
        
        constraint.add(mockValidator);

        verify(mockValidator).add(constraint);
    }

    /**
     * Test construction using copy and apply.
     */
    @Test
    public final void testCopyApply() {
        
        Task mockPrecedingTask = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockPrecedingTask);
        Task mockProcedingTask = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockProcedingTask);
        
        @SuppressWarnings("unchecked")
        Map<Term, Term> mockSubstitution = mock(Map.class);
        ImmutableCondition mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition mockConditionB = mock(ImmutableCondition.class);
        when(mockConditionA.applyToCopy(mockSubstitution)).thenReturn(mockConditionB);

        ImmutableValidatableBetweenConstraint initialConstraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockConditionA)
                .build();
        
        ImmutableValidatableBetweenConstraint constraint = initialConstraint.createCopyBuilder()
                .apply(mockSubstitution)
                .build();

        assertEquals(mockPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockProcedingTasks, constraint.getProcedingTasks());
        assertEquals(mockConditionB, constraint.getCondition());
        //TODO: check the substitution has been properly applied
    }

    /**
     * Test construction using copy and replace.
     */
    @Test
    public final void testCopyReplace() {
        Task mockPrecedingTask = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockPrecedingTask);
        Task mockProcedingTask = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockProcedingTask);
        
        Task mockTaskB = mock(Task.class);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockNewPrecedingTasks = new HashSet<Task>();
        mockNewPrecedingTasks.add(mockTaskB);
        mockNewPrecedingTasks.add(mockTaskC);
        Task mockTaskD = mock(Task.class);
        Task mockTaskE = mock(Task.class);
        Set<Task> mockNewProcedingTasks = new HashSet<Task>();
        mockNewProcedingTasks.add(mockTaskD);
        mockNewProcedingTasks.add(mockTaskE);
        
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);

        ImmutableValidatableBetweenConstraint initialConstraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockCondition)
                .build();
        
        ImmutableValidatableBetweenConstraint constraint = initialConstraint.createCopyBuilder()
                .replace(mockPrecedingTask, mockNewPrecedingTasks)
                .replace(mockProcedingTask, mockNewProcedingTasks)
                .build();

        assertEquals(mockNewPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockNewProcedingTasks, constraint.getProcedingTasks());
    }
}
