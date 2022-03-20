package de.scrum_master.stackoverflow.q61065342

import spock.lang.IgnoreIf
import spock.lang.Issue
import spock.lang.Specification

/**
 * https://stackoverflow.com/q/61065342/1082681
 */
@Issue("https://github.com/groovy/groovy-eclipse/issues/1353")
class SetGroovyPropertyTest extends Specification {
  // TODO: Fix problem on Linux GitHub CI. Is it Groovy or Spock?
  @IgnoreIf({ os.linux })
  void 'expect setX'() {
    setup:
    def collaborator = Mock(Collaborator)

    when:
    new UnderTest().call(collaborator, 'test')

    then:
    // Both of these seem to be called sometimes on Groovy 2.5, maybe depending on the OS platform,
    // test order or whatever
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
      // TODO: Remove log output after problem on Linux GitHub CI has been fixed
      println args
      new Exception("exception logged for debugging purposes").printStackTrace(System.out)
      assert args.last() == 'test'
    }
  }
}
