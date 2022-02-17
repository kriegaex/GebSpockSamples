package de.scrum_master.stackoverflow.q70149644

import spock.lang.Specification

class TestResultMapTest extends Specification {
  def "resource is authorized"() {
    given:
    TestResultMap map = new TestResultMap()
    TestResult testResult = Stub() {
      isAuthorized() >> true
    }
    map.put("resource", testResult)

    expect:
    map.isAuthorized("resource")
  }

  def "resource is unauthorized"() {
    given:
    TestResultMap map = new TestResultMap()
    TestResult testResult = Stub() {
      isAuthorized() >> false
    }
    map.put("resource", testResult)

    expect:
    !map.isAuthorized("resource")
  }

  def "resource not found"() {
    given:
    TestResultMap map = Spy() {
      get(_) >> null
    }

    when:
    map.isAuthorized("resource")

    then:
    def rte = thrown RuntimeException
    rte.message == "Authorization not calculated"
  }

  def "test without mocks"() {
    given:
    TestResultMap map = new TestResultMap()
    map.put("OK", new TestResult("Hello world"))
    map.put("not OK", new TestResult("Access denied"))

    expect:
    map.isAuthorized("OK")
    !map.isAuthorized("not OK")

    when:
    map.isAuthorized("foo")

    then:
    def rte = thrown RuntimeException
    rte.message == "Authorization not calculated"
  }
}
