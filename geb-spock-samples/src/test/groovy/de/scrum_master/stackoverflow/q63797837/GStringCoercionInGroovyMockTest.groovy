package de.scrum_master.stackoverflow.q63797837

import spock.lang.Issue
import spock.lang.Specification

@Issue("https://github.com/spockframework/spock/issues/1216")
class GStringCoercionInGroovyMockTest extends Specification {
  def 'pass String to groovyMock mocked method taking a String argument'() {
    given:
    TestClass testClass = new TestClass()
    SomeClass someClass = GroovyMock() // does not work
    // SomeClass someClass = Mock() // test works if used instead of the previous line
    testClass.someClass = someClass

    when:
    testClass.callMethod()

    then:
    0 * someClass.someMethod(_)
    thrown IllegalArgumentException
    // After #1216 is fixed, this passes without exception:
    // 1* someClass.someMethod(_)
  }

  static class SomeClass {
    String someMethod(String arg) {
      return arg
    }
  }

  static class TestClass {
    SomeClass someClass

    String callMethod() {
      someClass.someMethod("foo ${1 + 1}")
    }
  }

}
