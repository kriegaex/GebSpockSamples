package de.scrum_master.stackoverflow.q61667088

import org.apache.lucene.index.DocIDMerger
import spock.lang.Specification

class GlobalMockTest extends Specification {

  def "use Spock mock"() {
    given:
    def step = Mock(Step) {
      getName() >> "Joe"
    }
    def stage = new Stage("John")
    stage.step = step

    expect:
    stage.run() == "Joe"
  }

  def "use global GroovySpy"() {
    given:
    GroovySpy(Step, global: true) {
      getName() >> "Joe"
    }
    Step.staticMethod() >> "stubbed"
    def stage = new Stage("John")

    expect:
    Step.staticMethod() == "stubbed"
    stage.run() == "Joe"
  }

  def "use global GroovyMock"() {
    given:
    def globalMock = GroovyMock(Step, global: true)
    new Step(*_) >> Mock(Step) {
      getName() >> "Joe"
    }
    Step.staticMethod() >> "stubbed"

    when:
    def stage = new Stage("John")

    then:
    Step.staticMethod() == "stubbed"
    stage.run() == "Joe"
  }

}
