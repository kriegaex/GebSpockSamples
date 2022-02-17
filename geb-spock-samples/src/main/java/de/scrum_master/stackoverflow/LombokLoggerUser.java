package de.scrum_master.stackoverflow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LombokLoggerUser {
  public void method() {
    // ... code
    log.warn("message");
    log.warn("message", new RuntimeException("uh-oh"));
  }

  public static void main(String[] args) {
    new LombokLoggerUser().method();
  }
}
