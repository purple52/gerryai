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
package org.gerryai.htn.pddl.pddl4j;

import java.io.File;
import java.io.FileNotFoundException;

import org.gerryai.htn.pddl.ParserException;

import pddl4j.Domain;
import pddl4j.ErrorManager;
import pddl4j.ErrorManager.Message;
import pddl4j.Parser;

/**
 * Domain loader using the PDDL4J library.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class PDDL4JDomainLoader {
	
	/**
	 * The file to be pasrsed.
	 */
	private File file;
	
	/**
	 * The PDDL4J parser to use.
	 */
	private Parser parser;
	
	/**
	 * Constructor.
	 * @param parser the parser to use
	 * @param file the file to parse
	 */
	public PDDL4JDomainLoader(Parser parser, File file) {
		this.file = file;
		this.parser = parser;
	}
	
	/**
	 * Load the domain from the file and return a PDDL4J domain object.
	 * @return the domain
	 * @throws FileNotFoundException if the file could not be opened
	 * @throws ParserException if the file could not be parsed
	 */
	public final Domain load() throws FileNotFoundException, ParserException {
		
		Domain domain = parser.parse(file);
		
		// Gets the error manager of the pddl parser
		ErrorManager mgr = parser.getErrorManager();
		// If the parser produces errors we print it and stop
		if (mgr.contains(Message.ALL)) {
			throw new ParserException();
		}
		
		return domain;
	}
}
