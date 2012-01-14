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

import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;
import org.gerryai.logic.unifier.TermSubstitution;

import aima.core.logic.fol.parsing.ast.Predicate;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public interface AIMAConverter {

	Predicate convert(Task task);
	
	aima.core.logic.fol.parsing.ast.Term convert(Term term);
	
	Term convert(aima.core.logic.fol.parsing.ast.Term term);
	
	aima.core.logic.fol.parsing.ast.Variable convert(Variable variable);
	
	Variable convert(aima.core.logic.fol.parsing.ast.Variable variable);
	
	TermSubstitution convert(Map<aima.core.logic.fol.parsing.ast.Variable, aima.core.logic.fol.parsing.ast.Term> map);
}
