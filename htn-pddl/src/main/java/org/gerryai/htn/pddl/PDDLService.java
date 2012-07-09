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
package org.gerryai.htn.pddl;

import java.io.File;
import java.io.FileNotFoundException;

import org.gerryai.htn.domain.Domain;

/**
 * Interface for a service that can parse PDDL files and return domains and problems.
 * @author David Edwards <david@more.fool.me.uk>
 */
public interface PDDLService {

	/**
	 * Generate a domain from a PDDL file.
	 * @param file the file to parse
	 * @return the domain
	 * @throws ParserException if the file could not be parsed
	 * @throws FileNotFoundException if the file could not be opened
	 */
	Domain loadDomain(File file) throws FileNotFoundException, ParserException;
}
