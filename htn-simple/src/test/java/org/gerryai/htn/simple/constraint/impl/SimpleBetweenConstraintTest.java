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

import org.gerryai.htn.simple.constraint.ImmutableValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableConditionBuilder;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
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
    public void testConstructor() {
        ImmutableTask mockPrecedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockPrecedingTasks = new HashSet<ImmutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        ImmutableTask mockProcedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockProcedingTasks = new HashSet<ImmutableTask>();
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
     * Test validate is called
     */
    @Test
    public void testValidate() {
        ImmutableTask mockPrecedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockPrecedingTasks = new HashSet<ImmutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        ImmutableTask mockProcedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockProcedingTasks = new HashSet<ImmutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTerm<?>, ImmutableTask, ImmutableCondition> mockValidator = mock(ConstraintValidator.class);
 
        ImmutableValidatableBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
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
        ImmutableTask mockPrecedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockPrecedingTasks = new HashSet<ImmutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        ImmutableTask mockProcedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockProcedingTasks = new HashSet<ImmutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<ImmutableTerm<?>, ImmutableTask, ImmutableCondition> mockValidator = mock(ConstraintValidator.class);
 
        ImmutableValidatableBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockCondition)
                .build();
        
        constraint.add(mockValidator);

        verify(mockValidator).add(constraint);
    }

    /**
     * Test construction using copy and apply
     */
    @Test
    public <T extends ImmutableCondition> void testCopyApply() {
        
        ImmutableTask mockPrecedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockPrecedingTasks = new HashSet<ImmutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        ImmutableTask mockProcedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockProcedingTasks = new HashSet<ImmutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        
        ImmutableSubstitution mockSubstitution = mock(ImmutableSubstitution.class);
        ImmutableConditionBuilder mockConditionBuilderA = mock(ImmutableConditionBuilder.class);
        ImmutableConditionBuilder mockConditionBuilderB = mock(ImmutableConditionBuilder.class);
        ImmutableCondition mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition mockConditionB = mock(ImmutableCondition.class);
        when(mockConditionA.createCopyBuilder()).thenReturn(mockConditionBuilderA);
        when(mockConditionBuilderA.apply(mockSubstitution)).thenReturn(mockConditionBuilderB);
        when(mockConditionBuilderB.build()).thenReturn(mockConditionB);

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
     * Test construction using copy and replace
     */
    @Test
    public void testCopyReplace() {
        ImmutableTask mockPrecedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockPrecedingTasks = new HashSet<ImmutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        ImmutableTask mockProcedingTask = mock(ImmutableTask.class);
        Set<ImmutableTask> mockProcedingTasks = new HashSet<ImmutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        
        ImmutableTask mockTaskB = mock(ImmutableTask.class);
        ImmutableTask mockTaskC = mock(ImmutableTask.class);
        Set<ImmutableTask> mockNewPrecedingTasks = new HashSet<ImmutableTask>();
        mockNewPrecedingTasks.add(mockTaskB);
        mockNewPrecedingTasks.add(mockTaskC);
        ImmutableTask mockTaskD = mock(ImmutableTask.class);
        ImmutableTask mockTaskE = mock(ImmutableTask.class);
        Set<ImmutableTask> mockNewProcedingTasks = new HashSet<ImmutableTask>();
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
