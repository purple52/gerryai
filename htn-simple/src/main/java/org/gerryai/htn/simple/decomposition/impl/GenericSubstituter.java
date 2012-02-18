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
package org.gerryai.htn.simple.decomposition.impl;

import java.util.List;

import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.SubstitutableVariable;
import org.gerryai.logic.unification.Substitution;

/**
 * Generic class for doing substitutions using the visitor pattern.
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class GenericSubstituter implements Substituter<SubstitutableTerm> {

	/**
	 * The unifier containing the substitution to use.
	 */
	private Substitution<SubstitutableTerm, SubstitutableVariable> unifier;
	
	/**
	 * Constructor taking a unifier.
	 * @param unifier the unifier to work with
	 */
	public GenericSubstituter(Substitution<SubstitutableTerm, SubstitutableVariable> unifier) {
		this.unifier = unifier;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void visit(List<SubstitutableTerm> terms) {
		
		for (SubstitutableTerm term : terms) {
			if (!term.isCompound()) {
				if (unifier.getMap().containsKey(term)) {
					terms.set(terms.indexOf(term), unifier.getMap().get(term));
				}
			} else {
				term.apply(this);
			}
		}
	}
}
