package de.scrum_master.stackoverflow.q68674245

import spock.lang.Specification
import spock.lang.Unroll

import static de.scrum_master.stackoverflow.q68674245.StateMachineTest.StateMachine.*

class StateMachineTest extends Specification {
  @Unroll("#current can transit to #next")
  def "Validate successful transition of the state machine"() {
    expect:
    current.every { from -> next.every { to -> from.canTransitionTo(to) } }

    where:
    current                            | next
    [START, STATE_A, STATE_B, STATE_C] | [STATE_A, STATE_B, STATE_C]
  }

  @Unroll("#current must not transit to #next")
  def "Validate failed state machine transition"() {
    expect:
    current.every { from -> next.every { to -> !from.canTransitionTo(to) } }

    where:
    current                            | next
    [START, STATE_A, STATE_B, STATE_C] | START
  }

  static enum StateMachine {
    START, STATE_A, STATE_B, STATE_C

    boolean canTransitionTo(StateMachine stateMachine) {
      println "transition $this -> $stateMachine"
      return stateMachine != START
    }
  }
}
