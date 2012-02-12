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
package org.gerryai.htn.simple.constraint;

import org.gerryai.htn.simple.decomposition.Substitutable;
import org.gerryai.htn.simple.decomposition.Substituter;
import org.gerryai.htn.simple.logic.SubstitutableTerm;

/**
 * Extended constraint interface that supports being validated.
 * The constraint needs to identify what class of validator it needs to use.
 * @param <T> type of logical term used by this constraint
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface SubstitutableConstraint<T extends SubstitutableTerm> extends Substitutable<T> {

	/**
	 * {@inheritDoc}
	 */
	SubstitutableConstraint<T> apply(Substituter<T> substituter);
	
}
