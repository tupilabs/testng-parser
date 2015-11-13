/* 
 * The MIT License
 * 
 * Copyright (c) 2010 Bruno P. Kinoshita <http://www.kinoshita.eti.br>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tupilabs.testng.parser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

/**
 * @since 0.1
 */
public class TestTestNGParser extends TestCase {

	/**
	 * The TestNG parser.
	 */
	private TestNGParser parser;

	/**
	 * Initializes the TestNG parser.
	 */
	public void setUp() {
		this.parser = new TestNGParser();
	}

	public void testTestNGParser() {
		ClassLoader cl = TestTestNGParser.class.getClassLoader();
		URL url = cl.getResource("com/tupilabs/testng/parser/testng-results.xml");
		File file = new File(url.getFile());

		Suite suite = null;
		try {
			suite = this.parser.parse(file);
		} catch (ParserException e) {
			fail("Failed to parse testng file '" + file + "': " + e.getMessage());
		}

		assertNotNull(suite);

		assertTrue(suite.getName().equals("Command line suite"));
		assertTrue(suite.getDurationMs().equals("0"));
		assertTrue(suite.getStartedAt().equals("2010-11-17T13:31:41Z"));
		assertTrue(suite.getFinishedAt().equals("2010-11-17T13:31:41Z"));

		List<Test> tests = suite.getTests();
		assertEquals(tests.size(), 1);

		Test test = tests.get(0);
		assertTrue(test.getDurationMs().equals("0"));
		assertTrue(test.getStartedAt().equals("2010-11-17T13:31:41Z"));
		assertTrue(test.getFinishedAt().equals("2010-11-17T13:31:41Z"));
		assertTrue(test.getName().equals("Command line test"));

		List<Class> classes = test.getClasses();
		assertTrue(classes.size() == 1);

		Class clazz = classes.get(0);
		assertNotNull(clazz);

		assertTrue(clazz.getName().equals("br.eti.kinoshita.Test1"));

		Set<TestMethod> testMethods = clazz.getTestMethods();

		assertTrue(testMethods.size() == 1);

		TestMethod testMethod = testMethods.iterator().next();

		assertNotNull(testMethod);

		assertTrue(testMethod.getStatus().equals("PASS"));
		assertTrue(testMethod.getSignature().equals("testVoid()"));
		assertTrue(testMethod.getName().equals("testVoid"));
		assertTrue(testMethod.getDurationMs().equals("0"));
		assertTrue(testMethod.getStartedAt().equals("2010-11-17T13:31:41Z"));
		assertTrue(testMethod.getFinishedAt().equals("2010-11-17T13:31:41Z"));

	}

	public void testInvalidTestNGFile() {
		ClassLoader cl = TestTestNGParser.class.getClassLoader();
		URL url = cl
				.getResource("com/tupilabs/testng/parser/testng-invalid-results.xml");
		File file = new File(url.getFile());

		try {
			this.parser.parse(file);
		} catch (Throwable t) {
			assertNotNull(t);
		}

	}

	private Suite parseResourceSuite(String name) {
		File file = new File(TestTestNGParser.class.getResource(name).getFile());
		Suite suite = null;
		try {
			suite = this.parser.parse(file);
		} catch (ParserException e) {
			fail("Failed to parse testng file '" + file + "': " + e.getMessage());
		}
		return suite;
	}

	public void testMethodIterationOrder() {

		Suite suite = parseResourceSuite("testng-results-ordered.xml");

		String last = null;
		for (TestMethod method : suite.getTests().get(0).getClasses().get(0).getTestMethods()) {
			String testName = method.getName();
			if (last != null) {
				assertTrue("test not in correct order:" + testName,
						last.compareTo(method.getName()) < 0);
			}
			last = testName;
		}
		assertNotNull("did not find any testMethods..", last);
	}
}
