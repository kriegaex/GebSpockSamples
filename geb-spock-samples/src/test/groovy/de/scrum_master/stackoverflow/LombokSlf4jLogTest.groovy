package de.scrum_master.stackoverflow

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.slf4j.Logger
import org.spockframework.util.VersionNumber
import spock.lang.IgnoreIf
import spock.lang.Specification

/**
 * See https://stackoverflow.com/a/49870470/1082681
 */
@IgnoreIf({ VersionNumber.parse(jvm.javaSpecificationVersion).major > 11 }) // TODO: find JVM switches to permit reflection
@Slf4j
class LombokSlf4jLogTest extends Specification {
  def logger = Spy(new LoggerDelegate(originalLogger: log))
  // Or if you don't need a fully functional logger
  // def logger = Mock(Logger)

  @Rule
  ReplaceSlf4jLogger replaceSlf4jLogger = new ReplaceSlf4jLogger(LombokLoggerUser, logger)

  def "Lombok-generated logger is called"() {
    when: "when calling the method"
    new LombokLoggerUser().method()

    then: "a warning is logged"
    2 * logger.warn(*_)
  }

  static class LoggerDelegate {
    @Delegate Logger originalLogger
  }
}
