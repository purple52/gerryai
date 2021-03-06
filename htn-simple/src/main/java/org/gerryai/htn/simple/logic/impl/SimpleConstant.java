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

import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;

import com.google.common.base.Objects;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public final class SimpleConstant extends aima.core.logic.fol.parsing.ast.Constant
		implements Constant {

	/**
	 * Constructor.
	 * @param s name of the constant
	 */
	public SimpleConstant(String s) {
		super(s);
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
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Constant) {
            final Constant other = (Constant) obj;
            return Objects.equal(this.getName(), other.getName());
        } else {
            return false;
        }
    }
}
