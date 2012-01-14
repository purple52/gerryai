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

import java.util.ArrayList;
import java.util.List;


/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class PlanImpl implements Plan {

	/**
	 * List of actions that implement this plan.
	 */
	private List<Action> actions;
	
	/**
	 * Default constructor,
	 * Initialises the operators list to an empty list.
	 */
	public PlanImpl() {
		actions = new ArrayList<Action>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<Action> getActions() {
		return actions;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setActions(List<Action> actions) {
		this.actions = actions;
	}

}
