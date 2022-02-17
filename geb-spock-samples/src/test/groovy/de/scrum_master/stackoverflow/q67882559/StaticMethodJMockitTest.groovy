package de.scrum_master.stackoverflow.q67882559

import mockit.Mock
import mockit.MockUp
import mockit.internal.state.SavePoint
import spock.lang.Requires
import spock.lang.Specification
import spock.lang.Unroll

import static de.scrum_master.testing.JavaAgentDetector.JMockitActive

@Requires({ JMockitActive })
class StaticMethodJMockitTest extends Specification {
  def jMockitSavePoint = new SavePoint()

  def cleanup() {
    jMockitSavePoint.rollback()
  }

  @Unroll
  def "verify #description"() {
    given:
    mockClosure()
    MyClass myClass = new MyClass()

    when:
    myClass.verify(0)

    then:
    true

    where:
    description     | mockClosure
    "no mock"       | { /* no mock */ }
    "mock result 1" | { mockStatic(1) }
    "mock result 2" | { mockStatic(2) }
  }

  def mockStatic(val) {
    new MockUp<MyUtils>() {
      @Mock
      int staticMethod(int origin) {
        return val
      }
    }
  }

  public static class MyUtils {
    public static int staticMethod(int origin) {
      return 0;
    }
  }

  public static class MyClass {
    public void verify(int origin) {
      if (MyUtils.staticMethod(origin) == 1) {
        System.out.println("1");
      }
      if (MyUtils.staticMethod(origin) == 2) {
        System.out.println("2");
      }
    }
  }
}
