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
import java.util.Map;

import org.gerryai.htn.simple.logic.ImmutableCondition;
import org.gerryai.htn.simple.logic.ImmutableConstant;
import org.gerryai.htn.simple.logic.ImmutableLogicFactory;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableVariable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleLogicFactory implements ImmutableLogicFactory {

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableVariable<?> createVariable(String name) {
		return new SimpleVariable(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableConstant<?> createConstant(String name) {
		return new SimpleConstant(name);
	}
    
    /**
     * {@inheritDoc}
     */
    public final ImmutableCondition createCondition(String name, ImmutableTerm<?> term) {
        List<ImmutableTerm<?>> terms = new ArrayList<ImmutableTerm<?>>(1);
        terms.add(term);
        return createCondition(name, terms);
    }
    
	/**
	 * {@inheritDoc}
	 */
	public final ImmutableCondition createCondition(String name, List<ImmutableTerm<?>> terms) {
		return new SimpleCondition.Builder()
		        .setName(name)
		        .addTerm(terms)
		        .build();
	}
	
    /**
     * {@inheritDoc}
     */
	public final List<ImmutableTerm<?>> apply(List<ImmutableTerm<?>> oldTerms,
	        Map<ImmutableTerm<?>, ImmutableTerm<?>> substitution) {
        List<ImmutableTerm<?>> updatedTerms = new ArrayList<ImmutableTerm<?>>();        
        for (ImmutableTerm<?> oldTerm : oldTerms) {
            if (substitution.containsKey(oldTerm)) {
                updatedTerms.add(substitution.get(oldTerm));
            } else {
                updatedTerms.add(oldTerm);
            }
        }
        
        return updatedTerms;
	}
}
