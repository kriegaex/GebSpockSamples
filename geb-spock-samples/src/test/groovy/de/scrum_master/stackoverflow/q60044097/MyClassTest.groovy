package de.scrum_master.stackoverflow.q60044097

import spock.lang.Specification

class MyClassTest extends Specification {
  def "testMyMethod"() {
    given:
    def steps = Spy(Steps)
    def script = new Script()
    def myClass = new MyClass(steps)
    def dataObject = [
      'myKeyValue': [['branch': 'mock']]
    ]

    when:
    myClass.myMethodToTest(script, credId, dataObject)

    then:
    1 * steps.withCredentials(
      [
        [
          $class          : 'UsernamePasswordMultiBinding',
          credentialsId   : "mycredId",
          usernameVariable: 'USR',
          passwordVariable: 'PWD'
        ]
      ],
      _
    )
    1 * steps.sh(shString)

    where:
    credId     | shString
    "mycredId" | "git push --set-upstream origin mock"
  }
}
