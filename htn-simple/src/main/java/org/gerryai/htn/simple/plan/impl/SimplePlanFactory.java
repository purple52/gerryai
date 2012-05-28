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

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.simple.domain.ImmutableOperator;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableConstant;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.plan.ImmutableAction;
import org.gerryai.htn.simple.plan.PlanFactory;

/**
 * Factory for creating simple plans.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimplePlanFactory implements PlanFactory<ImmutableAction,
        ImmutableOperator, ImmutableCondition<?>, ImmutableVariable<?>, ImmutableConstant<?>> {

	/**
	 * {@inheritDoc}
	 */
	public final SimplePlan create() {
		SimplePlan plan = new SimplePlan();
		List<ImmutableAction> actions = new ArrayList<ImmutableAction>();
		plan.setActions(actions);
		
		return plan;
	}

}
