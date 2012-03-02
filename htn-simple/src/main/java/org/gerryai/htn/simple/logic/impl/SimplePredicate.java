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
package org.gerryai.htn.simple.logic.impl;

import java.util.List;

import org.gerryai.htn.aima.AIMAConverter;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePredicate extends Predicate implements SubstitutableCondition, SubstitutableTerm {

	/**
	 * List of terms belonging to this predicate.
	 */
	private List<SubstitutableTerm> terms;
	
	/**
	 * Converter to help build the underlying AIMA objects.
	 */
	private static AIMAConverter<SubstitutableTerm, SimpleVariable, ImmutableTask> converter;
	
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.getSymbolicName();
	}
	
	/**
	 * Constructor, taking a name and a list of terms.
	 * @param predicateName the name
	 * @param terms the terms
	 */
	public SimplePredicate(String predicateName, List<SubstitutableTerm> terms) {
		super(predicateName, converter.convert(terms));
		this.terms = terms;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void apply(Substituter<SubstitutableTerm> substituter) {
		// TODO: this only works for atomic elements!
		substituter.visit(terms);
	}


}
