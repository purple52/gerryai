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

import java.util.Map;

import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

import com.google.common.base.Objects;

/**
 * Simple implementation of a logical variable.
 * @author David Edwards <david@more.fool.me.uk>
 */
public final class SimpleVariable extends aima.core.logic.fol.parsing.ast.Variable
		implements Variable {

	/**
	 * @param name name of the variable
	 */
	public SimpleVariable(String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return this.getSymbolicName();
	}
	
    /**
     * {@inheritDoc}
     */
	public Term applyToCopy(Map<Term, Term> substitution) {
        if (substitution.containsKey(this)) {
            return substitution.get(this);
        } else {
            return this;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isGround() {
        return false;
    }
    
    /**
     * Builder class for SimpleVariable.
     * @author David Edwards <david@more.fool.me.uk>
     */
    public static final class Builder {
        
        /**
         * Name of the term to be built.
         */
        private String name;
        
        /**
         * {@inheritDoc}
         */
        public Builder copy(SimpleVariable term) {
            this.name = term.getName();
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public Builder apply(Map<Term, Term> substitution) {
            for (Term oldTerm : substitution.keySet()) {
                if (oldTerm.equals(this)) {
                    this.name = substitution.get(oldTerm).getName();
                }
            }
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public SimpleVariable build() {
            return new SimpleVariable(this.name);
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            final Variable other = (Variable) obj;
            return Objects.equal(this.getName(), other.getName());
        } else {
            return false;
        }
    }
}
