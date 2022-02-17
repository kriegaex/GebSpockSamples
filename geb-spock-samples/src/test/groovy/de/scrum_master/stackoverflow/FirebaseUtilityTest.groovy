package de.scrum_master.stackoverflow

import spock.lang.Specification

class FirebaseUtilityTest extends Specification {
  def "instantiating FirebaseUtility runs initialization code exactly once"() {
    given:
    FirebaseUtility f = Spy()

    when:
    f.getDb()
    then:
    1 * f.initializeFirebase()

    when:
    f.getDb()
    then:
    0 * f.initializeFirebase()
  }
}
