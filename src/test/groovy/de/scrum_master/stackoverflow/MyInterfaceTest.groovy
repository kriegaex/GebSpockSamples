package de.scrum_master.stackoverflow

import org.spockframework.mock.CannotInvokeRealMethodException
import spock.lang.Specification

class MyInterfaceTest extends Specification {
  def "Try to call real method on interface mock"() {
    given:
    MyInterface myInterface = Mock() {
      doSomething() >> { callRealMethod() }
    }
    when:
    myInterface.doSomething()
    then:
    thrown(CannotInvokeRealMethodException)
  }

  def "Try to call real method on interface stub"() {
    given:
    MyInterface myInterface = Stub() {
      doSomething() >> { callRealMethod() }
    }
    when:
    myInterface.doSomething()
    then:
    thrown(CannotInvokeRealMethodException)
  }

  def "Try to call real method on interface spy"() {
    given:
    MyInterface myInterface = Spy() {
      doSomething() >> { callRealMethod() }
    }
    when:
    myInterface.doSomething()
    then:
    thrown(CannotInvokeRealMethodException)
  }
}
