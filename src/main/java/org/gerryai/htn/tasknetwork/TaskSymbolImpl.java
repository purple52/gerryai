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
package org.gerryai.htn.tasknetwork;

import java.util.List;

import org.gerryai.logic.Term;

/**
 * Implementation of a task symbol.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class TaskSymbolImpl implements TaskSymbol {

	/**
	 * The name of the symbol.
	 */
	private String name;
	
	/**
	 * A list of the symbol's terms.
	 */
	private List<Term> terms;
	
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setName(String name) {
		this.name = name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<Term> getTerms() {
		return terms;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setTerms(List<Term> terms) {
		this.terms = terms;
	}
}
