package de.scrum_master.stackoverflow.q58279620

class Dummy {
  static void main(String[] args) {
    assert ClassUnderTest.someMethod((Integer) null) == -999
    assert ClassUnderTest.someMethod((String) null) == "nothing"
    assert ClassUnderTest.someMethod(null as Integer) == -999
    assert ClassUnderTest.someMethod(null as String) == "nothing"
  }
}
