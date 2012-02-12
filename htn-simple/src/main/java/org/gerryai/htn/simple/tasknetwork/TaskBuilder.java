package org.gerryai.htn.simple.tasknetwork;

import java.util.List;

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Interface for a factory for creating task networks.
 * @param <T> type of term this task builder will use
 * @param <K> type of task this builder produces
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskBuilder<T extends Term, K extends Task<T>> {

	/**
	 * Set the name of the task.
	 * @param name the name
	 * @return the updated builder
	 */
	TaskBuilder<T, K> setName(String name);

	/**
	 * Set whether the task is primitive.
	 * @param isPrimitive true if the task is primitive
	 * @return the updated builder
	 */
	TaskBuilder<T, K> setIsPrimitive(boolean isPrimitive);
	
	/**
	 * Add an argument to the task.
	 * @param term the term to add
	 * @return the updated builder
	 */
	TaskBuilder<T, K> addArgument(T term);
	
	/**
	 * Add a list of arguments to the task.
	 * @param terms the terms to add
	 * @return the updated builder
	 */
	TaskBuilder<T, K> addArguments(List<T> terms);
	
	/**
	 * Get the name of the task to be built.
	 * @return the name
	 */
	String getName();
	
	/**
	 * Get whether the task to be built is primitive.
	 * @return the name
	 */
	boolean getIsPrimitive();
	
	/**
	 * Get the arguments for the tack to be built.
	 * @return the arguments
	 */
	List<T> getArguments();
	
	/**
	 * Build the task.
	 * @return the task
	 */
	K build();
	
}
