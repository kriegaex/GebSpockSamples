package de.scrum_master.stackoverflow.q62269054

import spock.lang.Specification

class MyTestSpec extends Specification {
  def "testing addition using aMethod"() {
    given:
    CalcIntf calcIntf = Mock(Calc)
    calcIntf.aMethod(_, _) >> { return 123 }
    Math math = new Math(calcIntf)
    expect:
    math.m1() == 123
  }

  def "testing addition using bMethod"() {
    given:
    CalcIntf calcIntf = Mock(Calc)
    calcIntf.bMethod(_, _, _) >> { return 456 }
    Math math = new Math(calcIntf)
    expect:
    math.m2() == 456
  }

  interface CalcIntf {
    int aMethod(int a, int b)

    int bMethod(int a, int b, int c)
  }

  static class Calc implements CalcIntf {
    int aMethod(int a, int b) {
      return a + b
    }

    final int bMethod(int a, int b, int c) {
      return a + b + c
    }
  }

  static class Math {
    CalcIntf calcIntf

    Math(CalcIntf calcIntf) {
      this.calcIntf = calcIntf
    }

    int m1() {
      calcIntf.aMethod(1, 2)
    }

    int m2() {
      calcIntf.bMethod(1, 2, 3)
    }
  }
}
