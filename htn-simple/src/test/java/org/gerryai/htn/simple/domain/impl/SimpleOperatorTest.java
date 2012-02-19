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
package org.gerryai.htn.simple.domain.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.simple.domain.SubstitutableOperatorBuilder;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.logic.Variable;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperatorTest {

	@Test
	public void testName() {
		String name = "testname";
		
		SubstitutableOperatorBuilder mockBuilder = mock(SubstitutableOperatorBuilder.class);
		when(mockBuilder.getName()).thenReturn(name);
		SimpleOperator operator = new SimpleOperator(mockBuilder);
		
		assertEquals(name, operator.getName());
	}

	@Test
	public void testArguments() {
		List<Variable> arguments = new ArrayList<Variable>();
		SubstitutableOperatorBuilder mockBuilder = mock(SubstitutableOperatorBuilder.class);
		when(mockBuilder.getArguments()).thenReturn(arguments);
		
		SimpleOperator operator = new SimpleOperator(mockBuilder);

		assertEquals(arguments, operator.getArguments());
	}
	
	@Test
	public void testPreconditions() {
		Set<SubstitutableCondition> conditions = new HashSet<SubstitutableCondition>();
		SubstitutableOperatorBuilder mockBuilder = mock(SubstitutableOperatorBuilder.class);
		when(mockBuilder.getPreconditions()).thenReturn(conditions);
		
		SimpleOperator operator = new SimpleOperator(mockBuilder);

		assertEquals(conditions, operator.getPreconditions());
	}
	
	@Test
	public void testEffects() {
		Set<Effect> effects = new HashSet<Effect>();
		SubstitutableOperatorBuilder mockBuilder = mock(SubstitutableOperatorBuilder.class);
		when(mockBuilder.getEffects()).thenReturn(effects);
		
		SimpleOperator operator = new SimpleOperator(mockBuilder);

		assertEquals(effects, operator.getEffects());
	}

}
