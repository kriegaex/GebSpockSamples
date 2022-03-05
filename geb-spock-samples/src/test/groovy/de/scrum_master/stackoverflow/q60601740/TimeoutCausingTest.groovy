package de.scrum_master.stackoverflow.q60601740

import spock.lang.Ignore
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

import static java.lang.System.currentTimeMillis

@Ignore("Fails on purpose by causing a timeout")
@Issue("https://stackoverflow.com/a/60610944/1082681")
class TimeoutCausingTest extends Specification {
  static final int SLEEP_MILLIS = 500
  static final int TIMEOUT_MILLIS = 300

  def startTime = currentTimeMillis()

  def timeElapsed() {
    currentTimeMillis() - startTime
  }

  void checkConnectedProducts() {
    printf "%6d | checkConnectedProducts, sleeping %d ms%n", timeElapsed(), SLEEP_MILLIS
    // Interruptable
    Thread.sleep SLEEP_MILLIS
    // Non-interruptable
    // sleep SLEEP_MILLIS
    // Interruptable
    // sleep SLEEP_MILLIS, { true }
    printf "%6d | checkConnectedProducts finished%n", timeElapsed()
  }

  int countError() {
    printf "%6d | countError, sleeping %d ms%n", timeElapsed(), SLEEP_MILLIS
    // Interruptable, but will not be interrupted by '@Timeout' when called from method 'cleanup'
    Thread.sleep SLEEP_MILLIS
    printf "%6d | countError finished%n", timeElapsed(), SLEEP_MILLIS
    return 3
  }

  @Timeout(value = TIMEOUT_MILLIS, unit = TimeUnit.MILLISECONDS)
  def 'Start_test'() {
    expect: 'Validate_something'
    checkConnectedProducts()
  }

  /**
   * Even if a feature method (including its 'cleanup:' block) times out and gets aborted,
   * the 'cleanup' method will be executed.
   */
  def cleanup() {
    countError()
    printf "%6d | cleanup, sleeping %d ms%n", timeElapsed(), SLEEP_MILLIS
    // Interruptable, but will not be interrupted by '@Timeout' within method 'cleanup'
    Thread.sleep SLEEP_MILLIS
    printf "%6d | cleanup finished%n", timeElapsed()
    // Verify that really as much time has elapsed as we expect, i.e. 'cleanup()' has not been interrupted
    assert timeElapsed() >= TIMEOUT_MILLIS + SLEEP_MILLIS + SLEEP_MILLIS
  }
}
