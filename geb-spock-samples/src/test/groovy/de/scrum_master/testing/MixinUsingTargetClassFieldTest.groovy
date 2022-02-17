package de.scrum_master.testing

import spock.lang.Specification

class MixinUsingTargetClassFieldTest extends Specification {
  def setupSpec() {
    Target.mixin MyMixin
  }

/*
  // See https://github.com/groovy/groovy-eclipse/issues/1292
  static class MyMixin {
    String nameToUpper() {
      name.toUpperCase()
    }
  }
*/

  def test() {
    expect:
    new Target(name: "John Doe").nameToUpper() == "JOHN DOE"
  }
}

class Target {
  protected String name
}

// See https://github.com/groovy/groovy-eclipse/issues/1292
class MyMixin {
  String nameToUpper() {
    name.toUpperCase()
  }
}
