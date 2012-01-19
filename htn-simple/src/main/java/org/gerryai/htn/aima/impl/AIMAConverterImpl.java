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
package org.gerryai.htn.aima.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gerryai.htn.aima.AIMAConverter;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.gerryai.logic.VariableImpl;
import org.gerryai.logic.unification.Unifier;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 * 
 */
public class AIMAConverterImpl implements AIMAConverter {

	/**
	 * {@inheritDoc}
	 */
	public final Predicate convert(Task task) {

		String name = task.getName();
		List<Term> taskTerms = task.getArguments();

		List<aima.core.logic.fol.parsing.ast.Term> terms = new ArrayList<aima.core.logic.fol.parsing.ast.Term>();
		Iterator<Term> taskTermIterator = taskTerms.iterator();
		while (taskTermIterator.hasNext()) {
			Term taskTerm = taskTermIterator.next();
			aima.core.logic.fol.parsing.ast.Term term = convert(taskTerm);
			terms.add(term);
		}
		return new Predicate(name, terms);
	}

	/**
	 * {@inheritDoc}
	 */
	public final aima.core.logic.fol.parsing.ast.Term convert(Term term) {
		aima.core.logic.fol.parsing.ast.Constant constant = new aima.core.logic.fol.parsing.ast.Constant(
				term.getName());
		// TODO Implement
		return constant;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Unifier convert(
			Map<aima.core.logic.fol.parsing.ast.Variable,
			aima.core.logic.fol.parsing.ast.Term> map) {
		Map<Variable, Term> substitutionMap = new HashMap<Variable, Term>();

		Iterator<aima.core.logic.fol.parsing.ast.Variable> variableIterator = map
				.keySet().iterator();
		while (variableIterator.hasNext()) {
			aima.core.logic.fol.parsing.ast.Variable variable = variableIterator
					.next();
			// TODO: Avoid cast
			Variable key = convert(variable);
			Term value = convert(map.get(variable));
			substitutionMap.put(key, value);
		}

		Unifier substitution = new Unifier();
		substitution.setMap(substitutionMap);
		return substitution;
	}

	/**
	 * {@inheritDoc}
	 */
	public final aima.core.logic.fol.parsing.ast.Variable convert(
			Variable variable) {
		return new aima.core.logic.fol.parsing.ast.Variable(variable.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	public final Variable convert(
			aima.core.logic.fol.parsing.ast.Variable aimaVariable) {
		VariableImpl variable = new VariableImpl();
		variable.setName(aimaVariable.getSymbolicName());
		return variable;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Term convert(aima.core.logic.fol.parsing.ast.Term term) {
		// TODO Auto-generated method stub
		return null;
	}

}
