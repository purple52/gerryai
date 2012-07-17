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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilder;
import org.junit.Test;

/**
 * Unit tests for SimpleDomain.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleDomainTest {

	/**
	 * Test for set/get operators.
	 */
	@Test
	public final void testOperators() {
		Set<Operator> operators = new HashSet<Operator>();
		
		ImmutableDomainBuilder mockBuilder = mock(ImmutableDomainBuilder.class);
		when(mockBuilder.getOperators()).thenReturn(operators);
		SimpleDomain domain = new SimpleDomain(mockBuilder);
		
		assertEquals(operators, domain.getOperators());
	}

	/**
	 * Test for set/get methods.
	 */
	@Test
	public final void testMethods() {
		Set<Method> methods = new HashSet<Method>();
		
		ImmutableDomainBuilder mockBuilder = mock(ImmutableDomainBuilder.class);
		when(mockBuilder.getMethods()).thenReturn(methods);
		SimpleDomain domain = new SimpleDomain(mockBuilder);
		
		assertEquals(methods, domain.getMethods());
	}
}
