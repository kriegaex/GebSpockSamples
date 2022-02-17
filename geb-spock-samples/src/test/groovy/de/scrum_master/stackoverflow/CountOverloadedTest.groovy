package de.scrum_master.stackoverflow

import spock.lang.Specification

class CountOverloadedTest extends Specification {
  static class UnderTest {
    def doSomething() {
      ArrayList build_name_selector = ["1", "2", "3", "a", "b"]
      println "COUNT ${build_name_selector.count('1')} "  //WORKS
      println "EMPTYCOUNT ${build_name_selector.count({ true })}"  // FAILS
      println build_name_selector.class
    }
  }

  def test() {
    new UnderTest().doSomething()
    expect:
    true
  }
}
