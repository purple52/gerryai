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
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.domain.ImmutableOperatorBuilder;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperatorTest {

	@Test
	public void testName() {
		String name = "testname";
		
		ImmutableOperatorBuilder mockBuilder = mock(ImmutableOperatorBuilder.class);
		when(mockBuilder.getName()).thenReturn(name);
		SimpleOperator operator = new SimpleOperator(mockBuilder);
		
		assertEquals(name, operator.getName());
	}

	@Test
	public void testArguments() {
		List<ImmutableVariable<?>> arguments = new ArrayList<ImmutableVariable<?>>();
		ImmutableOperatorBuilder mockBuilder = mock(ImmutableOperatorBuilder.class);
		when(mockBuilder.getArguments()).thenReturn(arguments);
		
		SimpleOperator operator = new SimpleOperator(mockBuilder);

		assertEquals(arguments, operator.getArguments());
	}
	
	@Test
	public void testPreconditions() {
		Set<ImmutableCondition<?>> conditions = new HashSet<ImmutableCondition<?>>();
		ImmutableOperatorBuilder mockBuilder = mock(ImmutableOperatorBuilder.class);
		when(mockBuilder.getPreconditions()).thenReturn(conditions);
		
		SimpleOperator operator = new SimpleOperator(mockBuilder);

		assertEquals(conditions, operator.getPreconditions());
	}
	
	@Test
	public void testEffects() {
		Set<Effect> effects = new HashSet<Effect>();
		ImmutableOperatorBuilder mockBuilder = mock(ImmutableOperatorBuilder.class);
		when(mockBuilder.getEffects()).thenReturn(effects);
		
		SimpleOperator operator = new SimpleOperator(mockBuilder);

		assertEquals(effects, operator.getEffects());
	}

	   /**
     * Test builder construction.
     */
    @Test
    public void testSimpleOperatorBuilder() {
        // Create the builder under test
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder();
        
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
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder()
                .setName("testname");
        assertEquals("testname", builder.getName());
    }

    /**
     * Test adding single arguments.
     */
    @Test
    public void testAddArgument() {
        ImmutableVariable<?> mockVariableA = mock(ImmutableVariable.class);
        ImmutableVariable<?> mockVariableB = mock(ImmutableVariable.class);
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder()
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
        ImmutableVariable<?> mockVariableA = mock(ImmutableVariable.class);
        ImmutableVariable<?> mockVariableB = mock(ImmutableVariable.class);
        List<ImmutableVariable<?>> mockVariablesA = new ArrayList<ImmutableVariable<?>>();
        mockVariablesA.add(mockVariableA);
        mockVariablesA.add(mockVariableB);

        ImmutableVariable<?> mockVariableC = mock(ImmutableVariable.class);
        ImmutableVariable<?> mockVariableD = mock(ImmutableVariable.class);
        List<ImmutableVariable<?>> mockVariablesB = new ArrayList<ImmutableVariable<?>>();
        mockVariablesB.add(mockVariableC);
        mockVariablesB.add(mockVariableD);
        
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder()
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
        ImmutableCondition<?> mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition<?> mockConditionB = mock(ImmutableCondition.class);
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder()
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
        ImmutableCondition<?> mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition<?> mockConditionB = mock(ImmutableCondition.class);
        Set<ImmutableCondition<?>> mockConditionsA = new HashSet<ImmutableCondition<?>>();
        mockConditionsA.add(mockConditionA);
        mockConditionsA.add(mockConditionB);
        ImmutableCondition<?> mockConditionC = mock(ImmutableCondition.class);
        ImmutableCondition<?> mockConditionD = mock(ImmutableCondition.class);
        Set<ImmutableCondition<?>> mockConditionsB = new HashSet<ImmutableCondition<?>>();
        mockConditionsB.add(mockConditionC);
        mockConditionsB.add(mockConditionD);
        
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder()
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
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder()
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
        
        ImmutableOperatorBuilder builder = new SimpleOperator.Builder()
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
        ImmutableVariable<?> mockVariable = mock(ImmutableVariable.class);
        List<ImmutableVariable<?>> mockArguments = new ArrayList<ImmutableVariable<?>>();
        mockArguments.add(mockVariable);
        ImmutableCondition<?> mockCondition = mock(ImmutableCondition.class);
        Set<ImmutableCondition<?>> mockConditions = new HashSet<ImmutableCondition<?>>();
        mockConditions.add(mockCondition);
        Effect mockEffect = mock(Effect.class);
        Set<Effect> mockEffects = new HashSet<Effect>();
        mockEffects.add(mockEffect);
        
        ImmutableOperator operator = new SimpleOperator.Builder()
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
