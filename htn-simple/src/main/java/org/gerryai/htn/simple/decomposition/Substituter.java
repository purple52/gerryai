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
package org.gerryai.htn.simple.decomposition;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.simple.constraint.SubstitutableConstraint;
import org.gerryai.htn.simple.logic.SubstitutableCondition;
import org.gerryai.htn.simple.logic.SubstitutableConstant;
import org.gerryai.htn.simple.logic.SubstitutableTerm;
import org.gerryai.htn.simple.logic.SubstitutableVariable;
import org.gerryai.htn.simple.tasknetwork.SubstitutableTask;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;

/**
 * Interface for a class that can apply substitutions to objects from the simple HTN implementation.
 * @param <T> type of term used by this substituter
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface Substituter<T extends SubstitutableTerm> {

	/**
	 * Use this substituter to apply a substitution of the given variable.
	 * @param variable the variable
	 * @return a copy of the variable with the substitution applied
	 */
	SubstitutableTerm apply(SubstitutableVariable variable);

	/**
	 * Use this substituter to apply a substitution of the given constant.
	 * @param constant the constant
	 * @return a copy of the constant with the substitution applied
	 */
	SubstitutableTerm apply(SubstitutableConstant constant);

	/**
	 * Use this substituter to apply a substitution of the given term.
	 * @param term the term
	 * @return a copy of the term with the substitution applied
	 */
	SubstitutableTerm apply(SubstitutableTerm term);
	
	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	SubstitutableConstraint<T> apply(SubstitutableConstraint<T> constraint);
	
	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	AfterConstraint<Term, Task<Term>, SubstitutableCondition>
			apply(AfterConstraint<Term, Task<Term>, SubstitutableCondition> constraint);
	
	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	BetweenConstraint<Term, Task<Term>, SubstitutableCondition>
			apply(BetweenConstraint<Term, Task<Term>, SubstitutableCondition> constraint);

	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	PrecedenceConstraint<Term, Task<Term>> apply(PrecedenceConstraint<Term, Task<Term>> constraint);
	
	/**
	 * Use this substituter to apply a substitution to the given task.
	 * @param task the task to apply the substitution to
	 * @return a copy of the task with the substitution applied
	 */
	SubstitutableTask<T> apply(SubstitutableTask<T> task);
	
}
