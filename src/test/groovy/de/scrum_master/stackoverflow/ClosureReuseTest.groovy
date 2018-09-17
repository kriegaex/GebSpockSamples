package de.scrum_master.stackoverflow

import spock.lang.Specification

class ClosureReuseTest extends Specification {
  static enum Something {
    ONE, TWO, THREE
  }

  static class Dao {
    Optional<Something> doSomething() {
      getByValue(getBlah(Something.ONE, "foo"), "foo")
    }

    Something getBlah(Something something, String message) {
      println "getBlah"
      something
    }

    Optional<Something> getByValue(Something something, String message) {
      println "getByValue"
      Optional.of(something)
    }
  }

  def "test1"() {
    given:
    Dao dao = makeDaoSpy(1, 1)

    when:
    def result = dao.doSomething()

    then:
    result.isPresent()
    result.get() == Something.THREE
  }

  def makeDaoSpy(num1, num2) {
    Spy(Dao) {
      num1 * getBlah(Something.ONE, _ as String) >> { Something smth, String value ->
        println "getBlahClosure -> $smth, $value"
        Something.TWO
      }
      num2 * getByValue(Something.TWO, _ as String) >> { Something smth, String value ->
        println "getByValueClosure -> $smth, $value"
        Optional.of(Something.THREE)
      }
    }
  }

  def "test2"() {
    given:
    Dao dao = Spy()

    when:
    dao.getByValue(Something.TWO, "foobar")
    def result = dao.doSomething()

    then:
    interaction {
      getBlahInteraction(1, dao, "getBlah", Something.ONE)
      getByValueInteraction(2, dao, Something.TWO)
    }
    result.isPresent()
    result.get() == Something.THREE
  }

  def getBlahInteraction(int times, Object targetObject, String methodName, Something something) {
    times * targetObject."$methodName"(something, _ as String) >> { Something smth, String value ->
      println "getBlahClosure -> $smth, $value"
      Something.TWO
    }
  }

  def getByValueInteraction(int times, Dao dao, Something something) {
    times * dao.getByValue(something, _ as String) >> { Something smth, String value ->
      println "getByValueClosure -> $smth, $value"
      Optional.of(Something.THREE)
    }
  }

  def "test3"() {
    given:
    Dao dao = Spy()

    when:
    def result = dao.doSomething()

    then:
    1 * dao.getBlah(Something.ONE, _ as String) >> getBlahClosure.call(Something.ONE, "DUMMY")
    1 * dao.getByValue(Something.TWO, _ as String) >> getByValueClosure.call(Something.TWO, "DUMMY")
    result.isPresent()
    result.get() == Something.THREE
  }

  def getBlahClosure = { Something smth, String value ->
    println "getBlahClosure -> $smth, $value"
    Something.TWO
  }

  def getByValueClosure = { Something smth, String value ->
    println "getByValueClosure -> $smth, $value"
    Optional.of(Something.THREE)
  }
}
