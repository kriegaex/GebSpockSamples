package de.scrum_master.stackoverflow

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.slf4j.Logger
import spock.lang.Specification

/**
 * See https://stackoverflow.com/a/49870470/1082681
 */
@Slf4j
class LombokSlf4jLogTest extends Specification {
  def logger = Spy(new LoggerDelegate(originalLogger: log))

  @Rule
  ReplaceSlf4jLogger replaceSlf4jLogger = new ReplaceSlf4jLogger(Clazz, logger)

  def "warning is logged"() {
    when: "when calling the method"
    new Clazz().method()

    then: "a warning is logged"
    2 * logger.warn(*_)
  }

  static class LoggerDelegate {
    @Delegate Logger originalLogger
  }
}
