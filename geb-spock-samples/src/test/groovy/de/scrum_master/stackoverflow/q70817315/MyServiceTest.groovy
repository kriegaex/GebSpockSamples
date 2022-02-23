package de.scrum_master.stackoverflow.q70817315

import spock.lang.Specification

class MyServiceTest extends Specification {
  MyService myService

  void setup() {
    SomeService someServiceMock = Mock(SomeService)
    someServiceMock.generateString("TEST") >> "TEST"
    myService = new MyService(someService: someServiceMock)
//    myService = new ServiceForTest()
//    myService.someService = someServiceMock
  }

  def "Test for return current value"() {
    when:
    def methodForTest = myService.methodForTest("TEST")

    then:
    methodForTest == "TEST"
  }
}
