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
package org.gerryai.htn.simple.problem.impl;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.simple.problem.ImmutableState;
import org.gerryai.htn.simple.problem.ImmutableStateBuilder;
import org.gerryai.htn.simple.problem.ImmutableStateService;

/**
 * Implementation of a state service that can handle immutable objects.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleStateService implements ImmutableStateService {

    /**
     * {@inheritDoc}
     */
    public final ImmutableStateBuilder createStateBuilder() {
        return new SimpleState.Builder();
    }
    
    /**
     * {@inheritDoc}
     */
    public final boolean ask(ImmutableState state, Condition condition) {
        return state.ask(condition);
    }

    /**
     * {@inheritDoc}
     */
    public final ImmutableState tell(ImmutableState state, Effect effect) {
        return state.createCopyBuilder()
                .tell(effect)
                .build();
    }
     
}
