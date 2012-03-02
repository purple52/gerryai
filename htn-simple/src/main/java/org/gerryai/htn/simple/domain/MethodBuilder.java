/**
 *  Gerry AI - Open framework for automated planning algorithms
 *  Copyright (C) 2012  David Edwards
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gerryai.htn.simple.domain;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTaskNetwork;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Term;

/**
 * Interface for a class that can build methods.
 * 
 * @param <T>
 *            class of task the builder can handle
 * @param <K>
 *            type of task this builder can handle
 * @param <N>
 *            class of network the builder can handle
 * @param <C>
 *            class of constraint the builder can handle
 * @param <M>
 *            type of method this builder generates
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface MethodBuilder<
        T extends Term,
        K extends Task<T>,
        N extends TaskNetwork<T, K, C>,
        C extends Constraint<T>,
        M extends Method<T, K, N, C>> {

    /**
     * Set the name of the operator.
     * 
     * @param name
     *            the name
     * @return the updated builder
     */
    MethodBuilder<T, K, N, C, M> setName(String name);

    /**
     * Add an argument to the operator.
     * 
     * @param task
     *            the task to add
     * @return the updated builder
     */
    MethodBuilder<T, K, N, C, M> setTask(K task);

    /**
     * Add an argument to the operator.
     * 
     * @param taskNetwork
     *            the task network to add
     * @return the updated builder
     */
    MethodBuilder<T, K, N, C, M> setTaskNetwork(N taskNetwork);

    /**
     * Get the name of the method being built.
     * 
     * @return the name
     */
    String getName();

    /**
     * Get the task for the method being built.
     * 
     * @return the task
     */
    ImmutableTask getTask();

    /**
     * Get the task network for the method being built.
     * 
     * @return the task network
     */
    SubstitutableTaskNetwork getTaskNetwork();

    /**
     * Build the method.
     * 
     * @return the method
     */
    M build();
}
