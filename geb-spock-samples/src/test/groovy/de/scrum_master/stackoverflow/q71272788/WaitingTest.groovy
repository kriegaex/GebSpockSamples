package de.scrum_master.stackoverflow.q71272788

import spock.lang.Specification

import static java.lang.System.currentTimeMillis

class WaitingTest extends Specification {
  static final int SLEEP_MILLIS = 1

  def "verify slow multiplication"() {
    given:
    Calculator calculator = Stub() {
      multiply(_, _) >> {
        sleep SLEEP_MILLIS
        42
      }
    }
    def myClass = new MyClass(calculator)
    def startTime = currentTimeMillis()

    expect:
    myClass.calculate() == 42
    currentTimeMillis() - startTime >= SLEEP_MILLIS
  }
}
