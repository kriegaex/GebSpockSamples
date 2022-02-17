package de.scrum_master.stackoverflow

import geb.spock.GebReportingSpec
import spock.lang.Requires
import spock.lang.Shared

// Rename src/test/resources/XGebConfig.groovy to GebConfig.groovy before running this
@Requires({ new File("src/test/resources/GebConfig.groovy").exists() })
class GebConfigIT extends GebReportingSpec {
  @Shared
  def sharedLang = {
    println "Initialising sharedLang"
    System.getProperty("geb.env.lang")
  }()

  def normalLang = {
    println "Initialising normalLang"
    System.getProperty("geb.env.lang")
  }()

  def setup() {
    println "sharedLang = $sharedLang"
    println "normalLang = $normalLang"
  }

  def foo() {
    expect:
    !sharedLang
    normalLang
  }

  def bar() {
    expect:
    !sharedLang
    normalLang
  }
}
