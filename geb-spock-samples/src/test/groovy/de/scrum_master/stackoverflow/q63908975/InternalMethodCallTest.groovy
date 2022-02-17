package de.scrum_master.stackoverflow.q63908975

import spock.lang.Specification

class InternalMethodCallTest extends Specification {
  Foo foo = Spy()

  def "bar calls biz"() {
    when:
    foo.bar()

    then:
    1 * foo.biz(_)
  }

  static class Foo {
    void bar() { biz("dummy") }
    void biz(Object object) {}
  }
}
