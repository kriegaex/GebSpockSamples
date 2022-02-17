package de.scrum_master.stackoverflow.q60601740

import spock.lang.Ignore
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Timeout

import static java.lang.System.currentTimeMillis

@Ignore("reproduces that a timeout actually works, other than reported on SO")
@Issue("https://stackoverflow.com/a/60610944/1082681")
class StartupTest extends Specification {
  def startTime = currentTimeMillis()

  def timeElapsed() {
    currentTimeMillis() - startTime
  }

  void checkConnectedProducts() {
    for (int i = 1; i <= 5; i++) {
      println "${timeElapsed()} | checkConnectedProducts #$i, sleeping 1 s"
      Thread.sleep(1000)
    }
  }

  int countError() {
    println "${timeElapsed()} | countError, sleeping 4 s"
    sleep 4000
    return 3
  }

  @Timeout(3)
  def 'Start_test'() {
    setup:
    true

    when: 'Test_started'
    true

    and: 'Check_something'
    true

    then: 'Validate_something'
    checkConnectedProducts()

    cleanup:
    countError()
    println "${timeElapsed()} | cleanup, sleeping 4 s"
    sleep 4000
    println "${timeElapsed()} | cleanup finished"
  }
}
