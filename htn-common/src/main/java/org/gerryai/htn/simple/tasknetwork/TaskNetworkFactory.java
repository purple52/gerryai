package org.gerryai.htn.simple.tasknetwork;

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Interface for a factory for creating task networks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskNetworkFactory {

	/**
	 * Create a task network.

	 * @return the network
	 */
	TaskNetwork create();
	
	/**
	 * Create a task.
	 * @param name name of the task
	 * @return the task
	 */
	Task create(String name);
	
}
