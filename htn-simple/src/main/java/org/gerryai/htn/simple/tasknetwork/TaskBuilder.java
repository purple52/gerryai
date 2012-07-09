package org.gerryai.htn.simple.tasknetwork;

import java.util.List;
import java.util.Map;

import org.gerryai.htn.simple.logic.LogicFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Interface for a builder for creating immutable tasks.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface TaskBuilder {

	/**
	 * Set the name of the task.
	 * @param name the name
	 * @return the updated builder
	 */
	TaskBuilder setName(String name);

	/**
	 * Set whether the task is primitive.
	 * @param isPrimitive true if the task is primitive
	 * @return the updated builder
	 */
	TaskBuilder setIsPrimitive(boolean isPrimitive);
	
	/**
	 * Add an argument to the task.
	 * @param term the term to add
	 * @return the updated builder
	 */
	TaskBuilder addArgument(Term term);
	
	/**
	 * Add a list of arguments to the task.
	 * @param terms the terms to add
	 * @return the updated builder
	 */
	TaskBuilder addArguments(List<Term> terms);
	
	/**
	 * Copy from the provided task, overwriting any values already set.
	 * @param task the task to copy
	 * @return the updated builder
	 */
	TaskBuilder copy(Task task);
	
	/**
	 * Apply the provided substituter to the arguments provided so far.
	 * @param substitution the substitution to apply
	 * @return the updated builder
	 */
	TaskBuilder apply(Map<Term, Term> substitution);
	
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
	 * Get the arguments for the task to be built.
	 * @return the arguments
	 */
	List<Term> getArguments();
	
	/**
	 * Build the task.
	 * @return the task
	 */
	Task build();

	/**
	 * Get the logic factory this builder can use.
	 * @return the factory
	 */
	LogicFactory getLogicFactory();
	
}
