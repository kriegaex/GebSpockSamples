package de.scrum_master.stackoverflow.q61065342

import spock.lang.Specification

class SetGroovyPropertyTest extends Specification {
  void 'test'() {
    setup:
    def collaborator = Mock(Collaborator)

    when:
    new UnderTest().call(collaborator, 'test')

    then:
    1 * collaborator.setX('test')
//    1 * collaborator.setProperty('x', 'test')
  }
}
