package de.scrum_master.stackoverflow.q64576816

import spock.lang.Specification

class OptionalObjectListTest extends Specification {
  def test() {
    given:
    Optional<List<Object>> optionalObjectList = Optional.of([new Foo()])
    println optionalObjectList
    optionalObjectList = Optional.of([Mock(Foo)])
    println optionalObjectList

    expect:
    true
  }

  static class Foo {}

  static class A {
    private /*final*/ B b;

    A() {
      this.b = new B(new OtherService())
    }
  }

  static class B {
    private OtherService otherService

    B(OtherService service) {
      otherService = service
    }
  }

  static class OtherService {}

  def test2() {
    given:
    A a = new A(b: Mock(B))
//    A a = Spy(useObjenesis: false, constructorArgs: [["b": Mock(B)]])
    println a
    println a.b

    expect:
    true
  }
}
