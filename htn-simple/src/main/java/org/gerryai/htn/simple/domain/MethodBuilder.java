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

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Interface for a class that can build methods.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface MethodBuilder {

    /**
     * Set the name of the operator.
     * @param name the name
     * @return the updated builder
     */
    MethodBuilder setName(String name);

    /**
     * Add an argument to the operator.
     * @param task the task to add
     * @return the updated builder
     */
    MethodBuilder setTask(Task task);

    /**
     * Add an argument to the operator.
     * @param taskNetwork the task network to add
     * @return the updated builder
     */
    MethodBuilder setTaskNetwork(TaskNetwork taskNetwork);

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
    Task getTask();

    /**
     * Get the task network for the method being built.
     * 
     * @return the task network
     */
    TaskNetwork getTaskNetwork();

    /**
     * Build the method.
     * 
     * @return the method
     */
    Method build();
}
