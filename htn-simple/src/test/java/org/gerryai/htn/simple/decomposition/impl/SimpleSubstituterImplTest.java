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

import org.gerryai.htn.simple.constraint.ConstraintFactory;
import org.gerryai.htn.simple.constraint.impl.SimpleBeforeConstraint;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.SubstitutableVariable;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.logic.unification.Substitution;

import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleSubstituterImplTest {

	/**
	 * Test method for {@link org.gerryai.htn.simple.decomposition.impl.GenericSubstituter#apply(org.gerryai.htn.simple.constraint.impl.SimpleBeforeConstraint)}.
	 */
	@Test
	public void testApplySimpleBeforeConstraint() {
		
		@SuppressWarnings("unchecked")
		Substitution<SubstitutableTerm, SubstitutableVariable> mockUnifier = mock(Substitution.class);
		@SuppressWarnings("unchecked")
		ConstraintFactory<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> mockConstraintFactory = mock(ConstraintFactory.class);
		GenericSubstituter substituter = new GenericSubstituter(mockUnifier, mockConstraintFactory);
		
		SimpleBeforeConstraint constraint = mock(SimpleBeforeConstraint.class);
		
		SimpleBeforeConstraint updatedConstraint = (SimpleBeforeConstraint) substituter.apply(constraint);
		
		//assertEquals(updatedConstraint.getCondition(),constraint)
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.gerryai.htn.simple.decomposition.impl.GenericSubstituter#apply(org.gerryai.htn.simple.constraint.impl.SimpleAfterConstraint)}.
	 */
	@Test
	public void testApplySimpleAfterConstraint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.gerryai.htn.simple.decomposition.impl.GenericSubstituter#apply(org.gerryai.htn.simple.constraint.impl.SimpleBetweenConstraint)}.
	 */
	@Test
	public void testApplySimpleBetweenConstraint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.gerryai.htn.simple.decomposition.impl.GenericSubstituter#apply(org.gerryai.htn.simple.constraint.impl.SimplePrecedenceConstraint)}.
	 */
	@Test
	public void testApplySimplePrecedenceConstraint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.gerryai.htn.simple.decomposition.impl.GenericSubstituter#apply(org.gerryai.htn.simple.tasknetwork.impl.SimpleNonPrimitiveTask)}.
	 */
	@Test
	public void testApplySimpleNonPrimitiveTask() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.gerryai.htn.simple.decomposition.impl.GenericSubstituter#apply(org.gerryai.htn.simple.tasknetwork.impl.SimplePrimitiveTask)}.
	 */
	@Test
	public void testApplySimplePrimitiveTask() {
		fail("Not yet implemented");
	}

}
