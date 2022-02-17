package de.scrum_master.stackoverflow.q63652119;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.just;

@AllArgsConstructor
public class Facade {
  private final EventBus events;

  Mono<String> doSomething() {
    return just("someId").doOnNext(id -> events.push(new ExpectedEvent(id)));
  }

  /** And many other methods required to initialize the facade in the mentioned test **/
}
