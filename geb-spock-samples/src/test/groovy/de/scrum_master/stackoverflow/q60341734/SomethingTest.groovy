package de.scrum_master.stackoverflow.q60341734

import org.junit.Rule
import org.junit.rules.TestName
import spock.lang.Specification

class SomethingTest extends Specification {
  @Rule
  TestName testName

  def "some test"() {
    given:
    def d = Mock(Doer)
    def s = new Something(d)

    when:
    s.doSth(2)

    then:
    1 * d.validate({ println "$testName.methodName: closure parameter = $it"; it == 2 }) >> true
  }

  def "another test"() {
    given:
    def d = Mock(Doer)
    def s = new Something(d)
    def myClosureVar = { println "$testName.methodName: closure parameter = $it"; it == 2 }

    when:
    s.doSth(2)

    then:
    1 * d.validate({ myClosureVar(it) }) >> true
  }
}
