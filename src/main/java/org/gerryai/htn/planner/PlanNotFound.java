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
package org.gerryai.htn.planner;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class PlanNotFound extends Exception {
	
    public PlanNotFound() {
        super();
    }
    
    public PlanNotFound(String message) {
        super(message);
    }

    public PlanNotFound(String message, Throwable throwable) {
        super(message, throwable);
    }
    
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 8224738161084678509L;

}
