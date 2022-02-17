package de.scrum_master.issue.i1256

import spock.lang.PendingFeature
import spock.lang.Specification

class TesterSpec extends Specification {
  static class Tester {
    boolean isEnabled() {
      return true
    }
  }

  def 'Mock works'(){
    given:
    Tester x = Mock(Tester)
    when:
    x.enabled
    then:
    1 * x.isEnabled() >> true
  }

  def 'Spy works'() {
    given:
    Tester x = Spy(Tester)
    when:
    x.enabled
    then:
    1 * x.isEnabled() >> true
  }

  @PendingFeature
  def 'GroovyMock does not work'(){
    given:
    Tester x = GroovyMock(Tester)
    when:
    x.enabled
    then:
    1 * x.isEnabled() >> true
  }

  @PendingFeature
  def 'GroovySpy does not work'() {
    given:
    Tester x = GroovySpy(Tester)
    when:
    x.enabled
    then:
    1 * x.isEnabled() >> true
  }

}
