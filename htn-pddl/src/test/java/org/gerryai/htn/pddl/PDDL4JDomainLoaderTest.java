package org.gerryai.htn.pddl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.gerryai.htn.pddl.pddl4j.PDDL4JDomainLoader;
import org.junit.Test;

import pddl4j.Domain;
import pddl4j.Parser;

/**
 * Unit tests for PDDL4JDomainLoader class.
 * @author david
 */
public class PDDL4JDomainLoaderTest {

	/**
	 * Test loading a PDDL file.
	 * @throws FileNotFoundException only if test is broken
	 * @throws ParserException only if test if broken
	 */
	@Test
	public final void testLoad() throws FileNotFoundException, ParserException {
		
		Parser parser = new Parser();
		URL url = this.getClass().getResource("/blocksworld/blocksworld.pddl");
		File file = new File(url.getFile());
		
		PDDL4JDomainLoader domainLoader = new PDDL4JDomainLoader(parser, file);
		Domain domain = domainLoader.load();
		
		assertEquals("blocksworld", domain.getDomainName());
		
	}

}
