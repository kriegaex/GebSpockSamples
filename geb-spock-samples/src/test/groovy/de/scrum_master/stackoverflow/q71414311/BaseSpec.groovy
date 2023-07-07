package de.scrum_master.stackoverflow.q71414311

import spock.lang.Specification

class BaseSpec extends Specification {
  def setupSpec() {
    println "BaseSpec.setupSpec"
  }

  def cleanupSpec() {
    println "BaseSpec.cleanupSpec"
  }

  def setup() {
    println "BaseSpec.setup: $specificationContext.currentIteration.name"
  }

  def cleanup() {
    println "BaseSpec.cleanup: $specificationContext.currentIteration.name"
  }
}
