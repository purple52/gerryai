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
package org.gerryai.htn.tasknetwork;

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.domain.Operator;
import org.gerryai.htn.domain.OperatorNotFound;
import org.gerryai.htn.plan.Action;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class TaskResolverImpl implements TaskResolver {
	
	/**
	 * The domain this task resolver is working in
	 */
	private Domain domain;

	/**
	 * {@inheritDoc}
	 */
	public Action resolve(Task task) {
		
		try {
			Operator operator = domain.getOperatorByName(task.getName());
		} catch (OperatorNotFound e) {
			// TODO Handle exception properly
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

}
