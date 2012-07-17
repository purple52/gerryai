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
package org.gerryai.htn.simple.integration;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.planner.PlanNotFound;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilder;
import org.gerryai.htn.tasknetwork.InvalidConstraint;

/**
 * End-to-end integration test of simple implementation using the JSHOP blocks example.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class BlocksIT extends BaseIT {

	@Override
	final Domain createDomain() throws PlanNotFound, InvalidConstraint {
		
		ImmutableDomainBuilder domainBuilder = getPlanningFactory().getDomainBuilderFactory().createDomainBuilder();
		domainBuilder = addOperators(domainBuilder);
		domainBuilder = addMethods(domainBuilder);
		
		return domainBuilder
				.build();
	}
	
	/**
	 * Add the operators to the domain being built.
	 * @param builder the domain so far
	 * @return a builder with the operators added
	 */
	private ImmutableDomainBuilder addOperators(ImmutableDomainBuilder builder) {
		return builder;
	}

	/**
	 * Add the methods to the domain being built.
	 * @param builder the domain so far
	 * @return a builder with the methods added
	 */
	private ImmutableDomainBuilder addMethods(ImmutableDomainBuilder builder) {
		return builder;
	}
}
