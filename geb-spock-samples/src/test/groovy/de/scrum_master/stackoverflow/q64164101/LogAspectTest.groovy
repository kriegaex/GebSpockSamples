package de.scrum_master.stackoverflow.q64164101

import org.aspectj.lang.ProceedingJoinPoint
import reactor.core.publisher.Mono
import spock.lang.Specification

class LogAspectTest extends Specification {
  LogAspect logAspect = Spy()
  ProceedingJoinPoint joinPoint = Mock()

  def "aspect target method returns a Mono"() {
    given:
    joinPoint.proceed() >> Mono.just("Hello")

    when:
    logAspect.logAround(joinPoint)

    then:
    1 * logAspect.getConsumer(joinPoint, _)
  }

  def "aspect target method does not return a Mono"() {
    given:
    joinPoint.proceed() >> "dummy"

    when:
    logAspect.logAround(joinPoint)

    then:
    0 * logAspect.getConsumer(joinPoint, _)
  }
}
