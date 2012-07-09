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

import org.gerryai.htn.domain.Domain;
import org.gerryai.htn.pddl.PDDLService;
import org.gerryai.htn.pddl.ParserException;

import pddl4j.Parser;

/**
 * Implementation of a PDDL parsing service that uses PDDL4J to do the parsing.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class PDDL4JService implements PDDLService {

	/**
	 * The PDDL4J parser object.
	 */
	private Parser parser;
	
	/**
	 * The converter to use to convert the domain into a Gerry AI class.
	 */
	private DomainConverter<pddl4j.Domain, Domain> converter;
	
	/**
	 * Constructor.
	 */
	public PDDL4JService() {
		parser = new Parser();
	}
	
	@Override
	public final Domain loadDomain(File file) throws FileNotFoundException, ParserException {
		
		PDDL4JDomainLoader loader = new PDDL4JDomainLoader(parser, file);
		
		pddl4j.Domain pddl4jDomain = loader.load();
		
		return converter.convert(pddl4jDomain);
	}

}
