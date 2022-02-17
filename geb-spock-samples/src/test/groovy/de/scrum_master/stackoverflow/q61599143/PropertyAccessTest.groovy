package de.scrum_master.stackoverflow.q61599143

import spock.lang.Issue
import spock.lang.PendingFeature
import spock.lang.Specification

@Issue([
  "https://stackoverflow.com/a/61626273/1082681",
  "https://github.com/spockframework/spock/issues/1158"
])
class PropertyAccessTest extends Specification {
  def 'test setter on interface'() {
    given:
    def foo = Mock(Foo)
    when:
    foo.prop = 'val'
    then:
    1 * foo.setProp(_)
  }

  def 'test getter on interface'() {
    given:
    def foo = Mock(Foo)
    when:
    foo.prop
    then:
    1 * foo.getProp()
  }

  @PendingFeature
  def 'test setter on abstract'() {
    given:
    def foo = Mock(FooBase)
    when:
    foo.prop = 'val'
    //foo.setProp('val')
    then:
    1 * foo.setProp(_)
    //1 * foo.setProperty('prop', 'val')
  }

  @PendingFeature
  def 'test getter on abstract'() {
    given:
    def foo = Mock(FooBase)
    when:
    foo.prop
    //foo.getProp()
    then:
    1 * foo.getProp()
    //1 * foo.getProperty('prop')
  }

  @PendingFeature
  def 'test setter on concrete'() {
    given:
    def foo = Mock(FooImpl)
    when:
    foo.prop = 'val'
    //foo.setProp('val')
    then:
    1 * foo.setProp(_)
    //1 * foo.setProperty('prop', 'val')
  }

  @PendingFeature
  def 'test getter on concrete'() {
    given:
    def foo = Mock(FooImpl)
    when:
    foo.prop
    //foo.getProp()
    then:
    1 * foo.getProp()
    //1 * foo.getProperty('prop')
  }

  static interface Foo {
    String getProp()

    void setProp(String val)
  }

  static abstract class FooBase implements Foo {
    abstract String getProp();

    abstract void setProp(String val);
  }

  static class FooImpl extends FooBase {
    private String prop

    String getProp() {
      println('Foo.getProp')
      return prop
    }

    void setProp(String val) {
      println('Foo.setProp')
      this.prop = val
    }

    static void main(String[] args) {
      def fooImpl = new FooImpl()
      fooImpl.prop = "test1"
      println fooImpl.prop
      fooImpl.setProp("test2")
      println fooImpl.prop
    }
  }
}
