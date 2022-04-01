package de.scrum_master.stackoverflow.q71602791

import spock.lang.Specification

import java.util.function.BiFunction

class BiFunctionMockTest extends Specification {
  def "mock BiFunction"() {
    given:
    def biFunction = Mock(BiFunction) {
      apply(*_) >> "dummy"
    }
    def myInterface = Mock(MyInterface) {
      myAwesomeFunc() >> Optional.of(biFunction)
    }
    def underTest = new MyInterfaceUser(myInterface: myInterface)

    expect:
    underTest.doSomething().get().apply("foo", "bar") == "dummy"
  }

  interface MyInterface {
    Optional<BiFunction<Object, Object, Object>> myAwesomeFunc()
  }

  static class MyInterfaceUser {
    MyInterface myInterface

    def doSomething() {
      myInterface.myAwesomeFunc()
    }
  }
}
