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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.gerryai.logic.Predicate;
import org.gerryai.logic.Term;
import org.gerryai.logic.builder.PredicateBuilder;

import com.google.common.base.Objects;

/**
 * Simple implementation of an immutable condition.
 * @author David Edwards <david@more.fool.me.uk>
 */
public final class SimplePredicate implements Predicate {
    
    /**
     * Symbolic name of the underlying predicate.
     */
    private String name;
    
	/**
	 * List of terms belonging to the underlying predicate.
	 */
	private List<Term> terms;
	
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}

	/**
     * {@inheritDoc}
     */
	public List<Term> getTerms() {
	    return Collections.unmodifiableList(terms);
	}
	
	/**
	 * Constructor using a builder.
	 * @param builder the builder
	 */
	private SimplePredicate(Builder builder) {
	    this.name = builder.name;
		this.terms = builder.terms;
	}

    /**
     * {@inheritDoc}
     */
    public Predicate applyToCopy(Map<Term, Term> substitution) {
        return new Builder(this.name)
            .copy(this)
            .apply(substitution)
            .build();
    }
  
    /**
     * {@inheritDoc}
     */
    public boolean isGround() {
        for (Term term : terms) {
            if (!term.isGround()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builder class for SimplePredicate.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements PredicateBuilder {
        
        /**
         * Name of the term to be built.
         */
        private String name;
        
        /**
         * List of terms belonging to the condition to be built.
         */
        private List<Term> terms;
        
        /**
         * Constructor.
         * @param name the name of the predicate to be built
         */
        protected Builder(String name) {
            this.name = name;
            this.terms = new ArrayList<Term>();
        }

        /**
         * {@inheritDoc}
         */
        public final Builder addTerm(Term term) {
            this.terms.add(term);
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final Builder addTerms(List<Term> terms) {
            this.terms.addAll(terms);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder copy(Predicate predicate) {
            this.name = predicate.getName();
            this.terms.addAll(predicate.getTerms());
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder apply(Map<Term, Term> substitution) {
            List<Term> newTerms = new ArrayList<Term>(terms.size());
            for (Term oldTerm : terms) {
                newTerms.add(oldTerm.applyToCopy(substitution));
            }
            terms = newTerms;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Predicate build() {
            return new SimplePredicate(this);
        }

    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(name, terms);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Predicate) {
            final Predicate other = (Predicate) obj;
            return Objects.equal(name, other.getName())
                && Objects.equal(terms, other.getTerms());
        } else {
            return false;
        }
    }
}
