package de.scrum_master.testing

import spock.lang.Specification
import spock.util.Exceptions

class ExceptionCauseTest extends Specification {
  def "Exception cause demo"() {
    given:
    def exception = new RuntimeException("outer", new Exception("middle", new IllegalArgumentException("inner")))

    when:
    def causeChain = Exceptions.getCauseChain(exception)
    def rootCause = Exceptions.getRootCause(exception)
    println causeChain
    println rootCause
    then:
    causeChain.size() == 3
    rootCause.message == "inner"
  }
}
