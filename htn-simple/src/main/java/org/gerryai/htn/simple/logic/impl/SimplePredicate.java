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
import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableTermBuilder;
import org.gerryai.htn.simple.logic.ImmutableVariable;
import org.gerryai.htn.simple.tasknetwork.ImmutableTask;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimplePredicate extends Predicate implements ImmutableCondition<SimplePredicate> {

	/**
	 * List of terms belonging to this predicate.
	 */
	private List<ImmutableTerm<?>> terms;
	
	/**
	 * Converter to help build the underlying AIMA objects.
	 */
	private static AIMAConverter<ImmutableTerm<?>, ImmutableVariable<?>, ImmutableTask> converter;
	
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
	public SimplePredicate(String predicateName, List<ImmutableTerm<?>> terms) {
		super(predicateName, converter.convert(terms));
		this.terms = terms;
	}

    /**
     * {@inheritDoc}
     */
    public final ImmutableTermBuilder<SimplePredicate> createCopyBuilder() {
        return new Builder()
            .copy(this);
    }
    
    /**
     * Builder class for SimplePredicate.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements ImmutableTermBuilder<SimplePredicate> {
        
        /**
         * Name of the term to be built.
         */
        private String name;
        
        /**
         * List of terms belonging to the predicate to be built.
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
        public final Builder copy(SimplePredicate term) {
            this.name = term.getName();
            for (ImmutableTerm<?> subTerm : term.terms) {
                this.terms.add(subTerm);
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder apply(ImmutableSubstitution substitution) {
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
        public final SimplePredicate build() {
            return new SimplePredicate(this.name, this.terms);
        }
    }
}
