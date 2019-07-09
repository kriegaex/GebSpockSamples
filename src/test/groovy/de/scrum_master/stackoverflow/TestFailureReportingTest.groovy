package de.scrum_master.stackoverflow

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

@Ignore("test fails on purpose; activate if you want to see special error output")
class TestFailureReportingTest extends Specification {
  def normalFeature() {
    expect:
    0 == 1
  }

  def parametrisedFeature() {
    expect:
    a == b

    where:
    a | b
    2 | 3
    4 | 5
    6 | 6
  }

  @Unroll
  def unrolledParametrisedFeature() {
    expect:
    a == b

    where:
    a | b
    6 | 7
    8 | 9
    0 | 0
  }

  def cleanup() {
    specificationContext.currentSpec.listeners
      .findAll { it instanceof MyGlobalExtension.ErrorListener }
      .each {
        def errorInfo = (it as MyGlobalExtension.ErrorListener).errorInfo
        if (errorInfo)
          println "Test failure in feature '${specificationContext.currentFeature.name}', " +
            "exception class ${errorInfo.exception.class.simpleName}"
        else
          println "Test passed in feature '${specificationContext.currentFeature.name}'"
      }
  }
}
