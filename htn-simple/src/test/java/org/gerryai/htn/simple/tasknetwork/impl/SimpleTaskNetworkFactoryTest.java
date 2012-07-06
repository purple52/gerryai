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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.gerryai.htn.simple.constraint.validation.ConstraintValidatorFactory;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.logic.LogicFactory;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.junit.Test;

/**
 * Unit tests for SimpleTaskNetworkFactory.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleTaskNetworkFactoryTest {

	/**
	 * Test task builder creation.
	 */
	@Test
	public final void testCreateTaskBuilder() {
		@SuppressWarnings("unchecked")
		ConstraintValidatorFactory<ImmutableTask, ImmutableCondition> mockConstraintValidatorFactory
				= mock(ConstraintValidatorFactory.class);
		LogicFactory mockLogicFactory = mock(LogicFactory.class);
		
		SimpleTaskNetworkFactory factory
				= new SimpleTaskNetworkFactory(mockConstraintValidatorFactory, mockLogicFactory);
		
		assertTrue(factory.createTaskBuilder() instanceof SimpleTask.Builder);
	}
	
	/**
	 * Test task network builder creation.
	 */
	@Test
	public final void testCreateTaskNetworkBuilder() {
		@SuppressWarnings("unchecked")
		ConstraintValidatorFactory<ImmutableTask, ImmutableCondition> mockConstraintValidatorFactory
				= mock(ConstraintValidatorFactory.class);
		LogicFactory mockLogicFactory = mock(LogicFactory.class);
		
		SimpleTaskNetworkFactory factory =
				new SimpleTaskNetworkFactory(mockConstraintValidatorFactory, mockLogicFactory);
		assertTrue(factory.createTaskNetworkBuilder() instanceof SimpleTaskNetwork.Builder);
	}

}
