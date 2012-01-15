package org.gerryai.htn.simple.tasknetwork.impl;

import static org.junit.Assert.assertTrue;

import org.gerryai.htn.simple.tasknetwork.impl.PrimitiveTask;
import org.junit.Test;

public class PrimitiveTaskTest {

	@Test
	public void testIsPrimitive() {
		PrimitiveTask task = new PrimitiveTask();
		assertTrue(task.isPrimitive());
	}

}
