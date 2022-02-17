package de.scrum_master.stackoverflow.q59442086

import spock.lang.Specification

class MyClassTest extends Specification {
  def "testMyMethod"() {
    given:
    // Cannot use mock here because mock would have 'env' set to null. Furthermore,
    // we want to test the side effect of 'steps.sh()' being called from within the
    // closure, which also would not work with a mock. Thus, we need a spy.
    def steps = Spy(Steps)
    def myClass = new MyClass(steps)
    def script = new Script()
    script.env['USR'] = "test-user"

    when:
    myClass.myMethodToTest(script, credId)

    then:
    1 * steps.withCredentials(
      [
        [
          class: 'UsernamePasswordMultiBinding',
          credentialsId: credId,
          usernameVariable: 'USR',
          passwordVariable: 'PWD'
        ]
      ],
      _  // Don't forget the closure parameter!
    )
    // Here we need to test for a substring via argument constraint
    1 * steps.sh({ it.contains(shString) })

    where:
    credId     | shString
    "mycredId" | "export USR=test-user"
  }
}
