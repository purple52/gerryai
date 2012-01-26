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
package org.gerryai.htn.simple.tasknetwork;

/**
 * @author David Edwards <david@more.fool.me.uk>
 *
 */
public class InvalidConstraint extends Exception {

	/**
	 * Default constructor.
	 */
	public InvalidConstraint() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with a message.
	 * @param message the message
	 */
	public InvalidConstraint(String message) {
		super(message);
	}

	/**
	 * Constructor with a message and wrapped exception.
	 * @param message the message
	 * @param cause the exception
	 */
	public InvalidConstraint(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058425134552332094L;
}
