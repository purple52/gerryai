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
package org.gerryai.htn.simple.plan.impl;

import java.util.List;

import org.gerryai.htn.plan.Plan;
import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableConstant;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.plan.ImmutableAction;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePlan implements Plan<ImmutableAction, ImmutableOperator,
        ImmutableCondition<?>, ImmutableVariable<?>, ImmutableConstant<?>> {

	/**
	 * List of actions that implement this plan.
	 */
	private List<ImmutableAction> actions;
	
	/**
	 * {@inheritDoc}
	 */
	public final List<ImmutableAction> getActions() {
		return actions;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setActions(List<ImmutableAction> actions) {
		this.actions = actions;
	}

}
