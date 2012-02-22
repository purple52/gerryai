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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;

import java.util.List;

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
public class SimplePrecedenceConstraintTest {

    /**
     * Test get/set preceding task.
     */
    @Test
    public void testConstructor() {
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        SimplePrecedenceConstraint constraint = new SimplePrecedenceConstraint.Builder()
                .setPrecedingTask(mockPrecedingTask)
                .setProcedingTask(mockProcedingTask)
                .build();

        assertEquals(mockPrecedingTask, constraint.getPrecedingTask());
        assertEquals(mockProcedingTask, constraint.getProcedingTask());
    }

 
    /**
     * Test validate is called.
     */
    @Test
    public void testValidate() {
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockValidator = mock(ConstraintValidator.class);
        SimplePrecedenceConstraint constraint = new SimplePrecedenceConstraint.Builder()
                .setPrecedingTask(mockPrecedingTask)
                .setProcedingTask(mockProcedingTask)
                .build();
        constraint.validate(mockValidator);

        verify(mockValidator).validate(constraint);
    }

    /**
     * Test add is called.
     * 
     * @throws InvalidConstraint
     *             only if test fails
     */
    @Test
    public void testAdd() throws InvalidConstraint {
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockValidator = mock(ConstraintValidator.class);
        SimplePrecedenceConstraint constraint = new SimplePrecedenceConstraint.Builder()
                .setPrecedingTask(mockPrecedingTask)
                .setProcedingTask(mockProcedingTask)
                .build();
        constraint.add(mockValidator);

        verify(mockValidator).add(constraint);
    }

    /**
     * Test substituter is not called/
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testApply() {
        SubstitutableTask mockPrecedingTask = mock(SubstitutableTask.class);
        SubstitutableTask mockProcedingTask = mock(SubstitutableTask.class);
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);

        SimplePrecedenceConstraint constraint = new SimplePrecedenceConstraint.Builder()
                .setPrecedingTask(mockPrecedingTask)
                .setProcedingTask(mockProcedingTask)
                .build();
        
        constraint.apply(mockSubstituter);

        verify(mockSubstituter,never()).visit(any(List.class));
    }

}
