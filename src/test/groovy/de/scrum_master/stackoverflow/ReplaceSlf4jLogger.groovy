package de.scrum_master.stackoverflow

import org.junit.rules.ExternalResource
import org.slf4j.Logger

import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 *  Helper to exchange loggers set by lombok with mock logger
 *
 * allows to assert log action.
 *
 * Undos change after test to keep normal logging in other tests.
 *
 * code from this  <a href="https://stackoverflow.com/a/25031713/3573038">answer</a> answer
 */
class ReplaceSlf4jLogger extends ExternalResource {
  Field logField
  Logger logger
  Logger originalLogger

  ReplaceSlf4jLogger(Class logClass, Logger logger) {
    logField = logClass.getDeclaredField("log")
    this.logger = logger
  }

  @Override
  protected void before() throws Throwable {
    logField.accessible = true

    Field modifiersField = Field.getDeclaredField("modifiers")
    modifiersField.accessible = true
    modifiersField.setInt(logField, logField.getModifiers() & ~Modifier.FINAL)

    originalLogger = (Logger) logField.get(null)
    logField.set(null, logger)
  }

  @Override
  protected void after() {
    logField.set(null, originalLogger)
  }
}
