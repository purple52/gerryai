package org.gerryai.htn.simple.tasknetwork;

import java.util.List;

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Interface for a factory for creating task networks.
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
	 * Build the task.
	 * @return the task
	 */
	Task build();
	
}
