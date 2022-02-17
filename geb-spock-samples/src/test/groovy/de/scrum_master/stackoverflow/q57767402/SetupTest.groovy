package de.scrum_master.stackoverflow.q57767402

import spock.lang.Ignore
import spock.lang.See
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
//@Feature("Job")
//@Story("Spec with exception in setup")
@Ignore
@See("https://stackoverflow.com/q/57767402")
class SetupTest extends Specification {

  def setupSpec() {
    println 'in setup spec'
  }

  def cleanupSpec() {
    println 'in cleanup spec\n' + "-" * 70
  }

  def 'Test 1'() {
    setup:
    println 'in test 1'
    expect:
    2 == 2
  }

  def 'Test 2'() {
    setup:
    println 'in test 2'
    expect:
    2 == 3
  }
}
