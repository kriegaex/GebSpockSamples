package de.scrum_master.stackoverflow

import groovy.transform.TypeChecked
import spock.lang.Specification

class CarTest extends Specification {
  def "Default engine state"() {
    given:
    def engine = Mock(Engine)
    def car = new Car(engine: engine)

    when:
    car.drive()

    then:
    true
  }

  def "Changed engine state"() {
    given:
    def engine = Mock(Engine) {
      isState() >> true
    }
    def car = new Car(engine: engine)

    when:
    car.drive()

    then:
    true
  }

  def "Use the 'verifyAll' Spock feature"() {
    given:
    Closure sq = { x ->
      x * x
    }
    expect:
    verifyAll {
      sq(1) == 1
      sq(2) == 99
      sq(2) == 4
      sq(3) == 33
      sq(0.5) == 0.25
    }
  }
}
