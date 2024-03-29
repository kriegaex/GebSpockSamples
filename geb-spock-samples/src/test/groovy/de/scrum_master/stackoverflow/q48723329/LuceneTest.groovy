package de.scrum_master.stackoverflow.q48723329

import org.apache.lucene.document.TextField
import spock.lang.Specification

class LuceneTest extends Specification {
  // Sarek tests were moved into their own module. Keep this for "how-to" documentation purposes.
  /*
  @IgnoreIf({
    // If Sarek unfinal agent is active, mocking final Lucene class TextField unexpectedly works,
    // so the expected NPE will not be thrown here
    SarekAgent.unFinalActive
  })
  */
  def "Lucene text field normal GroovyMock"() {
    given: "normal Groovy mock"
    TextField textField = GroovyMock() {
      stringValue() >> "abc"
    }

    when: "calling parent method"
    textField.setStringValue("test")

    then: "exception is thrown"
    thrown NullPointerException

    and: "parent method stubbing does not work"
    textField.stringValue() == null
  }

  def "Lucene text field global GroovyMock"() {
    given: "global Groovy mock"
    TextField textField = GroovyMock(global: true) {
      stringValue() >> "abc"
    }

    expect: "can call parent method"
    textField.setStringValue("test")

    and: "parent method stubbing works"
    textField.stringValue() == "abc"
  }
}
