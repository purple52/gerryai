package org.gerryai.htn.tasknetwork;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TaskImplTest {

	@Test
	public void testIsPrimitive() {
		TaskImpl task = new TaskImpl();
		assertTrue(task.isPrimitive());
	}

}
