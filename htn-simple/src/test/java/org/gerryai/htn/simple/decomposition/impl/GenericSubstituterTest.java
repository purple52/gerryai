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
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.SubstitutableVariable;
import org.gerryai.logic.unification.Substitution;

import org.junit.Test;

/**
 * Unit tests for the GenericSubstituter class.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class GenericSubstituterTest {

	/**
	 * Empty list gets nothing added.
	 */
	@Test
	public void testEmptyList() {
		SubstitutableVariable mockVariable = mock(SubstitutableVariable.class);
		SubstitutableTerm mockTerm = mock(SubstitutableTerm.class);
		Map<SubstitutableVariable,SubstitutableTerm> map = new HashMap<SubstitutableVariable,SubstitutableTerm>();
		map.put(mockVariable, mockTerm);
		@SuppressWarnings("unchecked")
		Substitution<SubstitutableTerm, SubstitutableVariable> mockSubstitution = mock(Substitution.class);
		when(mockSubstitution.getMap()).thenReturn(map);
		
		List<SubstitutableTerm> mockTerms = new ArrayList<SubstitutableTerm>();
		
		GenericSubstituter substituter = new GenericSubstituter(mockSubstitution);
		substituter.visit(mockTerms);
		
		assertEquals(0, mockTerms.size());
	}
	
	/**
	 * Empty substitution makes no changes.
	 */
	@Test
	public void testEmptySubstitution() {
		
		@SuppressWarnings("unchecked")
		Substitution<SubstitutableTerm, SubstitutableVariable> mockSubstitution = mock(Substitution.class);
		Map<SubstitutableVariable,SubstitutableTerm> map = new HashMap<SubstitutableVariable,SubstitutableTerm>();
		when(mockSubstitution.getMap()).thenReturn(map);
		
		SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
		SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
		List<SubstitutableTerm> mockTerms = new ArrayList<SubstitutableTerm>();
		mockTerms.add(mockTermA);
		mockTerms.add(mockTermB);
		
		GenericSubstituter substituter = new GenericSubstituter(mockSubstitution);
		substituter.visit(mockTerms);
		
		assertEquals(2, mockTerms.size());
		assertEquals(mockTermA, mockTerms.get(0));
		assertEquals(mockTermB, mockTerms.get(1));
	}

	/**
	 * Test a single substitution at the start.
	 */
	@Test
	public void testSingleSubstitutionAtStart() {
        SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
        SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
        SubstitutableVariable mockVariableA = mock(SubstitutableVariable.class);
        List<SubstitutableTerm> mockTerms = new ArrayList<SubstitutableTerm>();
        mockTerms.add(mockVariableA);
        mockTerms.add(mockTermB);
        
        @SuppressWarnings("unchecked")
        Substitution<SubstitutableTerm, SubstitutableVariable> mockSubstitution = mock(Substitution.class);
        Map<SubstitutableVariable,SubstitutableTerm> map = new HashMap<SubstitutableVariable,SubstitutableTerm>();
        map.put(mockVariableA, mockTermA);
        when(mockSubstitution.getMap()).thenReturn(map);
       
        GenericSubstituter substituter = new GenericSubstituter(mockSubstitution);
        substituter.visit(mockTerms);
        
        assertEquals(2, mockTerms.size());
        assertEquals(mockTermA, mockTerms.get(0));
        assertEquals(mockTermB, mockTerms.get(1));
	}

    /**
     * Test a single substitution at the start.
     */
    @Test
    public void testSingleSubstitutionAtEnd() {
        SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
        SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
        SubstitutableVariable mockVariableA = mock(SubstitutableVariable.class);
        List<SubstitutableTerm> mockTerms = new ArrayList<SubstitutableTerm>();
        mockTerms.add(mockTermA);
        mockTerms.add(mockVariableA);
        
        @SuppressWarnings("unchecked")
        Substitution<SubstitutableTerm, SubstitutableVariable> mockSubstitution = mock(Substitution.class);
        Map<SubstitutableVariable,SubstitutableTerm> map = new HashMap<SubstitutableVariable,SubstitutableTerm>();
        map.put(mockVariableA, mockTermB);
        when(mockSubstitution.getMap()).thenReturn(map);
       
        GenericSubstituter substituter = new GenericSubstituter(mockSubstitution);
        substituter.visit(mockTerms);
        
        assertEquals(2, mockTerms.size());
        assertEquals(mockTermA, mockTerms.get(0));
        assertEquals(mockTermB, mockTerms.get(1));
    }

    /**
     * Test double substitution.
     */
    @Test
    public void testDoubleSubstitution() {
        SubstitutableTerm mockTermA = mock(SubstitutableTerm.class);
        SubstitutableTerm mockTermB = mock(SubstitutableTerm.class);
        SubstitutableVariable mockVariableA = mock(SubstitutableVariable.class);
        SubstitutableVariable mockVariableB = mock(SubstitutableVariable.class);
        List<SubstitutableTerm> mockTerms = new ArrayList<SubstitutableTerm>();
        mockTerms.add(mockVariableA);
        mockTerms.add(mockVariableB);
        
        @SuppressWarnings("unchecked")
        Substitution<SubstitutableTerm, SubstitutableVariable> mockSubstitution = mock(Substitution.class);
        Map<SubstitutableVariable,SubstitutableTerm> map = new HashMap<SubstitutableVariable,SubstitutableTerm>();
        map.put(mockVariableA, mockTermA);
        map.put(mockVariableB, mockTermB);
        when(mockSubstitution.getMap()).thenReturn(map);
       
        GenericSubstituter substituter = new GenericSubstituter(mockSubstitution);
        substituter.visit(mockTerms);
        
        assertEquals(2, mockTerms.size());
        assertEquals(mockTermA, mockTerms.get(0));
        assertEquals(mockTermB, mockTerms.get(1));
    }

}
