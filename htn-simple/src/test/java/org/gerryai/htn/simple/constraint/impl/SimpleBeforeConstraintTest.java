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
import java.util.Set;

import org.gerryai.htn.simple.constraint.ImmutableValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableTermBuilder;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
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
    public void testConstructor() {
        ImmutableTask mockTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        mockTasks.add(mockTask);
        ImmutableCondition<?> mockCondition = mock(ImmutableCondition.class);

        ImmutableValidatableBeforeConstraint constraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        
        assertEquals(mockTasks, constraint.getTasks());
        assertEquals(mockCondition, constraint.getCondition());
    }

    /**
     * Test validate is called
     */
    @Test
    public void testValidate() {
        ImmutableTask mockTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        mockTasks.add(mockTask);
        ImmutableCondition<?> mockCondition = mock(ImmutableCondition.class);

        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>> mockValidator = mock(ConstraintValidator.class);
        ImmutableValidatableBeforeConstraint constraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        constraint.validate(mockValidator);

        verify(mockValidator).validate(constraint);
    }

    /**
     * Test method for
     * {@link org.gerryai.htn.simple.constraint.impl.SimpleAfterConstraint#add(org.gerryai.htn.simple.constraint.validation.ConstraintValidator)}
     * .
     * 
     * @throws InvalidConstraint
     *             only if test fails
     */
    @Test
    public void testAdd() throws InvalidConstraint {
        ImmutableTask mockTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        mockTasks.add(mockTask);
        ImmutableCondition<?> mockCondition = mock(ImmutableCondition.class);

        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTerm<?>, ImmutableTask, ImmutableCondition<?>> mockValidator = mock(ConstraintValidator.class);
        ImmutableValidatableBeforeConstraint constraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockCondition)
                .build();
        constraint.add(mockValidator);

        verify(mockValidator).add(constraint);
    }

    /**
     * Test construction using copy and apply
     */
    @Test
    public <T extends ImmutableCondition<T>> void testCopyApply() {
        ImmutableSubstitution mockSubstitution = mock(ImmutableSubstitution.class);
        ImmutableTask mockTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        mockTasks.add(mockTask);
        ImmutableTermBuilder<T> mockConditionBuilderA = mock(ImmutableTermBuilder.class);
        ImmutableTermBuilder<T> mockConditionBuilderB = mock(ImmutableTermBuilder.class);
        ImmutableCondition<T> mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition<T> mockConditionB = mock(ImmutableCondition.class);
        when(mockConditionA.createCopyBuilder()).thenReturn(mockConditionBuilderA);
        when(mockConditionBuilderA.apply(mockSubstitution)).thenReturn(mockConditionBuilderB);
        when(mockConditionBuilderB.build()).thenReturn(mockConditionB);

        ImmutableValidatableBeforeConstraint initialConstraint = new SimpleBeforeConstraint.Builder()
                .addTasks(mockTasks)
                .setCondition(mockConditionA)
                .build();
        
        ImmutableValidatableBeforeConstraint constraint = initialConstraint.createCopyBuilder()
                .apply(mockSubstitution)
                .build();

        assertEquals(mockTasks, constraint.getTasks());
        assertEquals(mockConditionB, constraint.getCondition());
        //TODO: check substitution reuslts properly
    }

    /**
     * Test construction using copy and replace
     */
    @Test
    public void testCopyReplace() {
        ImmutableTask mockTaskA = mock(ImmutableTask.class);
        Set<ImmutableTask> mockTasks = new HashSet<ImmutableTask>();
        mockTasks.add(mockTaskA);
        ImmutableTask mockTaskB = mock(ImmutableTask.class);
        ImmutableTask mockTaskC = mock(ImmutableTask.class);
        Set<ImmutableTask> mockNewTasks = new HashSet<ImmutableTask>();
        mockNewTasks.add(mockTaskB);
        mockNewTasks.add(mockTaskC);
        ImmutableCondition<?> mockCondition = mock(ImmutableCondition.class);

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
