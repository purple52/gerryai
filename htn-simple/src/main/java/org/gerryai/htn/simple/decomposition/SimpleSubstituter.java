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

import org.gerryai.htn.simple.constraint.impl.SimpleAfterConstraint;
import org.gerryai.htn.simple.constraint.impl.SimpleBeforeConstraint;
import org.gerryai.htn.simple.constraint.impl.SimpleBetweenConstraint;
import org.gerryai.htn.simple.constraint.impl.SimplePrecedenceConstraint;
import org.gerryai.htn.simple.tasknetwork.impl.SimpleTask;
import org.gerryai.logic.Constant;
import org.gerryai.logic.Term;
import org.gerryai.logic.Variable;

/**
 * Interface for a class that can apply substitutions to objects from the simple HTN implementation.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface SimpleSubstituter<T extends Term, V extends Variable, C extends Constant> extends Substituter {

	T apply(V variable);

	T apply(C constant);
	
	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	SimpleBeforeConstraint apply(SimpleBeforeConstraint constraint);
	
	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	SimpleAfterConstraint apply(SimpleAfterConstraint constraint);
	
	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	SimpleBetweenConstraint apply(SimpleBetweenConstraint constraint);

	/**
	 * Use this substituter to apply a substitution to the given constraint.
	 * @param constraint the constraint to apply the substitution to
	 * @return a copy of the constraint with the substitution applied
	 */
	SimplePrecedenceConstraint apply(SimplePrecedenceConstraint constraint);
	
	/**
	 * Use this substituter to apply a substitution to the given task.
	 * @param task the task to apply the substitution to
	 * @return a copy of the task with the substitution applied
	 */
	SimpleTask apply(SimpleTask task);
	
}
