package org.gerryai.htn.simple.tasknetwork.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class SimpleNonPrimitiveTaskTest {

	@Test
	public void testIsPrimitive() {
		SimpleTaskBuilder mockBuilder = mock(SimpleTaskBuilder.class);
		SimpleNonPrimitiveTask task = new SimpleNonPrimitiveTask(mockBuilder);
		assertFalse(task.isPrimitive());
	}

}
