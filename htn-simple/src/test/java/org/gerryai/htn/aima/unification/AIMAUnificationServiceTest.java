/**
 *  Gerry AI - Open framework for automated planning algorithms
 *  Copyright (C) 2012  David Edwards
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the SubstitutableTerms of the GNU Affero General Public License as
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
package org.gerryai.htn.aima.unification;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.gerryai.htn.aima.AIMAConverter;
import org.gerryai.htn.constraint.Constraint;
import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.simple.domain.DomainHelper;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.tasknetwork.TaskNetworkBuilderFactory;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.htn.tasknetwork.TaskNetwork;
import org.gerryai.logic.Variable;
import org.gerryai.logic.unification.Substitution;
import org.junit.Test;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class AIMAUnificationServiceTest {

	/**
	 * Test for the finUnifier() method.
	 */
	@Test
	public void testFindUnifier() {
		aima.core.logic.fol.Unifier aimaUnifier = mock(aima.core.logic.fol.Unifier.class);
		@SuppressWarnings("unchecked")
		AIMAConverter<SubstitutableTerm, Variable, Task<SubstitutableTerm>> aimaConverter
				= mock(AIMAConverter.class);
		@SuppressWarnings("unchecked")
		DomainHelper<Operator<Condition>, Method<SubstitutableTerm, Task<SubstitutableTerm>,
				TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>, Constraint<SubstitutableTerm>>,
				Constraint<SubstitutableTerm>>, SubstitutableTerm, Task<SubstitutableTerm>, TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>,
				Constraint<SubstitutableTerm>>, Constraint<SubstitutableTerm>, Condition> mockDomainHelper
					= mock(DomainHelper.class);
		@SuppressWarnings("unchecked")
		TaskNetworkBuilderFactory<SubstitutableTerm, Task<SubstitutableTerm>, TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>,
				Constraint<SubstitutableTerm>>, Constraint<SubstitutableTerm>> mockTaskNetworkBuilderFactory
					= mock(TaskNetworkBuilderFactory.class);
		AIMAUnificationService<Operator<Condition>, Method<SubstitutableTerm, Task<SubstitutableTerm>,
				TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>, Constraint<SubstitutableTerm>>, Constraint<SubstitutableTerm>>,
				SubstitutableTerm, Task<SubstitutableTerm>, TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>, Constraint<SubstitutableTerm>>,
				Constraint<SubstitutableTerm>, Condition, Variable> unificationService
					= new AIMAUnificationService<Operator<Condition>, Method<SubstitutableTerm,
				Task<SubstitutableTerm>, TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>, Constraint<SubstitutableTerm>>,
				Constraint<SubstitutableTerm>>, SubstitutableTerm, Task<SubstitutableTerm>, TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>,
				Constraint<SubstitutableTerm>>, Constraint<SubstitutableTerm>, Condition, Variable>(
					aimaUnifier, aimaConverter, mockDomainHelper, 
					mockTaskNetworkBuilderFactory);
		
		@SuppressWarnings("unchecked")
		Task<SubstitutableTerm> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<SubstitutableTerm> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		Method<SubstitutableTerm, Task<SubstitutableTerm>, TaskNetwork<SubstitutableTerm, Task<SubstitutableTerm>, Constraint<SubstitutableTerm>>, Constraint<SubstitutableTerm>> mockMethod = mock(Method.class);
		when(mockMethod.getTask()).thenReturn(mockTaskB);
		Predicate mockPredicateA = mock(Predicate.class);
		Predicate mockPredicateB = mock(Predicate.class);
		when(aimaConverter.convert(mockTaskA)).thenReturn(mockPredicateA);
		when(aimaConverter.convert(mockTaskB)).thenReturn(mockPredicateB);
		
		Map<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term> mockMap = new HashMap<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term>();
		when(aimaUnifier.unify(mockPredicateA, mockPredicateB)).thenReturn(mockMap);
		
		@SuppressWarnings("unchecked")
		Substitution<SubstitutableTerm, Variable> mockUnifier = mock(Substitution.class);
		when(aimaConverter.convert(mockMap)).thenReturn(mockUnifier);
		Substitution<SubstitutableTerm, Variable> unifier = unificationService.findUnifier(mockTaskA, mockMethod);
		assertEquals(mockUnifier,unifier);
	}

}
