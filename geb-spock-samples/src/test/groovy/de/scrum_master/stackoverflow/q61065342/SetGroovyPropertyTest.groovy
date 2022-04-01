package de.scrum_master.stackoverflow.q61065342

import spock.lang.Issue
import spock.lang.Specification

/**
 * https://stackoverflow.com/q/61065342/1082681
 */
@Issue("https://github.com/groovy/groovy-eclipse/issues/1353")
class SetGroovyPropertyTest extends Specification {
  // GrEclipse #1353 should be fixed in GrEclipse Batch 2.5.16-02
  // @IgnoreIf({ os.linux })
  void 'expect setX'() {
    setup:
    def collaborator = Mock(Collaborator)

    when:
    new UnderTest().call(collaborator, 'test')

    then:
    // Both of these were be called sometimes on Groovy 2.5.x < 2.5.16-02 under Linux
    1 * collaborator.setX('test')
    //1 * collaborator.setProperty('x', 'test')
  }

  void 'expect setX or setProperty'() {
    setup:
    def collaborator = Mock(Collaborator)

    when:
    new UnderTest().call(collaborator, 'test')

    then:
    // Try to be as generic as possible, matching both setX and setProperty
    1 * collaborator./set(X|Property)/(*_) >> { args ->
      assert args.last() == 'test'
    }
  }
}
