package org.gerryai.htn.tasknetwork;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PrimitiveTaskTest {

	@Test
	public void testIsPrimitive() {
		PrimitiveTask task = new PrimitiveTask();
		assertTrue(task.isPrimitive());
	}

}
