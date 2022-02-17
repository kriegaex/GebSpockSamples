package de.scrum_master.stackoverflow.q63959033

import spock.lang.Specification
import spock.lang.Unroll

class TestSpockReverseString extends Specification {
  @Unroll
  def "reversing '#inString' yields '#expectedString'"() {
    expect:
    expectedString == new StringReverse().reverseString(inString)

    where:
    inString | expectedString
    null     | null
    ""       | ""
    "abc"    | "cba"
    "aaa"    | "aaa"
    "abcd"   | "dcba"
    "asa"    | "asa"
  }

  static class StringReverse {
    String reverseString(String string) {
      string?.reverse()
    }
  }
}
