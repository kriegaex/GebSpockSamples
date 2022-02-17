package de.scrum_master.stackoverflow.q57172322

import spock.lang.Specification
import spock.lang.Unroll

class ServiceDependenciesThrowingErrorsTest extends Specification {
  @Unroll
  def 'handle error in service #serviceName'() {
    given:
    def serviceUnderTest = new Service(
      dependency1: Mock(Supplier) { get() >> { closure1() } },
      dependency2: Mock(Supplier) { get() >> { closure2() } },
      dependency3: Mock(Supplier) { get() >> { closure3() } }
    )

    expect:
    serviceUnderTest.method() == 'default value'

    where:
    serviceName | closure1                            | closure2                            | closure3
    "A"         | { throw new Exception('closure1') } | { null }                            | { null }
    "B"         | { null }                            | { throw new Exception('closure2') } | { null }
    "C"         | { null }                            | { null }                            | { throw new Exception('closure3') }
  }

  static class Service {
    def dependency1
    def dependency2
    def dependency3

    def method() {
      try {
        def foo = dependency1.get()
        def bar = dependency2.get()
        def baz = dependency3.get()
        return "$foo $bar $baz"
      } catch (Exception e) {
        println e
        return 'default value'
      }
    }
  }

  static class Supplier {
    def get() {
      "OK"
    }
  }
}
