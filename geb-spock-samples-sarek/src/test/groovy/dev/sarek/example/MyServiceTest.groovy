package dev.sarek.example

import dev.sarek.agent.mock.MockFactory
import spock.lang.Specification

import static net.bytebuddy.matcher.ElementMatchers.named

class MyServiceTest extends Specification {
  def "do not mock static utility method"() {
    given:
    def service = new MyService()

    expect:
    service.greet("world") == "h3!!0 w0r!d"
    service.greet("Eve") == "h3!!0 3v3"
    service.greet("Adam") == "h3!!0 adam"
  }

  def "mock static utility method"() {
    given:
    def service = new MyService()

    and:
    def results = ["one", "two", "three"]
    def count = 0
    def mockFactory = MockFactory.forClass(Util)
      .mockStatic(
        named("toGeeky"),
        { method, args -> false },
        { method, args, proceedMode, returnValue, throwable -> results[count++] }
      )
      .build()

    expect:
    service.greet("world") == "one"
    service.greet("Eve") == "two"
    service.greet("Adam") == "three"

    cleanup:
    mockFactory.close()
  }
}
