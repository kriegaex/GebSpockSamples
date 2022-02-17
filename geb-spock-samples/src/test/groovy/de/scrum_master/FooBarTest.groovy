package de.scrum_master

import spock.lang.Specification

class FooBarTest extends Specification {
  def test() {
    expect:
    !Mock(FooBar).isFlag()
    !Mock(FooBar).flag
    !Mock(Baz).isFlag()
    !Mock(Baz).flag
    !Mock(ExampleData).isCurrent()
    !Mock(ExampleData).current
  }

  static class Baz {
    boolean isFlag() {
      return true
    }
  }
}
