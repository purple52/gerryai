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
    public void testPrecedingTasks() {
        @SuppressWarnings("unchecked")
        Set<SubstitutableTask> mockTasks = mock(Set.class);

        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
        constraint.setPrecedingTasks(mockTasks);

        assertEquals(mockTasks, constraint.getPrecedingTasks());
    }

    /**
     * Test get/set preceding tasks.
     */
    @Test
    public void testProcedingTasks() {
        @SuppressWarnings("unchecked")
        Set<SubstitutableTask> mockTasks = mock(Set.class);

        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
        constraint.setProcedingTasks(mockTasks);

        assertEquals(mockTasks, constraint.getProcedingTasks());
    }
    
    /**
     * Test get/set condition.
     */
    @Test
    public void testGetCondition() {
        SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);

        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
        constraint.setCondition(mockCondition);

        assertEquals(mockCondition, constraint.getCondition());
    }

    /**
     * Test validate is called
     */
    @Test
    public void testValidate() {
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockValidator = mock(ConstraintValidator.class);
        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
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
        @SuppressWarnings("unchecked")
        ConstraintValidator<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockValidator = mock(ConstraintValidator.class);
        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
        constraint.add(mockValidator);

        verify(mockValidator).add(constraint);
    }

    /**
     * Test substituter is called
     */
    @Test
    public void testApply() {
        @SuppressWarnings("unchecked")
        Substituter<SubstitutableTerm> mockSubstituter = mock(Substituter.class);
        SubstitutableCondition mockCondition = mock(SubstitutableCondition.class);

        SimpleBetweenConstraint constraint = new SimpleBetweenConstraint();
        constraint.setCondition(mockCondition);
        constraint.apply(mockSubstituter);

        verify(mockCondition).apply(mockSubstituter);
    }

}
