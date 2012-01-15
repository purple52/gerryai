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
package org.gerryai.logic.unification;

import java.util.Map;

import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Class representing a unifier of two expressions as a set of substitutions.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class Unifier implements Substitution<Term> {
	
	/**
	 * The map of variables to terms.
	 */
	private Map<Variable, Term> map;

	/**
	 * {@inheritDoc}
	 */
	public final Map<Variable, Term> getMap() {
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setMap(Map<Variable, Term> map) {
		this.map = map;
	}

}
