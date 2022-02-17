package de.scrum_master.stackoverflow.q60926015

import spock.lang.Specification

class SchedulableNodeTest extends Specification {
  def actor = Mock(Actor)

  def "Populating edge info means both source and destination information will be populated"() {
    given:
    actor.getDstChannels() >> []
    actor.getSrcChannels() >> []
    SchedulableNode schedulable = Spy(SchedulableNode, constructorArgs: [actor])

    when:
    schedulable.populateEdgeInfo([:])

    then:
    1 * schedulable.populateDestinationInfo(_)
    1 * schedulable.populateSourceInfo(_)
  }
}
