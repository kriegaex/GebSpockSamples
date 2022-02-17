package de.scrum_master.stackoverflow

import groovy.util.logging.Slf4j

@Slf4j
class ClazzX {
  static void main(String[] args) {
    def iLog = log
    Object obj = new LombokLoggerUser()
    try {
      iLog = obj.log
    }
    catch (MissingPropertyException e) {
      println e
    }
    iLog.info("test")
  }
}
