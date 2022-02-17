package de.scrum_master.stackoverflow

import org.spockframework.mock.MockUtil
import spock.lang.Specification
import spock.lang.Unroll
import spock.mock.AutoAttach
import spock.mock.DetachedMockFactory

class DetachedMockTest extends Specification {
  static mockFactory = new DetachedMockFactory()

  @AutoAttach
  def engine = mockFactory.Mock(Engine)

  def "Auto-attach detached mock"() {
    given:
    engine.isStarted() >> { println "Fake started state"; true }
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
  }

  def "Manually attach detached mock"() {
    given:
    def myEngine = mockFactory.Mock(Engine)
    myEngine.isStarted() >> { println "Fake started state"; true }
    def mockUtil = new MockUtil()
    mockUtil.attachMock(myEngine, this)
    def car = new Car(engine: myEngine)

    when:
    car.drive()
    then:
    myEngine.isStarted()
    1 * myEngine.start() >> { println "Starting engine" }
    myEngine.isStarted()

    when:
    car.park()
    then:
    myEngine.isStarted()
    1 * myEngine.stop() >> { println "Stopping engine" }
    myEngine.isStarted()

    cleanup:
    mockUtil.detachMock(myEngine)
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
