package de.scrum_master.stackoverflow.q62543765

import spock.lang.Specification

class MyClassTest extends Specification {

  def "do not use mock"() {
    when:
    new MyClass([firstArg: new OtherClass(), secondArg: "foo"])
    then:
    noExceptionThrown()

    when:
    new MyClass([firstArg: null, secondArg: "foo"])
    then:
    thrown AssertionError

    when:
    new MyClass([secondArg: "foo"])
    then:
    thrown AssertionError
  }

  def "use mock"() {
    given:
    def otherClass = Mock(OtherClass) {
      asBoolean() >> true
    }
    when:
    new MyClass([firstArg: otherClass, secondArg: "foo"])
    then:
    noExceptionThrown()
  }
}
