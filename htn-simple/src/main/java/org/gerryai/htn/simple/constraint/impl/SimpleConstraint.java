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
package org.gerryai.htn.simple.constraint.impl;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.simple.constraint.ValidatableConstraint;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;

/**
 * Interface for constraints that support the operations required by this HTN implementation.
 * @param <C> the type of constraint being implemented
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface SimpleConstraint<C extends SimpleConstraint<C>>
        extends
        Constraint<SubstitutableTerm>,
        ValidatableConstraint<SubstitutableTerm, SubstitutableTask, SubstitutableCondition> {

    /**
     * Create a new builder object that can build this type of constraint.
     * The builder returned is a simple constraint builder, so only supports
     * the build mechanisms that are general to all SimpleConstraint classes.
     * @return the builder
     */
    SimpleConstraintBuilder<C> createCopyBuilder();
}
