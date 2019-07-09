package de.scrum_master.stackoverflow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Clazz {
  public void method() {
    // ... code
    log.warn("message");
    log.warn("message", new RuntimeException("uh-oh"));
  }
}
