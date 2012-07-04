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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gerryai.htn.simple.logic.LogicFactory;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Function;
import org.gerryai.logic.Predicate;
import org.gerryai.logic.Sentence;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.gerryai.logic.builder.SentenceBuilder;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleLogicFactory implements LogicFactory {

    /**
     * {@inheritDoc}
     */
    public final Function createFunction(String name, Term term) {
        List<Term> terms = new ArrayList<Term>(1);
        terms.add(term);
        return createFunction(name, terms);
    }
    
    /**
     * {@inheritDoc}
     */
    public final Function createFunction(String name, List<Term> terms) {
        return new SimpleFunction.Builder(name)
                .addTerm(terms)
                .build();
    }
    
	/**
	 * {@inheritDoc}
	 */
	public final Variable createVariable(String name) {
		return new SimpleVariable(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Constant createConstant(String name) {
		return new SimpleConstant(name);
	}
    
    /**
     * {@inheritDoc}
     */
    public final Predicate createPredicate(String name, Term term) {
        List<Term> terms = new ArrayList<Term>(1);
        terms.add(term);
        return createPredicate(name, terms);
    }
    
	/**
	 * {@inheritDoc}
	 */
	public final Predicate createPredicate(String name, List<Term> terms) {
		return new SimplePredicate.Builder(name)
		        .addTerms(terms)
		        .build();
	}
	
    /**
     * {@inheritDoc}
     */
	public final List<Term> apply(List<Term> oldTerms,
	        Map<Term, Term> substitution) {
        List<Term> updatedTerms = new ArrayList<Term>();        
        for (Term oldTerm : oldTerms) {
            if (substitution.containsKey(oldTerm)) {
                updatedTerms.add(substitution.get(oldTerm));
            } else {
                updatedTerms.add(oldTerm);
            }
        }
        
        return updatedTerms;
	}
	
    /**
     * {@inheritDoc}
     */
    public final Sentence copyApply(Sentence sentence,
            Map<Variable, Constant> bindings) {
        
        Map<Term, Term> substitution =
                new HashMap<Term, Term>(bindings);
        
        return sentence.applyToCopy(substitution);
    }

    /**
     * {@inheritDoc}
     */
    public final Term copyApply(Term function,
            Map<Variable, Constant> bindings) {
        
        Map<Term, Term> substitution =
                new HashMap<Term, Term>(bindings);
        
        return function.applyToCopy(substitution);
    }
  
    /**
     * {@inheritDoc}
     */
    public final SentenceBuilder sentenceBuilder() {
        return new SimpleSentenceBuilder();
    }
}
