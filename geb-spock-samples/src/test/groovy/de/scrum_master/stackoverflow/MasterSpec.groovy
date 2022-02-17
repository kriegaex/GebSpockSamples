package de.scrum_master.stackoverflow

import spock.lang.Specification
import static java.lang.System.currentTimeMillis

class MasterSpec extends Specification {
  static ThreadLocal<Long> startMillis = new ThreadLocal<>()

  def setupSpec() {
    startMillis.set(currentTimeMillis())
    sleep 50
    println "BaseSpec.setupSpec: " + (currentTimeMillis() - startMillis.get())
  }

  def cleanupSpec() {
    sleep 50
    println "BaseSpec.cleanupSpec: " + (currentTimeMillis() - startMillis.get())
  }

  def setup() {
    sleep 50
    println "BaseSpec.setup: " + (currentTimeMillis() - startMillis.get())
  }

  def cleanup() {
    sleep 50
    println "BaseSpec.cleanup: " + (currentTimeMillis() - startMillis.get())
  }
}
