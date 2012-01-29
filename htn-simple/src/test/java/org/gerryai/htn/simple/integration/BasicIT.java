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

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.domain.OperatorBuilderFactory;
import org.gerryai.htn.simple.domain.impl.SimpleDomain;
import org.gerryai.htn.simple.domain.impl.SimpleOperatorBuilderFactory;
import org.gerryai.logic.Variable;
import org.gerryai.logic.VariableImpl;
import org.junit.Test;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class BasicIT {

	@Test
	public void test() {
		
		SimpleOperatorBuilderFactory operatorBuilderFactory = new SimpleOperatorBuilderFactory();
		
		Domain domain = new SimpleDomain();
		
		Variable variableA = new VariableImpl();
		variableA.setName("a");
		
		Operator operatorA = operatorBuilderFactory.create()
				.setName("pickup")
				.addArgument(variableA)
				.build();
		Set<Operator> operators = new HashSet<Operator>();
		operators.add(operatorA);
		domain.setOperators(operators);
	}

}
