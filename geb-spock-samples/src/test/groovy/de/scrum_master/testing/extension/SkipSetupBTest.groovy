package de.scrum_master.testing.extension

import spock.lang.Specification

class SkipSetupBTest extends Specification {
  def setup() {
    println "SkipSetupTestB -> setup"
  }

  def feature1() {
    setup:
    println "SkipSetupTestB -> feature1"
    expect: true
  }

  @SkipSetup
  def feature2() {
    setup:
    println "SkipSetupTestB -> feature2"
    expect: true
  }

  def feature3() {
    setup:
    println "SkipSetupTestB -> feature3"
    expect: true
  }

  @SkipSetup
  def feature4() {
    setup:
    println "SkipSetupTestB -> feature4"
    expect: true
  }
}
