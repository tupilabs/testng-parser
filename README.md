# testng-parser: A simple TestNG parser

TestNG Parser project with a simple TestNG parser, used by other projects such 
as Jenkins plug-ins.

## Build status

[![Build Status](https://buildhive.cloudbees.com/job/tupilabs/job/testng-parser/badge/icon)](https://buildhive.cloudbees.com/job/tupilabs/job/testng-parser/)

## Usage example

Below an example of how to use this parser.

    ClassLoader cl = TestTestNGParser.class.getClassLoader();
    URL url = cl.getResource("com/tupilabs/testng/parser/testng-results.xml");
    File file = new File(url.getFile());

    List<Suite> suites = null;
    try {
        suites = this.parser.parse(file);
    } catch (ParserException e) {
        fail("Failed to parse testng file '" + file + "': " + e.getMessage());
    }
    
    assertTrue(suite.getName().equals("Command line suite"));
    
    List<Test> tests = suite.getTests();
    assertEquals(tests.size(), 1);
    
    Class clazz = classes.get(0);
    assertNotNull(clazz);

    assertTrue(clazz.getName().equals("br.eti.kinoshita.Test1"));

    List<TestMethod> testMethods = clazz.getTestMethods();
    
    TestMethod testMethod = testMethods.get(0);
    assertTrue(testMethod.getStatus().equals("PASS"));
    
## Licensing

This project is licensed under MIT License.

## Contributing

Fork it, send pull requests or drop us a message :-)
