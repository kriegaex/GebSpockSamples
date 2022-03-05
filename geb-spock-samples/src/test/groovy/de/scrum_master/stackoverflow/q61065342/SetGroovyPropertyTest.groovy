package de.scrum_master.stackoverflow.q61065342

import spock.lang.Specification

/**
 * https://stackoverflow.com/q/61065342/1082681
 */
class SetGroovyPropertyTest extends Specification {
  void 'test'() {
    setup:
    def collaborator = Mock(Collaborator)

    when:
    new UnderTest().call(collaborator, 'test')

    then:
    // Both of these seem to be called sometimes on Groovy 2.5, maybe depending on the OS platform,
    // test order or whatever
    1 * collaborator.setX('test')
    //1 * collaborator.setProperty('x', 'test')

    // Try to be as generic as possible, matching both setX and setProperty
//    1 * collaborator./set(X|Property)/(*_) >> { args ->
//      println args
//      assert args.last() == 'test'
//    }
  }
}
