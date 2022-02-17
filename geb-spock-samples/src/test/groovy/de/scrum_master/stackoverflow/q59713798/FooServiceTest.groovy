package de.scrum_master.stackoverflow.q59713798

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class FooServiceTest extends Specification {
  def fooService = new FooService()
  def someClass = new SomeClass()

  def "real SomeClass static method"() {
    expect:
    fooService.getFoo(someClass) == 66
  }

  def "mocked SomeClass static method"() {
    given:
    def originalMetaClass = SomeClass.metaClass
    SomeClass.metaClass.static.findAllByCodeLike = { code ->
      [
        new SomeClass(someClassAttribute: "1"),
        new SomeClass(someClassAttribute: "2"),
        new SomeClass(someClassAttribute: "3"),
        new SomeClass(someClassAttribute: "4")
      ]
    }

    expect:
    fooService.getFoo(someClass) == 10

    cleanup:
    SomeClass.metaClass = originalMetaClass
  }

  def "real SomeClass static method after meta-class reset"() {
    // Due to @Stepwise this feature method is guaranteed to run *after* the one
    // mocking the static method via metaClass. The condition checks that the test fixture
    // in the other method has been cleaned up, e.g. the metaClass has been reset.

    expect:
    fooService.getFoo(someClass) == 66
  }
}
