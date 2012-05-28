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
package org.gerryai.htn.simple.plan;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.htn.plan.Plan;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Variable;

/**
 * @param <A> type of action this factory uses
 * @param <O> type of operator this factory uses
 * @param <I> type of condition the factory uses
 * @param <V> type of variable this factory uses
 * @param <S> type of constant this factory uses
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface PlanFactory<
        A extends Action<O, I, V, S>,
        O extends Operator<I, V>,
        I extends Condition,
        V extends Variable,
        S extends Constant> {

	/**
	 * Create a new empty plan.
	 * @return an empty plan
	 */
	Plan<A, O, I, V, S> create();
}
