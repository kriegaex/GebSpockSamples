package de.scrum_master.stackoverflow.q63652119

import org.spockframework.mock.MockUtil
import spock.mock.DetachedMockFactory

class DerivedSpec extends AbstractSpec {
  static mockFactory = new DetachedMockFactory()
  static EventBus eventBus = mockFactory.Mock(EventBus)

  def "check if proper event was emited"() {
    given:
    ExpectedEvent publishedEvent
    new MockUtil().attachMock(eventBus, this)

    when:
    def someId = facade.doSomething().block()
    println "facade = $facade, eventBus = ${eventBus()}"

    then:
    1 * eventBus.push(_ as ExpectedEvent) >> { ExpectedEvent event -> publishedEvent = event }
    publishedEvent.id == someId
  }

  EventBus eventBus() {
    return eventBus
  }
}
