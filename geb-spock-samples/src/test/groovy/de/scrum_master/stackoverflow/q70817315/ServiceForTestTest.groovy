package de.scrum_master.stackoverflow.q70817315

import spock.lang.Specification

class ServiceForTestTest extends Specification {
  ServiceForTest serviceForTest

  void setup() {
    SomeService someServiceMock = Mock(SomeService)
    someServiceMock.generateString("TEST") >> "TEST"
    serviceForTest = new ServiceForTest(someService: someServiceMock)
//    serviceForTest = new ServiceForTest()
//    serviceForTest.someService = someServiceMock
  }

  def "Test for return current value"() {
    when:
    def methodForTest = serviceForTest.methodForTest("TEST")

    then:
    methodForTest == "TEST"
  }
}
