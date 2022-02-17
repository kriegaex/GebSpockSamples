package de.scrum_master.stackoverflow.q66197474

import spock.lang.Specification

class MyClassTest extends Specification {
  Foo foo = Mock()
  def myClass = Spy(MyClass, constructorArgs: [foo]) {
    getStringBar() >> "mock stringBar"
  }

  def myFeature() {
    expect:
    myClass.getStringBar() == "mock stringBar"
    myClass.testFunction() == "value of testFunction"
  }
}
