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

import java.util.ArrayList;
import java.util.List;

import org.gerryai.htn.aima.AIMAConverter;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.decomposition.SimpleSubstituter;
import org.gerryai.htn.simple.decomposition.Substitutable;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;
import org.gerryai.logic.Term;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePredicate extends Predicate implements Condition, SimpleTerm {

	/**
	 * List of terms belonging to this predicate.
	 */
	private List<SimpleTerm> terms;
	
	private static AIMAConverter<SimpleTerm, SimpleVariable, SimpleTask> converter;
	
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.getSymbolicName();
	}
	
	/**
	 * @param predicateName
	 * @param updatedTerms
	 */
	public SimplePredicate(String predicateName, List<SimpleTerm> terms) {
		super(predicateName, converter.convert(terms));
		this.terms = terms;
	}

	/**
	 * {@inheritDoc}
	 */
	public final SimpleTerm apply(
			SimpleSubstituter<SimpleTerm, SimpleVariable, SimpleConstant> substituter) {
		List<SimpleTerm> updatedTerms = new ArrayList<SimpleTerm>();
		for (SimpleTerm term : terms) {
			updatedTerms.add(term.apply(substituter));
		}
		return new SimplePredicate(this.getSymbolicName(), updatedTerms);
	}

}
