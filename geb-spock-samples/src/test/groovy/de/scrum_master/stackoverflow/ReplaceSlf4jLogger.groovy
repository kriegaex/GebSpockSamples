package de.scrum_master.stackoverflow

import org.junit.rules.ExternalResource
import org.slf4j.Logger

import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * Helper to replace Lombok logger by mock:
 *   - allows to assert log action
 *   - undos change after test to keep normal logging in other tests
 *
 * See https://stackoverflow.com/a/49870470/1082681
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
