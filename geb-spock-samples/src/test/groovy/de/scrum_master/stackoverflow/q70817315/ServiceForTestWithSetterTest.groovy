package de.scrum_master.stackoverflow.q70817315

import spock.lang.Specification

class ServiceForTestWithSetterTest extends Specification {
  ServiceForTestWithSetter serviceForTest

  void setup() {
    SomeService someServiceMock = Mock(SomeService)
    someServiceMock.generateString("TEST") >> "TEST"
    serviceForTest = new ServiceForTestWithSetter(someServiceMock)
  }

  def "Test for return current value"() {
    when:
    def methodForTest = serviceForTest.methodForTest("TEST")

    then:
    methodForTest == "TEST"
  }
}
