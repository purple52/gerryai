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
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.domain.ImmutableCondition;
import org.gerryai.htn.tasknetwork.TaskNetwork;

/**
 * Extension of the task network interface that is immutable and only uses immutable components.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface ImmutableTaskNetwork extends TaskNetwork<ImmutableTask,
        ImmutableConstraint<?>> {

    /**
     * Create a new builder object that will build a copy of this immutable task network.
     * @param constraintValidator validator to use while building
     * @return the builder
     * @throws InvalidConstraint if one of the constraints is invalid
     */
    ImmutableTaskNetworkBuilder createCopyBuilder(ConstraintValidator<ImmutableTask,
            ImmutableCondition> constraintValidator) throws InvalidConstraint;
}
