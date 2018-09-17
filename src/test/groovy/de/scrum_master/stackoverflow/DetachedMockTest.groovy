package de.scrum_master.stackoverflow

import org.spockframework.mock.MockUtil
import spock.lang.Specification
import spock.mock.DetachedMockFactory

class DetachedMockTest extends Specification {
  static mockFactory = new DetachedMockFactory()
  static mockUtil = new MockUtil()

  def "Use detached mock"() {
    given:
    def engine = mockFactory.Mock(Engine)
    engine.isStarted() >> { println "Fake started state"; true }
    mockUtil.attachMock(engine, this)
    def car = new Car(engine: engine)

    when:
    car.drive()
    then:
    engine.isStarted()
    1 * engine.start() >> { println "Starting engine" }
    engine.isStarted()

    when:
    car.park()
    then:
    engine.isStarted()
    1 * engine.stop() >> { println "Stopping engine" }
    engine.isStarted()

    cleanup:
    mockUtil.detachMock(engine)
  }

  static class Engine {
    private boolean started

    boolean isStarted() { return started }
    void start() { started = true }
    void stop() { started = false }
  }

  static class Car {
    private Engine engine
    void drive() { engine.start() }
    void park() { engine.stop() }
  }
}
