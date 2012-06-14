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

import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableConditionBuilder;
import org.gerryai.htn.simple.logic.ImmutableTerm;

/**
 * Simple implementation of an immutable condition.
 * @author David Edwards <david@more.fool.me.uk>
 */
public final class SimpleCondition implements ImmutableCondition {
    
    /**
     * Symbolic name of the underlying predicate.
     */
    private String name;
    
	/**
	 * List of terms belonging to the underlying predicate.
	 */
	private List<ImmutableTerm<?>> terms;
	
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}

	/**
     * {@inheritDoc}
     */
	public List<ImmutableTerm<?>> getTerms() {
	    return Collections.unmodifiableList(terms);
	}
	
	/**
	 * Constructor using a builder.
	 * @param builder the builder
	 */
	private SimpleCondition(Builder builder) {
	    this.name = builder.name;
		this.terms = builder.terms;
	}

    /**
     * {@inheritDoc}
     */
    public ImmutableConditionBuilder createCopyBuilder() {
        return new Builder()
            .copy(this);
    }
    
    /**
     * Builder class for SimplePredicate.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements ImmutableConditionBuilder {
        
        /**
         * Name of the term to be built.
         */
        private String name;
        
        /**
         * List of terms belonging to the condition to be built.
         */
        private List<ImmutableTerm<?>> terms;
        
        /**
         * Constructor.
         */
        protected Builder() {
            this.terms = new ArrayList<ImmutableTerm<?>>();
        }
        
        /**
         * {@inheritDoc}
         */
        public final ImmutableConditionBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final ImmutableConditionBuilder addTerm(ImmutableTerm<?> term) {
            this.terms.add(term);
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public final ImmutableConditionBuilder addTerm(List<ImmutableTerm<?>> terms) {
            this.terms.addAll(terms);
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder copy(ImmutableCondition condition) {
            this.setName(condition.getName());
            this.terms.addAll(condition.getTerms());
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder apply(Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution) {
            List<ImmutableTerm<?>> newTerms = new ArrayList<ImmutableTerm<?>>(terms.size());
            for (ImmutableTerm<?> oldTerm : terms) {
                ImmutableTerm<?> newTerm = oldTerm.createCopyBuilder()
                        .apply(substitution)
                        .build();
                newTerms.add(newTerm);
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final ImmutableCondition build() {
            return new SimpleCondition(this);
        }

    }
}
