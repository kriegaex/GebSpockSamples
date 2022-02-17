package de.scrum_master.testing.extension

import spock.lang.Specification

class SetupOnceTest extends Specification {
  def setup() {
    println "SetupOnceTest -> setup"
  }

  def feature1() {
    setup:
    println "SetupOnceTest -> feature1"
    expect:
    true
  }

  @SetupOnce
  def feature2() {
    setup:
    println "SetupOnceTest -> feature2"
    expect:
    true
  }

  def feature3() {
    setup:
    println "SetupOnceTest -> feature3, iteration $count"
    expect:
    true
    where:
    count << [1, 2, 3]
  }

  @SetupOnce
  def feature4() {
    setup:
    println "SetupOnceTest -> feature4, iteration $count"
    expect:
    true
    where:
    count << [1, 2, 3]
  }
}
