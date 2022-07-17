package de.scrum_master.testing

import spock.lang.Specification
import spock.util.concurrent.AsyncConditions
import spock.util.concurrent.BlockingVariable
import spock.util.concurrent.BlockingVariables
import spock.util.concurrent.PollingConditions

class AsyncTest extends Specification {
  static final def SLEEP_MILLIS = 100
  static final def TIMEOUT_SECS = Math.max(0.4, 1.5 * SLEEP_MILLIS / 1000)

  def logAndSleep() {
    println "Asynchronous action started"
    sleep SLEEP_MILLIS
    println "Asynchronous action finished"
  }

  def "join thread"() {
    when:
    def thread = Thread.start {
      logAndSleep()
    }

    then:
    thread.join()
  }

  def "use PollingConditions"() {
    given:
    boolean finished

    when:
    Thread.start {
      logAndSleep()
      finished = true
    }

    then:
    new PollingConditions(timeout: TIMEOUT_SECS).eventually {
      // No need to use 'assert', unless 'PollingConditions' is assigned via 'def' instead of explicit type
      finished
    }
  }

  def "use AsyncConditions"() {
    given:
    boolean finished
    // Avoid the need to use 'assert' by helping the Groovy compiler, specifying an explicit type instead of using 'def'
    AsyncConditions conditions = new AsyncConditions()

    when:
    Thread.start {
      logAndSleep()
      finished = true
      // This is a bit contrived here and better suited for callbacks
      conditions.evaluate {
        finished
      }
    }

    then:
    conditions.await(2)
  }

  def "use BlockingVariable"() {
    given:
    def finished = new BlockingVariable<Boolean>(TIMEOUT_SECS)

    when:
    Thread.start {
      logAndSleep()
      finished.set(true)
    }

    then:
    finished.get()
  }

  def "use BlockingVariables"() {
    given:
    def vars = new BlockingVariables(TIMEOUT_SECS)

    when:
    Thread.start {
      logAndSleep()
      vars.finished = true
    }

    then:
    vars.finished
  }
}
