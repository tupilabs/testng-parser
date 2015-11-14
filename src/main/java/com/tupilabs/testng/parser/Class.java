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

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents the <class> tag. This tag is child of the <test> tag.
 * @since 0.1
 */
public class Class implements Serializable {

	private static final long serialVersionUID = 882304891611257192L;
	/**
	 * The name attribute.
	 */
	private String name;
	/**
	 * The list of <test-method> tags.
	 */
	private Set<TestMethod> testMethods;
	/**
	 * Default constructor. Initializes the list of <test-method> tags.
	 */
	public Class() {
		super();
		this.testMethods = Collections.synchronizedSet(new LinkedHashSet<TestMethod>());
	}
	/**
	 * Retrieves the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Retrieves the list of <test-method> tags.
	 * @return the testMethods
	 */
	public Set<TestMethod> getTestMethods() {
		return testMethods;
	}
	/**
	 * Adds a test method into the list of <test-method> tags.
	 * @param testMethod the test method.
	 * @return true if added sucessfully, otherwise false.
	 */
	public boolean addTestMethod(TestMethod testMethod) {
	    for (TestMethod method : this.testMethods) {
	        if (method.equals(testMethod)) {
	            if ((Statuses.get(testMethod.getStatus()) == Statuses.FAIL) || (Statuses.get(testMethod.getStatus()) == Statuses.SKIP)) {
	                method.setStatus(testMethod.getStatus());
	            }
	        }
	    }
		return this.testMethods.add(testMethod);
	}
	/**
	 * Removes a test method from the list of <test-method> tags.
	 * @param testMethod the test method.
	 * @return true if removed sucessfully, otherwise false.
	 */
	public boolean removeTestMethod(TestMethod testMethod) {
		return this.testMethods.remove(testMethod);
	}
}
