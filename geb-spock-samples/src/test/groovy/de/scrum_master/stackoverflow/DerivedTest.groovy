package de.scrum_master.stackoverflow

import spock.lang.Unroll

import static java.lang.System.currentTimeMillis

class DerivedTest extends MasterSpec {
  def setupSpec() {
    sleep 50
    println "DerivedTest.setupSpec: " + (currentTimeMillis() - startMillis.get())
  }

  def cleanupSpec() {
    sleep 50
    println "DerivedTest.cleanupSpec: " + (currentTimeMillis() - startMillis.get())
  }

  def setup() {
    sleep 50
    println "DerivedTest.setup: " + (currentTimeMillis() - startMillis.get())
  }

  def cleanup() {
    sleep 50
    println "DerivedTest.cleanup: " + (currentTimeMillis() - startMillis.get())
  }

  @Unroll
  def "feature #id"() {
    given:
    long featureStartMillis = currentTimeMillis()
    println "DerivedTest.feature $id START: " + (currentTimeMillis() - startMillis.get())
    sleep 50
    println "DerivedTest.feature $id: " + (currentTimeMillis() - startMillis.get())

    expect:
    true

    cleanup:
    println "DerivedTest.feature $id ONLY: " + (currentTimeMillis() - featureStartMillis)

    where:
    id << ["A", "B"]
  }
}
