package de.scrum_master.stackoverflow.q71235231

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.Appender
import spock.lang.Specification
import spock.lang.Unroll

class ClassWithLoggerTest extends Specification {
  @Unroll("test logger with number #number")
  def "test logger"() {
    given:
    // Groovy can simply access private fields, if we know their names
    Logger logger = ClassWithLogger.LOGGER
    def appender = Mock(Appender)
    logger.addAppender(appender)

    when:
    new ClassWithLogger().logSomething(number)

    then:
    infoCount * appender.doAppend({ LoggingEvent event ->
      event.level == Level.INFO && event.formattedMessage == infoMessage
    })
    errorCount * appender.doAppend({ LoggingEvent event ->
      event.level == Level.ERROR && event.formattedMessage == errorMessage
    })
    warningCount * appender.doAppend({ LoggingEvent event ->
      event.level == Level.WARN && event.formattedMessage == warningMessage
    })

    cleanup:
    logger.detachAppender(appender)

    where:
    number << [4, 1, 0, -1, -7]
    infoCount = 1
    infoMessage = "FYI, 3 * $number = ${3 * number}"
    errorCount = number == 0 ? 1 : 0
    errorMessage = "Using zero is forbidden"
    warningCount = number < 0 ? 1 : 0
    warningMessage = "Be warned that $number is a negative number"
  }
}
