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

import org.gerryai.htn.simple.domain.SubstitutableMethodBuilder;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleMethodTest {
	
	@Test
	public void testName() {
		String name = "testname";
		SubstitutableMethodBuilder mockBuilder = mock(SubstitutableMethodBuilder.class);
		when(mockBuilder.getName()).thenReturn(name);
		SimpleMethod method = new SimpleMethod(mockBuilder);
		assertEquals(name, method.getName());
	}

	@Test
	public void testTask() {
		SubstitutableTask task = mock(SubstitutableTask.class);
		SubstitutableMethodBuilder mockBuilder = mock(SubstitutableMethodBuilder.class);
		when(mockBuilder.getTask()).thenReturn(task);
		SimpleMethod method = new SimpleMethod(mockBuilder);
		assertEquals(task, method.getTask());
	}

	@Test
	public void testTaskNetwork() {
		SubstitutableTaskNetwork taskNetwork = mock(SubstitutableTaskNetwork.class);
		SubstitutableMethodBuilder mockBuilder = mock(SubstitutableMethodBuilder.class);
		when(mockBuilder.getTaskNetwork()).thenReturn(taskNetwork);
		SimpleMethod method = new SimpleMethod(mockBuilder);
		assertEquals(taskNetwork, method.getTaskNetwork());
	}

}
