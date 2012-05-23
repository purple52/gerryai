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

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.domain.ImmutableMethod;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainTest {

	@Test
	public void testOperators() {
		Set<ImmutableOperator> operators = new HashSet<ImmutableOperator>();
		
		SimpleDomainBuilder mockBuilder = mock(SimpleDomainBuilder.class);
		SimpleDomain domain = new SimpleDomain(mockBuilder);
		
		domain.setOperators(operators);
		
		assertEquals(operators, domain.getOperators());
	}

	@Test
	public void testMethods() {
		Set<ImmutableMethod> methods = new HashSet<ImmutableMethod>();
		
		SimpleDomainBuilder mockBuilder = mock(SimpleDomainBuilder.class);
		SimpleDomain domain = new SimpleDomain(mockBuilder);
		
		domain.setMethods(methods);
		
		assertEquals(methods, domain.getMethods());
	}
}
