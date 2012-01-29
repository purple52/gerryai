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
package org.gerryai.htn.simple.domain.impl;

import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Effect;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.domain.DomainBuilder;
import org.gerryai.htn.simple.domain.OperatorBuilder;
import org.gerryai.htn.simple.domain.DomainBuilderFactory;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class SimpleDomainBuilderFactory implements
		DomainBuilderFactory<Operator, Method, Constraint, Effect> {

	/**
	 * {@inheritDoc}
	 */
	public final DomainBuilder<Operator, Method> createDomainBuilder() {
		return new SimpleDomainBuilder();	
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final OperatorBuilder<Constraint, Effect> createOperatorBuilder() {
		return new SimpleOperatorBuilder();	
	}

}