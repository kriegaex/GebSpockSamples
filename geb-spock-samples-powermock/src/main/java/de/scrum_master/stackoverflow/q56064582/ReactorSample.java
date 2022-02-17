package de.scrum_master.stackoverflow.q56064582;

import reactor.core.publisher.Mono;

public class ReactorSample {
  public Mono<String> doSomething() {
    return Mono.just("foo");
  }
}
