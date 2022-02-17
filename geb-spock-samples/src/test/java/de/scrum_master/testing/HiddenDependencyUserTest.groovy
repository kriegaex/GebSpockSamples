package de.scrum_master.testing

import spock.lang.Specification

class HiddenDependencyUserTest extends Specification {
  def testJavaCode() {
    given: "Java class using hidden dependency"
    HiddenDependency hiddenDependency = GroovySpy(global : true) {
      getResult() >> "mocked result"
    }

    expect: "Global Groovy spy does not work for Java code under test"
    new HiddenDependencyUser().message != "mocked result"
  }

  def testGroovyCode() {
    given: "Groovy class using hidden dependency"
    HiddenDependencyGroovy hiddenDependency = GroovySpy(global : true) {
      getResult() >> "mocked result"
    }

    expect: "Global Groovy spy works for Groovy code under test"
    new HiddenDependencyUserGroovy().message == "mocked result"
  }
}
