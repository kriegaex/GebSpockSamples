package de.scrum_master.testing

import spock.lang.Specification
import spock.lang.Unroll

class InteractionTest extends Specification {
  @Unroll
  def "interaction, turn #turn"() {
    given:_ "My 'given' comment, turn $turn"
    def foo = 11
    def bar = foo + 4
    println "blah"

    expect:_ "My 'expect' comment, turn $turn"
    interaction {
      foo = 2
      bar = 5
      true
    }
    println "foo"
    foo * bar == 10
    foo + bar == 7

    and:_ "My 'and' comment, turn $turn"
    true

    where:
    turn << [1, 2]
  }
}
