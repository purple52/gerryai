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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.simple.domain.ImmutableEffect;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.logic.Variable;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleOperatorTest {

    /**
     * Test setting the name.
     */
    @Test
    public final void testSetName() {
        ImmutableOperator operator = new SimpleOperator.Builder()
                .setName("testname")
                .build();
        assertEquals("testname", operator.getName());
    }

    /**
     * Test adding single arguments.
     */
    @Test
    public final void testAddArgument() {
        Variable mockVariableA = mock(Variable.class);
        Variable mockVariableB = mock(Variable.class);
        ImmutableOperator operator = new SimpleOperator.Builder()
                .addArgument(mockVariableA)
                .addArgument(mockVariableB)
                .build();
        assertEquals(2, operator.getArguments().size());
        assertEquals(operator.getArguments().get(0), mockVariableA);
        assertEquals(operator.getArguments().get(1), mockVariableB);
    }

    /**
     * Test adding sets of arguments. 
     */
    @Test
    public final void testAddArguments() {
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
        
        ImmutableOperator operator = new SimpleOperator.Builder()
                .addArguments(mockVariablesA)
                .addArguments(mockVariablesB)
                .build();
        
        List<Variable> expectedList = new ArrayList<Variable>();
        expectedList.add(mockVariableA);
        expectedList.add(mockVariableB);
        expectedList.add(mockVariableC);
        expectedList.add(mockVariableD);
        
        assertEquals(expectedList, operator.getArguments());
    }

    /**
     * Test adding single preconditions.
     */
    @Test
    public final void testAddPrecondition() {
        ImmutableCondition mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition mockConditionB = mock(ImmutableCondition.class);
        ImmutableOperator operator = new SimpleOperator.Builder()
                .addPrecondition(mockConditionA)
                .addPrecondition(mockConditionB)
                .build();
        assertEquals(2, operator.getPreconditions().size());
        assertTrue(operator.getPreconditions().contains(mockConditionA));
        assertTrue(operator.getPreconditions().contains(mockConditionB));
    }

    /**
     * Test adding sets of arguments.
     */
    @Test
    public final void testAddPreconditions() {
        ImmutableCondition mockConditionA = mock(ImmutableCondition.class);
        ImmutableCondition mockConditionB = mock(ImmutableCondition.class);
        Set<ImmutableCondition> mockConditionsA = new HashSet<ImmutableCondition>();
        mockConditionsA.add(mockConditionA);
        mockConditionsA.add(mockConditionB);
        ImmutableCondition mockConditionC = mock(ImmutableCondition.class);
        ImmutableCondition mockConditionD = mock(ImmutableCondition.class);
        Set<ImmutableCondition> mockConditionsB = new HashSet<ImmutableCondition>();
        mockConditionsB.add(mockConditionC);
        mockConditionsB.add(mockConditionD);
        
        ImmutableOperator operator = new SimpleOperator.Builder()
                .addPreconditions(mockConditionsA)
                .addPreconditions(mockConditionsB)
                .build();
        
        Set<ImmutableCondition> expectedConditions = new HashSet<ImmutableCondition>();
        expectedConditions.add(mockConditionA);
        expectedConditions.add(mockConditionB);
        expectedConditions.add(mockConditionC);
        expectedConditions.add(mockConditionD);
        
        assertEquals(expectedConditions, operator.getPreconditions());
    }

    /**
     * Test adding single effects.
     */
    @Test
    public final void testAddEffect() {
        ImmutableEffect mockEffectA = mock(ImmutableEffect.class);
        ImmutableEffect mockEffectB = mock(ImmutableEffect.class);
        ImmutableOperator operator = new SimpleOperator.Builder()
                .addEffect(mockEffectA)
                .addEffect(mockEffectB)
                .build();
        assertEquals(2, operator.getEffects().size());
        assertTrue(operator.getEffects().contains(mockEffectA));
        assertTrue(operator.getEffects().contains(mockEffectB));
    }

    /**
     * Test adding sets of effects.
     */
    @Test
    public final void testAddEffects() {
        ImmutableEffect mockEffectA = mock(ImmutableEffect.class);
        ImmutableEffect mockEffectB = mock(ImmutableEffect.class);
        Set<ImmutableEffect> mockEffectsA = new HashSet<ImmutableEffect>();
        mockEffectsA.add(mockEffectA);
        mockEffectsA.add(mockEffectB);
        ImmutableEffect mockEffectC = mock(ImmutableEffect.class);
        ImmutableEffect mockEffectD = mock(ImmutableEffect.class);
        Set<ImmutableEffect> mockEffectsB = new HashSet<ImmutableEffect>();
        mockEffectsA.add(mockEffectC);
        mockEffectsA.add(mockEffectD);
        
        ImmutableOperator operator = new SimpleOperator.Builder()
                .addEffects(mockEffectsA)
                .addEffects(mockEffectsB)
                .build();
        
        Set<ImmutableEffect> expectedEffects = new HashSet<ImmutableEffect>();
        expectedEffects.add(mockEffectA);
        expectedEffects.add(mockEffectB);
        expectedEffects.add(mockEffectC);
        expectedEffects.add(mockEffectD);
        
        assertEquals(expectedEffects, operator.getEffects());
    }

    /**
     * Test build.
     */
    @Test
    public final void testBuild() {
        Variable mockVariable = mock(Variable.class);
        List<Variable> mockArguments = new ArrayList<Variable>();
        mockArguments.add(mockVariable);
        ImmutableCondition mockCondition = mock(ImmutableCondition.class);
        Set<ImmutableCondition> mockConditions = new HashSet<ImmutableCondition>();
        mockConditions.add(mockCondition);
        ImmutableEffect mockEffect = mock(ImmutableEffect.class);
        Set<ImmutableEffect> mockEffects = new HashSet<ImmutableEffect>();
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
