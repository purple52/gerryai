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

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
import org.gerryai.logic.Variable;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperatorBuilderTest {

	/**
	 * Test builder construction.
	 */
	@Test
	public void testSimpleTaskNetworkBuilder() {
		// Create the builder under test
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder();
		
		// Check that the argument, preconditions and effects collections have been initialised
		assertTrue(builder.getArguments().isEmpty());
		assertTrue(builder.getPreconditions().isEmpty());
		assertTrue(builder.getEffects().isEmpty());
	}
	
	/**
	 * Test setting the name.
	 */
	@Test
	public void testSetName() {
		// Create the builder under test
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder()
				.setName("testname");
		assertEquals("testname", builder.getName());
	}

	/**
	 * Test adding single arguments.
	 */
	@Test
	public void testAddArgument() {
		Variable mockVariableA = mock(Variable.class);
		Variable mockVariableB = mock(Variable.class);
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder()
				.addArgument(mockVariableA)
				.addArgument(mockVariableB);
		assertEquals(2, builder.getArguments().size());
		assertEquals(builder.getArguments().get(0), mockVariableA);
		assertEquals(builder.getArguments().get(1), mockVariableB);
	}

	/**
	 * Test adding sets of arguments. 
	 */
	@Test
	public void testAddArguments() {
		Variable mockVariableA = mock(Variable.class);
		Variable mockVariableB = mock(Variable.class);
		List<Variable> mockVariablesA = new ArrayList<Variable>();
		mockVariablesA.add(mockVariableA);
		mockVariablesA.add(mockVariableB);

		Variable mockVariableC = mock(Variable.class);
		Variable mockVariableD = mock(Variable.class);
		List<Variable> mockVariablesB = new ArrayList<Variable>();
		mockVariablesB.add(mockVariableC);
		mockVariablesB.add(mockVariableD);
		
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder()
				.addArguments(mockVariablesA)
				.addArguments(mockVariablesB);
		assertEquals(4, builder.getArguments().size());
		assertEquals(builder.getArguments().get(0), mockVariableA);
		assertEquals(builder.getArguments().get(1), mockVariableB);
		assertEquals(builder.getArguments().get(2), mockVariableC);
		assertEquals(builder.getArguments().get(3), mockVariableD);
		}

	/**
	 * Test adding single preconditions.
	 */
	@Test
	public void testAddPrecondition() {
		Condition mockConditionA = mock(Condition.class);
		Condition mockConditionB = mock(Condition.class);
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder()
				.addPrecondition(mockConditionA)
				.addPrecondition(mockConditionB);
		assertEquals(2, builder.getPreconditions().size());
		assertTrue(builder.getPreconditions().contains(mockConditionA));
		assertTrue(builder.getPreconditions().contains(mockConditionB));
	}

	/**
	 * Test adding sets of arguments.
	 */
	@Test
	public void testAddPreconditions() {
		Condition mockConditionA = mock(Condition.class);
		Condition mockConditionB = mock(Condition.class);
		Set<Condition> mockConditionsA = new HashSet<Condition>();
		mockConditionsA.add(mockConditionA);
		mockConditionsA.add(mockConditionB);
		Condition mockConditionC = mock(Condition.class);
		Condition mockConditionD = mock(Condition.class);
		Set<Condition> mockConditionsB = new HashSet<Condition>();
		mockConditionsB.add(mockConditionC);
		mockConditionsB.add(mockConditionD);
		
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder()
				.addPreconditions(mockConditionsA)
				.addPreconditions(mockConditionsB);
		assertEquals(4, builder.getPreconditions().size());
		assertTrue(builder.getPreconditions().contains(mockConditionA));
		assertTrue(builder.getPreconditions().contains(mockConditionB));
		assertTrue(builder.getPreconditions().contains(mockConditionC));
		assertTrue(builder.getPreconditions().contains(mockConditionD));
	}

	/**
	 * Test adding single effects.
	 */
	@Test
	public void testAddEffect() {
		Effect mockEffectA = mock(Effect.class);
		Effect mockEffectB = mock(Effect.class);
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder()
				.addEffect(mockEffectA)
				.addEffect(mockEffectB);
		assertEquals(2, builder.getEffects().size());
		assertTrue(builder.getEffects().contains(mockEffectA));
		assertTrue(builder.getEffects().contains(mockEffectB));
	}

	/**
	 * Test adding sets of effects.
	 */
	@Test
	public void testAddEffects() {
		Effect mockEffectA = mock(Effect.class);
		Effect mockEffectB = mock(Effect.class);
		Set<Effect> mockEffectsA = new HashSet<Effect>();
		mockEffectsA.add(mockEffectA);
		mockEffectsA.add(mockEffectB);
		Effect mockEffectC = mock(Effect.class);
		Effect mockEffectD = mock(Effect.class);
		Set<Effect> mockEffectsB = new HashSet<Effect>();
		mockEffectsA.add(mockEffectC);
		mockEffectsA.add(mockEffectD);
		
		SimpleOperatorBuilder builder = new SimpleOperatorBuilder()
				.addEffects(mockEffectsA)
				.addEffects(mockEffectsB);
		assertEquals(4, builder.getEffects().size());
		assertTrue(builder.getEffects().contains(mockEffectA));
		assertTrue(builder.getEffects().contains(mockEffectB));
		assertTrue(builder.getEffects().contains(mockEffectC));
		assertTrue(builder.getEffects().contains(mockEffectD));
	}

	/**
	 * Test build.
	 */
	@Test
	public void testBuild() {
		Variable mockVariable = mock(Variable.class);
		List<Variable> mockArguments = new ArrayList<Variable>();
		mockArguments.add(mockVariable);
		Condition mockCondition = mock(Condition.class);
		Set<Condition> mockConditions = new HashSet<Condition>();
		mockConditions.add(mockCondition);
		Effect mockEffect = mock(Effect.class);
		Set<Effect> mockEffects = new HashSet<Effect>();
		mockEffects.add(mockEffect);
		
		SimpleOperator operator = new SimpleOperatorBuilder()
				.setName("testname")
				.addArguments(mockArguments)
				.addPreconditions(mockConditions)
				.addEffects(mockEffects)
				.build();
		
		assertEquals("testname", operator.getName());
		assertEquals(1, operator.getArguments().size());
		assertTrue(operator.getArguments().contains(mockVariable));
		assertEquals(1, operator.getPreconditions().size());
		assertTrue(operator.getPreconditions().contains(mockCondition));
		assertEquals(1, operator.getEffects().size());
		assertTrue(operator.getEffects().contains(mockEffect));
	}

}
