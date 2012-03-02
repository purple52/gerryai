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
package org.gerryai.htn.simple.domain.impl;

import org.gerryai.htn.simple.domain.SubstitutableMethod;
import org.gerryai.htn.simple.domain.SubstitutableMethodBuilder;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;
import org.gerryai.htn.simple.tasknetwork.ImmutableTaskNetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 * 
 */
public class SimpleMethodBuilder implements SubstitutableMethodBuilder {

    /**
     * Name of the method being built.
     */
    private String name;

    /**
     * Task that the method being built will decompose.
     */
    private ImmutableTask task;

    /**
     * Task network that the method being built decomposes into.
     */
    private ImmutableTaskNetwork taskNetwork;

    /**
     * {@inheritDoc}
     */
    public final SubstitutableMethodBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public final SubstitutableMethodBuilder setTask(ImmutableTask task) {
        this.task = task;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public final SubstitutableMethodBuilder setTaskNetwork(
            ImmutableTaskNetwork taskNetwork) {
        this.taskNetwork = taskNetwork;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public final SubstitutableMethod build() {
        return new SimpleMethod(this);
    }

    /**
     * Get the name of the method being built.
     * 
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Get the task for the method being built.
     * 
     * @return the task
     */
    public final ImmutableTask getTask() {
        return task;
    }

    /**
     * Get the task network for the method being built.
     * 
     * @return the task network
     */
    public final ImmutableTaskNetwork getTaskNetwork() {
        return taskNetwork;
    }

}
