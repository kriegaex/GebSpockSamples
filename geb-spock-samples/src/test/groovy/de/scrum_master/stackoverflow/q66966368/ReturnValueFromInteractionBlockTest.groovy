package de.scrum_master.stackoverflow.q66966368

import spock.lang.Specification

class ReturnValueFromInteractionBlockTest extends Specification {
  def 'Test some functionality' () {
    given:
    UnderTest underTest = Spy()
    when:
    underTest.doSomething("test")
    then:
    1 * underTest.getPipelineMock("### test ###")
  }

  static class UnderTest {
    void doSomething(String input) {
      getPipelineMock("### $input ###")
    }

    void getPipelineMock(String argument) {
      println "getPipelineMock -> $argument"
    }
  }
}
