# testng-parser: A simple TestNG parser

TestNG Parser project with a simple TestNG parser, used by other projects such 
as Jenkins plug-ins.

## Example

    ClassLoader cl = TestTestNGParser.class.getClassLoader();
    URL url = cl.getResource("com/tupilabs/testng/parser/testng-results.xml");
    File file = new File(url.getFile());

    Suite suite = null;
    try {
        suite = this.parser.parse(file);
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

[![Build Status](https://buildhive.cloudbees.com/job/tupilabs/job/testng-parser/badge/icon)](https://buildhive.cloudbees.com/job/tupilabs/job/testng-parser/)