package de.scrum_master.stackoverflow.q63002164

import spock.lang.Specification
import spock.lang.Unroll

class InputFileBasedParametrisedTest extends Specification {
  @Unroll
  def "verify #inputLine"() {
    expect:
    inputLine.contains("et") || true

    where:
//    inputLine << ["weather", "whether", "getters & setters"]
    inputLine << new File("src/test/resources/test.txt").readLines()
  }
}
