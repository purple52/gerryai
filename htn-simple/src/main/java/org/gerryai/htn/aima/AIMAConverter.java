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
package org.gerryai.htn.aima;

import java.util.List;
import java.util.Map;

import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * Interface for a converter between AIMA first order logic classes and our classes.
 * @param <T> type of term the converter can handle
 * @param <V> type of variable the converter can handle
 * @param <K> type of task the converter can handle
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface AIMAConverter<T extends Term, V extends Variable, K extends Task<T>> {

	/**
	 * Convert a Gerry AI Task into an AIMA Predicate.
	 * @param task task to convert
	 * @return predicate
	 */
	Predicate convert(K task);
	
	/**
	 * Convert a Gerry AI Term into an AIMA Term.
	 * @param term term to convert
	 * @return AIMA term
	 */
	aima.core.logic.fol.parsing.ast.Term convert(Term term);
	
	/**
	 * Convert a list of Gerry AI Terms into AIMA Terms.
	 * @param terms list of terms to convert
	 * @return list of converted terms
	 */
	List<aima.core.logic.fol.parsing.ast.Term> convert(List<T> terms);
	
	/**
	 * Convert an AIMA Term into a Gerry AI Term.
	 * @param term term to convert
	 * @return converted term
	 */
	T convert(aima.core.logic.fol.parsing.ast.Term term);
	
	/**
	 * Convert a Gerry AI Variable into an AIMA Variable.
	 * @param variable variable to convert
	 * @return converted variable
	 *
	aima.core.logic.fol.parsing.ast.Variable convert(Variable variable);
	
	/**
	 * Convert an AIMA Variable into Gerry AI Variable.
	 * @param variable variable to convert
	 * @return converted variable
	 *
	Variable convert(aima.core.logic.fol.parsing.ast.Variable variable);
	*/
	
	/**
	 * Convert an AIMA Map<Variable, Term> into a Gerry AI Unifier.
	 * @param map map to convert
	 * @return the unifier
	 */
	ImmutableSubstitution convert(Map<aima.core.logic.fol.parsing.ast.Variable,
	        aima.core.logic.fol.parsing.ast.Term> map);
}
