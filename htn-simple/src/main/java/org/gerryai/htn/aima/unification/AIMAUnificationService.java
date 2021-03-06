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
package org.gerryai.htn.aima.unification;

import java.util.Map;

import org.gerryai.htn.aima.AIMAConverter;
import org.gerryai.htn.domain.Method;
import org.gerryai.htn.simple.decomposition.UnificationService;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * Unification service that use an AIMA unifier underneath.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class AIMAUnificationService	implements UnificationService {

	/**
	 * AIMA Unifier object to do the underlying expression unification.
	 */
	private aima.core.logic.fol.Unifier unifier;

	/**
	 * Converter to convert between our classes and the AIMA FOL classes.
	 */
	private AIMAConverter converter;
	
	/**
	 * Constructor taking all required dependencies.
	 * @param unifier AIMA unifier object
	 * @param converter converted to translate between the two class schemes
	 */
	public AIMAUnificationService(aima.core.logic.fol.Unifier unifier,
			AIMAConverter converter) {
		this.unifier = unifier;
		this.converter = converter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Map<Term, Term> findUnifier(Task task, Method method) {

		Predicate taskPredicate = converter.convert(task);
		Predicate methodTaskPredicate = converter.convert(method.getTask());
		
		Map<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term> map
				= unifier.unify(taskPredicate, methodTaskPredicate);

		return converter.convert(map);
	}

}
