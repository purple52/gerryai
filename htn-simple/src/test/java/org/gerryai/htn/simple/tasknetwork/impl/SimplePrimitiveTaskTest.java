package org.gerryai.htn.simple.tasknetwork.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.gerryai.htn.simple.tasknetwork.impl.SimplePrimitiveTask;
import org.junit.Test;

public class SimplePrimitiveTaskTest {

	@Test
	public void testIsPrimitive() {
		SimpleTaskBuilder mockBuilder = mock(SimpleTaskBuilder.class);
		SimplePrimitiveTask task = new SimplePrimitiveTask(mockBuilder);
		assertTrue(task.isPrimitive());
	}

}
