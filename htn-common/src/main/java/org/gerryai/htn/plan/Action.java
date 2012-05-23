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

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Operator;
import org.gerryai.logic.Variable;

/**
 * Interface to be implemented by an action within a plan.
 * An action must an specify an operator that it actions and
 * a set of bindings to ground the operator.
 * @param <O> type of operator this action uses
 * @param <I> type of condition the action uses
 * @param <V> type of variable the action uses
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface Action<O extends Operator<I, V>, I extends Condition, V extends Variable> {

	/**
	 * Get the operator that this action uses.
	 * @return the operator
	 */
	O getOperator();
	
	/**
	 * Set the operator that this action uses.
	 * @param operator operator to set
	 */
	void setOperator(O operator);
	
	/**
	 * Get the bindings for this action.
	 * @return the bindings
	 */
	Bindings getBindings();
	
	/**
	 * Set the bindings for this action.
	 * @param bindings bindings to set
	 */
	void setBindings(Bindings bindings);
}
