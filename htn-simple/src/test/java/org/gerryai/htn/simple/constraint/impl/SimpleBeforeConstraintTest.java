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

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.ImmutableValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 * 
 */
public class SimpleBeforeConstraintTest {

    /**
     * Test get/set tasks.
     */
    @Test
    public final void testConstructor() {
        Task mockTask = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTask);
        Condition mockCondition = mock(Condition.class);

        ImmutableValidatableBeforeConstraint constraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        
        assertEquals(mockTasks, constraint.getTasks());
        assertEquals(mockCondition, constraint.getCondition());
    }

    /**
     * Test validate is called.
     */
    @Test
    public final void testValidate() {
        Task mockTask = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTask);
        Condition mockCondition = mock(Condition.class);

        ConstraintValidator mockValidator = mock(ConstraintValidator.class);
        ImmutableValidatableBeforeConstraint constraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        constraint.validate(mockValidator);

        verify(mockValidator).validate(constraint);
    }

    /**
     * Test that constraint is added to the validator.
     * @throws InvalidConstraint only if test fails
     */
    @Test
    public final void testAdd() throws InvalidConstraint {
        Task mockTask = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTask);
        Condition mockCondition = mock(Condition.class);

        ConstraintValidator mockValidator = mock(ConstraintValidator.class);
        ImmutableValidatableBeforeConstraint constraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
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
        @SuppressWarnings("unchecked")
        Map<Term, Term> mockSubstitution = mock(Map.class);
        Task mockTask = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTask);
        Condition mockConditionA = mock(Condition.class);
        Condition mockConditionB = mock(Condition.class);
        when(mockConditionA.applyToCopy(mockSubstitution)).thenReturn(mockConditionB);
        
        ImmutableValidatableBeforeConstraint initialConstraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockConditionA)
                .build();
        
        ImmutableValidatableBeforeConstraint constraint = initialConstraint.createCopyBuilder()
                .apply(mockSubstitution)
                .build();

        assertEquals(mockTasks, constraint.getTasks());
        assertEquals(mockConditionB, constraint.getCondition());
        //TODO: check substitution results properly
    }

    /**
     * Test construction using copy and replace.
     */
    @Test
    public final void testCopyReplace() {
        Task mockTaskA = mock(Task.class);
        Set<Task> mockTasks = new HashSet<Task>();
        mockTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockNewTasks = new HashSet<Task>();
        mockNewTasks.add(mockTaskB);
        mockNewTasks.add(mockTaskC);
        Condition mockCondition = mock(Condition.class);

        ImmutableValidatableBeforeConstraint initialConstraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        
        ImmutableValidatableBeforeConstraint constraint = initialConstraint.createCopyBuilder()
                .replace(mockTaskA, mockNewTasks)
                .build();

        assertEquals(mockNewTasks, constraint.getTasks());
    }

}
