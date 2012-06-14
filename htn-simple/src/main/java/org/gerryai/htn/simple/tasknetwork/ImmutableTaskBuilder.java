package org.gerryai.htn.simple.tasknetwork;

import java.util.List;
import java.util.Map;

import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * Interface for a builder for creating immutable tasks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableTaskBuilder {

	/**
	 * Set the name of the task.
	 * @param name the name
	 * @return the updated builder
	 */
	ImmutableTaskBuilder setName(String name);

	/**
	 * Set whether the task is primitive.
	 * @param isPrimitive true if the task is primitive
	 * @return the updated builder
	 */
	ImmutableTaskBuilder setIsPrimitive(boolean isPrimitive);
	
	/**
	 * Add an argument to the task.
	 * @param term the term to add
	 * @return the updated builder
	 */
	ImmutableTaskBuilder addArgument(ImmutableTerm<?> term);
	
	/**
	 * Add a list of arguments to the task.
	 * @param terms the terms to add
	 * @return the updated builder
	 */
	ImmutableTaskBuilder addArguments(List<ImmutableTerm<?>> terms);
	
	/**
	 * Copy from the provided task, overwriting any values already set.
	 * @param task the task to copy
	 * @return the updated builder
	 */
	ImmutableTaskBuilder copy(ImmutableTask task);
	
	/**
	 * Apply the provided substituter to the arguments provided so far.
	 * @param substitution the substitution to apply
	 * @return the updated builder
	 */
	ImmutableTaskBuilder apply(Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution);
	
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
	List<ImmutableTerm<?>> getArguments();
	
	/**
	 * Build the task.
	 * @return the task
	 */
	ImmutableTask build();

	/**
	 * Get the logic factory this builder can use.
	 * @return the factory
	 */
	ImmutableLogicFactory getLogicFactory();
	
}
