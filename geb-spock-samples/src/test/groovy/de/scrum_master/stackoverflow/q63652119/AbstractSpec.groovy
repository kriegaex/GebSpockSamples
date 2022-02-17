package de.scrum_master.stackoverflow.q63652119

import spock.lang.Shared
import spock.lang.Specification

import static reactor.core.publisher.Mono.just

abstract class AbstractSpec extends Specification {
  @Shared
  Facade facade = new Facade(eventBus())

  def setupSpec() {
    /** run different methods on the facade to prepare it for the tests **/
  }

  EventBus eventBus() {
    return { event -> just("No event bus configured") }
  }
}
