package de.scrum_master.stackoverflow

import org.junit.Rule
import org.junit.rules.TestName
import spock.lang.IgnoreIf
import spock.lang.Specification
import spock.lang.Unroll

@IgnoreIf({ jvm.java10Compatible }) // TODO: find JVM switches to permit reflection
class HighlanderTest extends Specification {
  def singletonTool = new GroovySingletonTool<Highlander>(Highlander)
  @Rule
  TestName gebReportingSpecTestName

  def setup() {
    println "\n--- $gebReportingSpecTestName.methodName ---"
  }

  @Unroll
  def "Highlander fight no. #fightNo"() {
    given:
    singletonTool.reinitialiseSingleton()
    def highlander = Highlander.instance

    when:
    highlander.fight()

    then:
    highlander.count == 1

    where:
    fightNo << [1, 2, 3]
  }

  @Unroll
  def "Highlander stub fight no. #fightNo"() {
    given:
    Highlander highlanderStub = Stub() {
      fight() >> { println "I am a stub" }
    }
    singletonTool.setSingleton(highlanderStub)
    def highlander = Highlander.instance

    when:
    highlander.fight()

    then:
    highlander == highlanderStub

    where:
    fightNo << [1, 2, 3]
  }

  @Unroll
  def "Highlander mock fight no. #fightNo"() {
    given:
    Highlander highlanderMock = Mock() {
      fight() >> { println "I am just mocking you" }
    }
    singletonTool.setSingleton(highlanderMock)
    def highlander = Highlander.instance

    when:
    highlander.fight()

    then:
    highlander == highlanderMock
    0 * highlander.doSomething()

    where:
    fightNo << [1, 2, 3]
  }

  @Unroll
  def "Highlander spy fight no. #fightNo"() {
    given:
    // Unset not necessary because Objenesis creates object without constructor call
    // singletonTool.unsetSingleton()
    Highlander highlanderSpy = Spy(useObjenesis: true)
    // Spy's member is not initialised by Objenesis
    highlanderSpy.count = 0
    singletonTool.setSingleton(highlanderSpy)
    def highlander = Highlander.instance

    when:
    highlander.fight()

    then:
    highlander == highlanderSpy
    highlander.count == 1
    1 * highlander.doSomething() >> { println "I spy" }

    where:
    fightNo << [1, 2, 3]
  }

  @Unroll
  def "Use singleton mock in #highlanderReferer.class.simpleName"() {
    given:
    Highlander highlanderMock = Mock()
    singletonTool.setSingleton(highlanderMock)

    when:
    highlanderReferer.doSomething()
    then:
    Highlander.instance == highlanderMock
    1 * highlanderMock.fight() >> { println "I am just mocking you" }
    0 * highlanderMock.doSomething()

    when:
    singletonTool.reinitialiseSingleton()
    then:
    Highlander.instance != highlanderMock

    where:
    highlanderReferer << [new HighlanderRefererJava(), new HighlanderRefererGroovy()]
  }
}
