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
package org.gerryai.htn.plan;

import java.util.List;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Operator;


/**
 * Interface that a plan must implement.
 * @param <O> type of operator this plan uses
 * @param <I> type of condition the plan uses
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Plan<O extends Operator<I>, I extends Condition> {
	
	/**
	 * Get the actions that make up this plan.
	 * @return the actions
	 */
	List<Action<O, I>> getActions();
	
	/**
	 * Set the actions that make up this plan.
	 * @param actions actions to set
	 */
	void setActions(List<Action<O, I>> actions);

}
