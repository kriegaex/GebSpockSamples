package de.scrum_master.testing

import groovy.transform.Memoized

import static mockit.internal.startup.Startup.verifyInitialization

class JavaAgentDetector {
  /**
   * Detects an active JMockit agent
   *
   * @return true if JMockit agent has been initialised successfully
   * (needs to be on the Java command line via '-javaagent:');<br>
   * false otherwise
   */
  @Memoized
  static boolean isJMockitActive() {
    try {
      verifyInitialization()
      return true
    } catch (IllegalStateException exception) {
      return false
    }
  }
}
