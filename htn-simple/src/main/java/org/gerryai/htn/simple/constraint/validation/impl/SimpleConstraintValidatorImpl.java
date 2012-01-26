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
package org.gerryai.htn.simple.constraint.validation.impl;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.impl.SimplePrecedenceConstraint;
import org.gerryai.htn.simple.constraint.validation.ConstraintValidator;
import org.gerryai.htn.simple.constraint.validation.SimpleConstraintValidator;

/**
 * Class for validating precedence constraints.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleConstraintValidatorImpl implements ConstraintValidator, SimpleConstraintValidator {

	/**
	 * Set of precedence constraints that have been added so far.
	 */
	private Set<SimplePrecedenceConstraint> precedenceConstraints;
	
	/**
	 * Default consructor.
	 */
	public SimpleConstraintValidatorImpl() {
		precedenceConstraints = new HashSet<SimplePrecedenceConstraint>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean validate(SimplePrecedenceConstraint constraint) {
		//for (SimplePrecedenceConstraint existingConstraint : precedenceConstraints) {
		//	
		//}
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void add(SimplePrecedenceConstraint constraint) {
		precedenceConstraints.add(constraint);
	}
}
