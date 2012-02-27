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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
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
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockPrecedingTasks = new HashSet<SubstitutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockProcedingTasks = new HashSet<SubstitutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);
        
        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
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
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockPrecedingTasks = new HashSet<SubstitutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockProcedingTasks = new HashSet<SubstitutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockValidator = mock(ConstraintValidator.class);
 
        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
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
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockPrecedingTasks = new HashSet<SubstitutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockProcedingTasks = new HashSet<SubstitutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockValidator = mock(ConstraintValidator.class);
 
        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint.Builder()
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
    public void testCopyApply() {
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockPrecedingTasks = new HashSet<SubstitutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockProcedingTasks = new HashSet<SubstitutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);

        @SuppressWarnings("unchecked")
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);

        SimpleBetweenConstraint initialConstraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockCondition)
                .build();
        
        SimpleBetweenConstraint constraint = initialConstraint.createBuilder()
                .copy(initialConstraint)
                .apply(mockSubstituter)
                .build();

        assertEquals(mockPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockProcedingTasks, constraint.getProcedingTasks());
        verify(mockCondition).apply(mockSubstituter);
    }

    /**
     * Test construction using copy and replace
     */
    @Test
    public void testCopyReplace() {
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockPrecedingTasks = new HashSet<SubstitutableTask>();
        mockPrecedingTasks.add(mockPrecedingTask);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockProcedingTasks = new HashSet<SubstitutableTask>();
        mockProcedingTasks.add(mockProcedingTask);
        
        SubstitutableTask mockTaskB = mock(SubstitutableTask.class);
        SubstitutableTask mockTaskC = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockNewPrecedingTasks = new HashSet<SubstitutableTask>();
        mockNewPrecedingTasks.add(mockTaskB);
        mockNewPrecedingTasks.add(mockTaskC);
        SubstitutableTask mockTaskD = mock(SubstitutableTask.class);
        SubstitutableTask mockTaskE = mock(SubstitutableTask.class);
        Set<SubstitutableTask> mockNewProcedingTasks = new HashSet<SubstitutableTask>();
        mockNewProcedingTasks.add(mockTaskD);
        mockNewProcedingTasks.add(mockTaskE);
        
        SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);

        SimpleBetweenConstraint initialConstraint = new SimpleBetweenConstraint.Builder()
                .addPrecedingTasks(mockPrecedingTasks)
                .addProcedingTasks(mockProcedingTasks)
                .setCondition(mockCondition)
                .build();
        
        SimpleBetweenConstraint constraint = initialConstraint.createBuilder()
                .copy(initialConstraint)
                .replace(mockPrecedingTask, mockNewPrecedingTasks)
                .replace(mockProcedingTask, mockNewProcedingTasks)
                .build();

        assertEquals(mockNewPrecedingTasks, constraint.getPrecedingTasks());
        assertEquals(mockNewProcedingTasks, constraint.getProcedingTasks());
    }
}
