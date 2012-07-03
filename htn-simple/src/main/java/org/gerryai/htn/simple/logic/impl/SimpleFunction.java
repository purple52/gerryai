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

import org.gerryai.logic.Function;
import org.gerryai.logic.Term;

/**
 * Implementation of a function that uses a map to describe its return values.
 * @author David Edwards <david@more.fool.me.uk>
 */
public final class SimpleFunction implements Function {

    /**
     * Symbolic function name.
     */
    private String name;
    
    /**
     * List of arguments passed to this function.
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
    private SimpleFunction(Builder builder) {
        this.name = builder.name;
        this.terms = builder.terms;
    }

    /**
     * {@inheritDoc}
     */
    public Function applyToCopy(Map<Term, Term> substitution) {
        return new Builder()
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
    public static final class Builder {
        
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
         */
        protected Builder() {
            this.terms = new ArrayList<Term>();
        }
        
        /**
         * {@inheritDoc}
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public Builder addTerm(Term term) {
            this.terms.add(term);
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public Builder addTerm(List<Term> terms) {
            this.terms.addAll(terms);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public Builder copy(Function condition) {
            this.setName(condition.getName());
            this.terms.addAll(condition.getTerms());
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public Builder apply(
                Map<Term, Term> substitution) {
            List<Term> newTerms = new ArrayList<Term>(terms.size());
            for (Term oldTerm : terms) {
                Term newTerm = oldTerm.applyToCopy(substitution);
                newTerms.add(newTerm);
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public Function build() {
            return new SimpleFunction(this);
        }
    }
}
