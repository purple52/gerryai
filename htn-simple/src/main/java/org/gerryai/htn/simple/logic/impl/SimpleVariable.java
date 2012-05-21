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

import org.gerryai.htn.simple.decomposition.ImmutableSubstitution;
import org.gerryai.htn.simple.logic.ImmutableTerm;
import org.gerryai.htn.simple.logic.ImmutableTermBuilder;
import org.gerryai.htn.simple.logic.ImmutableVariable;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleVariable extends aima.core.logic.fol.parsing.ast.Variable
		implements ImmutableVariable<SimpleVariable> {

	/**
	 * @param name name of the variable
	 */
	public SimpleVariable(String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.getSymbolicName();
	}
	
    /**
     * {@inheritDoc}
     */
    public final ImmutableTermBuilder<SimpleVariable> createCopyBuilder() {
        return new Builder()
            .copy(this);
    }
    
    /**
     * Builder class for SimpleVariable.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static class Builder implements ImmutableTermBuilder<SimpleVariable> {
        
        /**
         * Name of the term to be built.
         */
        private String name;
        
        /**
         * {@inheritDoc}
         */
        public final Builder copy(SimpleVariable term) {
            this.name = term.getName();
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final Builder apply(ImmutableSubstitution substitution) {
            for (ImmutableTerm<?> oldTerm : substitution.getMap().keySet()) {
                if (oldTerm.getName().equals(this.name)) {
                    this.name = substitution.getMap().get(oldTerm).getName();
                }
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public final SimpleVariable build() {
            return new SimpleVariable(this.name);
        }
    }
}
