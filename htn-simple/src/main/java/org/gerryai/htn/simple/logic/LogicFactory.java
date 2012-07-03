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

import org.gerryai.logic.Constant;
import org.gerryai.logic.Function;
import org.gerryai.logic.Predicate;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.gerryai.logic.builder.SentenceBuilder;

/**
 * Interface for a logical term factory.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface LogicFactory {

    /**
     * Create a function.
     * @param name the name of the function
     * @param term single term for the function
     * @return the function
     */
    Function createFunction(String name, Term term);
 
    /**
     * Create a function.
     * @param name the name of the function
     * @param terms list of terms for the function
     * @return the function
     */
    Function createFunction(String name, List<Term> terms);
    
	/**
	 * Create a variable.
	 * @param name the name of the variable
	 * @return the variable
	 */
	Variable createVariable(String name);
	
	/**
	 * Create a constant.
	 * @param name the name of the variable
	 * @return the variable
	 */
	Constant createConstant(String name);

	/**
     * Create a predicate.
     * @param name the name of the predicate
     * @param term a single term for the predicate
     * @return the condition
     */
    Predicate createPredicate(String name, Term term);
    
	/**
	 * Create a predicate.
	 * @param name the name of the predicate
	 * @param terms list of terms for the predicate
	 * @return the condition
	 */
	Predicate createPredicate(String name, List<Term> terms);
	
	/**
	 * Copy the supplied terms and apply the substitution provided.
	 * @param oldTerms terms to copy
	 * @param substitution substitution to apply
	 * @return the new terms
	 */
	List<Term> apply(List<Term> oldTerms, Map<Term, Term> substitution);
	
	/**
	 * Get a builder to build a logical sentence.
	 * @return a fresh builder
	 */
	SentenceBuilder sentenceBuilder();
}
