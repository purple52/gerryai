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
package org.gerryai.htn.simple.logic;

import java.util.List;
import java.util.Map;

/**
 * Interface for a variable factory.
 * @param <V> the type of variable this factory produces
 * @param <C> type of constant this factory produces
 * @param <P> type of predicate this factory produces
 * @param <T> type of term this factory uses
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface LogicFactory<V, C, P, T> {

	/**
	 * Create a variable.
	 * @param name the name of the variable
	 * @return the variable
	 */
	V createVariable(String name);
	
	/**
	 * Create a constant.
	 * @param name the name of the variable
	 * @return the variable
	 */
	C createConstant(String name);

	/**
     * Create a condition.
     * @param name the name of the predicate
     * @param terms a single term for the predicate
     * @return the condition
     */
    P createCondition(String name, T terms);
    
	/**
	 * Create a condition.
	 * @param name the name of the predicate
	 * @param terms list of terms for the predicate
	 * @return the condition
	 */
	P createCondition(String name, List<T> terms);
	
	/**
	 * Copy the supplied terms and apply the substitution provided.
	 * @param oldTerms terms to copy
	 * @param substitution substitution to apply
	 * @return the new terms
	 */
	List<T> apply(List<T> oldTerms, Map<T, T> substitution);
	
}
