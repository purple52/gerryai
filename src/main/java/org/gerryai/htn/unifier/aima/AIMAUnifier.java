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
package org.gerryai.htn.unifier.aima;

import java.util.Map;

import org.gerryai.htn.domain.Method;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.unifier.TermSubstitution;
import org.gerryai.logic.unifier.Unifier;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class AIMAUnifier implements Unifier {
	
	/**
	 * AIMA Unifier object to do the underlying expression unification
	 */
	aima.core.logic.fol.Unifier unifier;
	
	/**
	 * Converter to convert between our classes and the AIMA FOL classes
	 */
	AIMAConverter converter;

	/**
	 * {@inheritDoc}
	 */
	public TermSubstitution unify(Task task, Method method) {
		
		Predicate taskPredicate = converter.convert(task);
		Predicate methodTaskPredicate = converter.convert(method.getTask());

		Map<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term> map = unifier.unify(taskPredicate, methodTaskPredicate);
		return converter.convert(map);
	}

}
