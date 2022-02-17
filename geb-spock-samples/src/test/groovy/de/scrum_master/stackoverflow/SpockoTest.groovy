package de.scrum_master.stackoverflow

import spock.lang.Specification

class SpockoTest extends Specification {
  static class Spocko {
    def something

    def doStuff() {
          something = 'fruit'
//      this.each {
//        it.something = 'fruit'
//      }
    }

    def doMoreStuff() {
      setSomething('vegetable')
    }
  }

  def 'test it'() {
    given: 'Spocko spy'
    Spocko spySpocko = Spy(Spocko)

    when: 'calling method assigning value to property'
    spySpocko.doStuff()
    then: 'no setter is called'
    0 * spySpocko.setSomething(_)
    spySpocko.something == 'fruit'

    when: 'calling method using setter'
    spySpocko.doMoreStuff()
    then: 'setter gets called'
    1 * spySpocko.setSomething('vegetable')

    when: 'using Groovy setter-like syntax from another class'
    spySpocko.something = 'fish'
    then: 'actually a setter gets called'
    1 * spySpocko.setSomething('fish')
  }
}
