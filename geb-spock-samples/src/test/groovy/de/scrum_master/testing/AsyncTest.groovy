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
    def conditions = new PollingConditions(timeout: TIMEOUT_SECS)

    when:
    Thread.start {
      logAndSleep()
      finished = true
    }

    then:
    conditions.eventually {
      // We *have* to use 'assert' here! This is *not* a 'then:' block!
      assert finished
    }
  }

  def "use AsyncConditions"() {
    given:
    boolean finished
    def conditions = new AsyncConditions()

    when:
    Thread.start {
      logAndSleep()
      finished = true
      // This is a bit contrived here and better suited for callbacks
      conditions.evaluate {
        // We *have* to use 'assert' here! This is *not* a 'then:' block!
        assert finished
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
