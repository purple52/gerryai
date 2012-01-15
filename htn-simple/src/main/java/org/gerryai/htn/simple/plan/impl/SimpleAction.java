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

import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.plan.Action;
import org.gerryai.logic.unification.Bindings;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleAction implements Action {

	/**
	 * Operator that this action is a grounded instance of.
	 */
	private Operator operator;
	
	/**
	 * Bindings that ground the operator.
	 */
	private Bindings bindings;
	
	/**
	 * {@inheritDoc}
	 */
	public final Operator getOperator() {
		return operator;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Bindings getBindings() {
		return bindings;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setBindings(Bindings bindings) {
		this.bindings = bindings;
	}

}