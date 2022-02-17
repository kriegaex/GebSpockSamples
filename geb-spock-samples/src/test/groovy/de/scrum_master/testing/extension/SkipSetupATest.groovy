package de.scrum_master.testing.extension

import spock.lang.Specification

class SkipSetupATest extends Specification {
  def setup() {
    println "SkipSetupTestA -> setup"
  }

  def feature1() {
    setup:
    println "SkipSetupTestA -> feature1"
    expect: true
  }

  @SkipSetup
  def feature2() {
    setup:
    println "SkipSetupTestA -> feature2"
    expect: true
  }

  def feature3() {
    setup:
    println "SkipSetupTestA -> feature3"
    expect: true
  }

  @SkipSetup
  def feature4() {
    setup:
    println "SkipSetupTestA -> feature4"
    expect: true
  }
}
