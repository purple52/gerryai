package org.gerryai.htn.simple.tasknetwork;

import java.util.List;

import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.tasknetwork.Task;

/**
 * Interface for a factory for creating task networks.
 * @param <T> type of term this task builder will use
 * @param <K> type of task this builder produces
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskBuilder<T extends SubstitutableTerm, K extends Task<T>> {

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
	 * Use the provided task as a base to create a new task.
	 * @param task the task to copy
	 * @return the updated builder
	 */
	TaskBuilder<T, K> setBaseTask(K task);
	
	/**
	 * Apply the provided substituter to the task when building.
	 * @param substituter the substituter to apply
	 * @return the updated builder
	 */
	TaskBuilder<T, K> setSubstituter(Substituter<T> substituter);
	
	/**
	 * Get the name of the task to be built.
	 * @return the name
	 */
	String getName();
	
	/**
	 * Get whether the task to be built is primitive.
	 * @return the name
	 */
	boolean isPrimitive();
	
	/**
	 * Get the arguments for the tack to be built.
	 * @return the arguments
	 */
	List<T> getArguments();
	
	/**
	 * Get the base task to build from.
	 * @return the base task
	 */
	K getBaseTask();
	
	/**
	 * Get the substituter to apply during building.
	 * @return the substituter
	 */
	Substituter<T> getSubstituter();
	
	/**
	 * Build the task.
	 * @return the task
	 */
	K build();
	
}
