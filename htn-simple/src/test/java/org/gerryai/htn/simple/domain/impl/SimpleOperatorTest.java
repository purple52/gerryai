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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.domain.Effect;
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
		
		SimpleOperatorBuilder mockBuilder = mock(SimpleOperatorBuilder.class);
		SimpleOperator operator = new SimpleOperator(mockBuilder);
		
		operator.setName(name);
		assertEquals(name, operator.getName());
	}

	@Test
	public void testArguments() {
		List<Variable> arguments = new ArrayList<Variable>();
		SimpleOperatorBuilder mockBuilder = mock(SimpleOperatorBuilder.class);
		SimpleOperator operator = new SimpleOperator(mockBuilder);
		operator.setArguments(arguments);
		assertEquals(arguments, operator.getArguments());
	}
	
	@Test
	public void testPreconditions() {
		Set<SubstitutableCondition> conditions = new HashSet<SubstitutableCondition>();
		SimpleOperatorBuilder mockBuilder = mock(SimpleOperatorBuilder.class);
		SimpleOperator operator = new SimpleOperator(mockBuilder);
		operator.setPreconditions(conditions);
		assertEquals(conditions, operator.getPreconditions());
	}
	
	@Test
	public void testEffects() {
		Set<Effect> effects = new HashSet<Effect>();
		SimpleOperatorBuilder mockBuilder = mock(SimpleOperatorBuilder.class);
		SimpleOperator operator = new SimpleOperator(mockBuilder);
		operator.setEffects(effects);
		assertEquals(effects, operator.getEffects());
	}

}
