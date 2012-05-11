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
package org.gerryai.htn.simple.tasknetwork.impl;

import static org.mockito.Mockito.mock;

import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleTaskNetworkFactoryTest {

	/**
	 * Test task builder creation.
	 */
	@Test
	public void testCreateTaskBuilder() {
		@SuppressWarnings("unchecked")
		ConstraintValidatorFactory<SubstitutableTerm, ImmutableTask, SubstitutableCondition> mockConstraintValidatorFactory = mock(ConstraintValidatorFactory.class);
		//SimpleTaskNetworkFactory factory = new SimpleTaskNetworkFactory(mockConstraintValidatorFactory);
		//assertTrue(factory.createTask() instanceof SimpleTask.Builder);
	}
	
	/**
	 * Test task network builder creation.
	 */
	@Test
	public void testCreateTaskNetworkBuilder() {
		@SuppressWarnings("unchecked")
		ConstraintValidatorFactory<SubstitutableTerm, ImmutableTask, SubstitutableCondition> mockConstraintValidatorFactory = mock(ConstraintValidatorFactory.class);
		//SimpleTaskNetworkFactory factory = new SimpleTaskNetworkFactory(mockConstraintValidatorFactory);
		//assertTrue(factory.createTaskNetworkBuilder() instanceof SimpleTaskNetwork.Builder);
	}

}
