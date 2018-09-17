package de.scrum_master.stackoverflow

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.slf4j.Logger
import spock.lang.Specification

@Slf4j
class LombokSlf4jLogTest extends Specification {
  def logger = Spy(new LoggerDelegate(originalLogger: log))

  @Rule
  ReplaceSlf4jLogger replaceSlf4jLogger = new ReplaceSlf4jLogger(Clazz, logger)

  def "warning is logged"() {
    when: "when calling the method"
    new Clazz().method()

    then: "a warning is logged"
    1 * logger.warn(*_)
    true
  }

  static class LoggerDelegate {
    @Delegate Logger originalLogger
  }
}
