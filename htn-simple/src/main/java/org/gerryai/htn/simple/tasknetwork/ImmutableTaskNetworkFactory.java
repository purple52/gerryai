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
package org.gerryai.htn.simple.tasknetwork;

import org.gerryai.htn.simple.constraint.ImmutableConstraint;
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface ImmutableTaskNetworkFactory extends
        TaskNetworkFactory<ImmutableTerm<?>, ImmutableTask, ImmutableTaskNetwork,
        ImmutableConstraint<?>, ImmutableSubstitution> {

    /**
     * Create a fresh task builder.
     * @return the builder
     */
    ImmutableTaskBuilder createTaskBuilder();
    
    /**
     * Create a fresh task network builder.
     * @return the builder
     */
    ImmutableTaskNetworkBuilder createTaskNetworkBuilder();
}
