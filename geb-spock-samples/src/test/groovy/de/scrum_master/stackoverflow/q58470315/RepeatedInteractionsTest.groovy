package de.scrum_master.stackoverflow.q58470315

import spock.lang.Specification

class RepeatedInteractionsTest extends Specification {
  def "test scene1"() {
    given: "subject under test with injected mock"
    ToBeTestedWithInteractions subjectUnderTest = new ToBeTestedWithInteractions()
    DataService dataService = Mock()
    subjectUnderTest.dataService = dataService

    when: "getting data"
    subjectUnderTest.getData()

    then: "no error, normal return values"
    noExceptionThrown()
    1 * dataService.findByOffset(5) >> dataService
    1 * dataService.getOffset() >> 200

    when: "getting data"
    subjectUnderTest.getData()

    then: "NPE, only first method called"
    thrown NullPointerException
    1 * dataService.findByOffset(5) >> null
    0 * dataService.getOffset()
  }
}
