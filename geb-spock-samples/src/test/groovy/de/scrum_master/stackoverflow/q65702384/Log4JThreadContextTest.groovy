package de.scrum_master.stackoverflow.q65702384

import org.apache.logging.log4j.ThreadContext
import spock.lang.Specification

class Log4JThreadContextTest extends Specification {
  def setupSpec() {
    ThreadContext.put("userId", "sriram")
  }

  def test() {
    expect:
    ThreadContext.get("userId") == "sriram"
  }
}
