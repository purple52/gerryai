package org.gerryai.htn.simple.tasknetwork;

import java.util.List;

import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;

/**
 * Interface for a factory for creating task networks.
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
	ImmutableTaskBuilder addArgument(SubstitutableTerm term);
	
	/**
	 * Add a list of arguments to the task.
	 * @param terms the terms to add
	 * @return the updated builder
	 */
	ImmutableTaskBuilder addArguments(List<SubstitutableTerm> terms);
	
	/**
	 * Copy from the provided task, overwriting any values already set.
	 * @param task the task to copy
	 * @return the updated builder
	 */
	ImmutableTaskBuilder copy(ImmutableTask task);
	
	/**
	 * Apply the provided substituter to the arguments provided so far.
	 * @param substituter the substituter to apply
	 * @return the updated builder
	 */
	ImmutableTaskBuilder apply(Substituter<SubstitutableTerm> substituter);
	
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
	List<SubstitutableTerm> getArguments();
	
	/**
	 * Build the task.
	 * @return the task
	 */
	ImmutableTask build();
	
}
