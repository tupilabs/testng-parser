package com.tupilabs.testng.parser.issue1;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.tupilabs.testng.parser.Suite;
import com.tupilabs.testng.parser.TestMethod;
import com.tupilabs.testng.parser.TestNGParser;
import com.tupilabs.testng.parser.TestTestNGParser;

public class TestDataProviderIssue1 {

    @Test
    public void testDataProviderIssue1() {
        final TestNGParser parser = new TestNGParser();
        ClassLoader cl = TestTestNGParser.class.getClassLoader();
        URL url = cl
                .getResource("com/tupilabs/testng/parser/issue1/testng-results.xml");
        File file = new File(url.getFile());

        List<Suite> suites = parser.parse(file);
        for (Suite suite : suites){
            System.out.println(suite.getName());
        for (com.tupilabs.testng.parser.Test test : suite.getTests()) {
            System.out.println("\t" + test.getName());
            for (com.tupilabs.testng.parser.Class clazz : test.getClasses()) {
                System.out.println("\t\t" + clazz.getName());
                for (TestMethod method : clazz.getTestMethods()) {
                    System.out.println("\t\t\t" + method.getName() + " - " + method.getStatus());
                    assertTrue(method.getStatus().equals("FAIL"));
                }
            }
        }
    }
    }

}
