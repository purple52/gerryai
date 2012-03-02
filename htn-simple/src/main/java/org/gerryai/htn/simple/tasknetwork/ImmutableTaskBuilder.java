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
public interface ImmutableTaskBuilder<T extends SubstitutableTerm, K extends Task<T>> {

	/**
	 * Set the name of the task.
	 * @param name the name
	 * @return the updated builder
	 */
	ImmutableTaskBuilder<T, K> setName(String name);

	/**
	 * Set whether the task is primitive.
	 * @param isPrimitive true if the task is primitive
	 * @return the updated builder
	 */
	ImmutableTaskBuilder<T, K> setIsPrimitive(boolean isPrimitive);
	
	/**
	 * Add an argument to the task.
	 * @param term the term to add
	 * @return the updated builder
	 */
	ImmutableTaskBuilder<T, K> addArgument(T term);
	
	/**
	 * Add a list of arguments to the task.
	 * @param terms the terms to add
	 * @return the updated builder
	 */
	ImmutableTaskBuilder<T, K> addArguments(List<T> terms);
	
	/**
	 * Copy from the provided task, overwriting any values already set.
	 * @param task the task to copy
	 * @return the updated builder
	 */
	ImmutableTaskBuilder<T, K> copy(K task);
	
	/**
	 * Apply the provided substituter to the arguments provided so far.
	 * @param substituter the substituter to apply
	 * @return the updated builder
	 */
	ImmutableTaskBuilder<T, K> apply(Substituter<T> substituter);
	
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
	 * Build the task.
	 * @return the task
	 */
	K build();
	
}
