package de.scrum_master.stackoverflow.q58279620

import spock.lang.Specification
import spock.lang.Unroll

class PassingNullToOverloadedMethodTest extends Specification {
  @Unroll
  def "someMethod('#input') returns #expected"(String input, def expected) {
    when:
    def result = ClassUnderTest.someMethod(input)

    then:
    result == expected

    where:
    input | expected
    "foo" | "something"
    ""    | "something"
    null  | "nothing"
  }

  @Unroll
  def "someMethod(#input) returns #expected"(Integer input, def expected) {
    when:
    def result = ClassUnderTest.someMethod(input)

    then:
    result == expected

    where:
    input | expected
    0     | 11
    123   | 11
    // This fails, see https://github.com/spockframework/spock/issues/589#issuecomment-539323700
    //null  | -999
  }
}
